public class EmailNotificationService implements EventListener {
    private Order lastReceivedOrder;
    @Override
    public void onOrderPlaced(Order order) {
        // Logic to send email about the order would go here...
        System.out.println("Email sent for order " + order.getOrderId());
        lastReceivedOrder = order;
    }

    @Override
    public Order getLastReceivedOrder() {
        return lastReceivedOrder;
    }

    public Order getLastReceivedOrderForTesting() {
        return lastReceivedOrder;
    }


}