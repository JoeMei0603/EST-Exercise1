public class LogServiceStub implements LogService {

    private final boolean reachable;

    public LogServiceStub (boolean reachable) {
        this.reachable = reachable;
    }

    @Override
    public void logTicketCreation(Ticket ticket) {
        if (!this.reachable) {
            throw new RuntimeException("LogService is not reachable");
        }
        System.out.println("Ticket creation has been logged, Ticket Id: " + ticket.getId());
    }
}
