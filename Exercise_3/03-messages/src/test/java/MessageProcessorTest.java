import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageProcessorTest {

    private MessageService messageServiceMock;
    private MessageProcessor messageProcessor;

    @BeforeEach
    public void setUp() {
        messageServiceMock = Mockito.mock(MessageService.class);
        messageProcessor = new MessageProcessor(messageServiceMock);
    }

    @Test
    public void testNumberOfInvocations() {
        Message message1 = new Message("Jamo", "Jerome", "Hi Jerome!");
        Message message2 = new Message("Joel", "Faizan", "Hi Faizan!");
        Message message3 = new Message("Jamo", "Joel", "Hi Joel!");

        messageProcessor.processMessages(Arrays.asList(message1, message2, message3));
        verify(messageServiceMock, times(3)).sendMessage(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testContentOfInvocations() {
        Message message1 = new Message("Jamo", "Jerome", "Hello, Jerome!");
        Message message2 = new Message("Joel", "Faizan", "Hi, Faizan!");
        Message message3 = new Message("Jamo", "Joel", "Good morning, Joel!");

        List<Message> messages = Arrays.asList(message1, message2, message3);
        messageProcessor.processMessages(messages);

        ArgumentCaptor<String> receiver = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> content = ArgumentCaptor.forClass(String.class);

        verify(messageServiceMock, times(3)).sendMessage(receiver.capture(), content.capture());

        List<String> receiversCaptured = receiver.getAllValues();
        List<String> contentsCaptured = content.getAllValues();


        assertEquals("Jerome", receiversCaptured.get(0));
        assertEquals("Hello, Jerome!", contentsCaptured.get(0));

        assertEquals("Faizan", receiversCaptured.get(1));
        assertEquals("Hi, Faizan!", contentsCaptured.get(1));

        assertEquals("Joel", receiversCaptured.get(2));
        assertEquals("Good morning, Joel!", contentsCaptured.get(2));
    }

    @Test
    public void testContentOfInvocationsBetterObservability() {
        Message message1 = new Message("Jamo", "Jerome", "Hello, Jerome!");
        Message message2 = new Message("Joel", "Faizan", "Hi, Faizan!");
        Message message3 = new Message("Jamo", "Joel", "Good morning, Joel!");

        List<Message> messages = Arrays.asList(message1, message2, message3);
        messageProcessor.processMessages(messages);

        List<Message> sentMessages = messageProcessor.getSentMessages();

        assertEquals(3, sentMessages.size());

        assertEquals("Jerome", sentMessages.get(0).getReceiver());
        assertEquals("Hello, Jerome!", sentMessages.get(0).getContent());

        assertEquals("Faizan", sentMessages.get(1).getReceiver());
        assertEquals("Hi, Faizan!", sentMessages.get(1).getContent());

        assertEquals("Joel", sentMessages.get(2).getReceiver());
        assertEquals("Good morning, Joel!", sentMessages.get(2).getContent());
    }

    //additional edge case test
    @Test
    public void testEmptyMessageList() {
        List<Message> emptyMessages = emptyList();
        messageProcessor.processMessages(emptyMessages);
        verify(messageServiceMock, times(0)).sendMessage(Mockito.anyString(), Mockito.anyString());
        assertEquals(0, messageProcessor.getSentMessages().size());
    }

}
