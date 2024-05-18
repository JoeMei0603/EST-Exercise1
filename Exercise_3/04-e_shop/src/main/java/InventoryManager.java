public class InventoryManager implements EventListener {
    private Order lastReceivedOrder;
    @Override
    public void onOrderPlaced(Order order) {
        // Logic to update inventory based on order would go here
        System.out.println("Inventory updated for order " + order.getOrderId());
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