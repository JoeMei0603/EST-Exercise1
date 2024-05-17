public class TicketRepositoryStub implements TicketRepository {

    private final boolean reachable;

    public TicketRepositoryStub (boolean reachable) {
        this.reachable = reachable;
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (!this.reachable) {
            throw new RuntimeException("TicketRepository is not reachable");
        }
        System.out.println("Ticket has been saved, Ticket Id: " + ticket.getId());
        return ticket;
    }
}
