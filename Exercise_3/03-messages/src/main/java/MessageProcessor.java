import java.util.ArrayList;
import java.util.List;

public class MessageProcessor {

   private MessageService messageService;
   private List<Message> sentMessages;

    public MessageProcessor(MessageService messageService) {
        this.messageService = messageService;
        this.sentMessages = new ArrayList<>();
    }

    public void processMessages(List<Message> messages) {
        for (Message message : messages) {
            messageService.sendMessage(message.getReceiver(), message.getContent());
            sentMessages.add(message);
        }
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

}
