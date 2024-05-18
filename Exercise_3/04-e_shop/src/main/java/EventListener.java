public interface EventListener {
    void onOrderPlaced(Order order);
    Order getLastReceivedOrder();
}
