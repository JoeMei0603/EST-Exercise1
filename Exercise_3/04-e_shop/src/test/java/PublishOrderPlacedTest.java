import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PublishOrderPlacedTest {

    private EventPublisher eventPublisher;
    private EventListener emailNotificationServiceMock;
    private EventListener inventoryManagerMock;
    private Order order;

    @BeforeEach
    public void setUp() {
        eventPublisher = new EventPublisher();
        emailNotificationServiceMock = Mockito.mock(EventListener.class);
        inventoryManagerMock = Mockito.mock(EventListener.class);
        order = new Order("1", 13.0);
    }

    @Test
    public void testNumberOfInvocations() {

        eventPublisher.subscribe(emailNotificationServiceMock);
        eventPublisher.subscribe(inventoryManagerMock);

        eventPublisher.publishOrderToAllListeners(order);

        verify(emailNotificationServiceMock, times(1)).onOrderPlaced(order);
        verify(inventoryManagerMock, times(1)).onOrderPlaced(order);
    }

    @Test
    public void testContentOfInvocations() {

        eventPublisher.subscribe(emailNotificationServiceMock);
        eventPublisher.subscribe(inventoryManagerMock);

        eventPublisher.publishOrderToAllListeners(order);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);

        verify(emailNotificationServiceMock, times(1)).onOrderPlaced(orderCaptor.capture());
        verify(inventoryManagerMock, times(1)).onOrderPlaced(orderCaptor.capture());

        Order capturedOrder = orderCaptor.getValue();

        assertEquals("1", capturedOrder.getOrderId());
        assertEquals(13.0, capturedOrder.getAmount());
    }

    @Test
    public void testContentOfEmail() {
        EventPublisher publisher = new EventPublisher();
        EmailNotificationService emailService = new EmailNotificationService();
        publisher.subscribe(emailService);

        Order order = new Order("22", 1000.0);
        publisher.publishOrderToAllListeners(order);

        Order lastReceivedOrder = emailService.getLastReceivedOrderForTesting();

        assertEquals(order.getOrderId(), lastReceivedOrder.getOrderId());
    }

    @Test
    public void testInventoryContent() {
        EventPublisher publisher = new EventPublisher();
        InventoryManager inventoryManager = new InventoryManager();
        publisher.subscribe(inventoryManager);

        Order order = new Order("11", 13.0);
        publisher.publishOrderToAllListeners(order);

        Order lastReceivedOrder = inventoryManager.getLastReceivedOrderForTesting();

        assertEquals(order.getOrderId(), lastReceivedOrder.getOrderId());
    }

}
