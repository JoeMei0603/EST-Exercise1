public class NotificationServiceStub implements NotificationService {

    private final boolean reachable;

    public NotificationServiceStub (boolean reachable) {
        this.reachable = reachable;
    }

    @Override
    public void notifyCustomer(String customerEmail, String message) {
        if (!this.reachable) {
            throw new RuntimeException("NotificationService is not reachable");
        }
        System.out.println("Customer Email: " + customerEmail + "; Message: " + message);
    }
}
