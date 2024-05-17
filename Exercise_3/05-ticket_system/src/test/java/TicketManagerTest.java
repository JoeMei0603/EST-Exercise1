import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketManagerTest {

    private Ticket ticket;
    private TicketManager ticketManager;
    private NotificationService notificationService;
    private LogService logService;
    private TicketRepository ticketRepository;

    @BeforeEach
    void testSetup() {
        this.notificationService = new NotificationServiceStub(true);
        this.logService = new LogServiceStub(true);
        this.ticketRepository = new TicketRepositoryStub(true);

        this.ticketManager = new TicketManager(this.notificationService, this.logService, this.ticketRepository);
        this.ticket = new Ticket("default@test.com", "default issue", TicketPriority.NORMAL);
    }

    @Test
    void testCreateTicketNull() {
        assertThrows(IllegalArgumentException.class, () -> this.ticketManager.createTicket(null));
    }

//    @Test
//    void testCreateTicketLogServiceUnreachable() {
//        this.logService = new LogServiceStub(false);
//        this.ticketManager = new TicketManager(this.notificationService, this.logService, this.ticketRepository);
//
//        assertThrows(RuntimeException.class, () -> this.ticketManager.createTicket(this.ticket));
//    }

    @Test
    void testCreateTicketLogServiceUnreachable() {
        this.logService = new LogServiceStub(false);
        this.ticketManager = new TicketManager(this.notificationService, this.logService, this.ticketRepository);

        Ticket savedTicket = this.ticketManager.createTicket(this.ticket);

        assertEquals(this.ticket, savedTicket);
    }

//    @Test
//    void testCreateTicketNotificationServiceUnreachable() {
//        this.notificationService = new NotificationServiceStub(false);
//        this.ticketManager = new TicketManager(this.notificationService, this.logService, this.ticketRepository);
//
//        assertThrows(RuntimeException.class, () -> this.ticketManager.createTicket(this.ticket));
//    }

    @Test
    void testCreateTicketNotificationServiceUnreachable() {
        this.notificationService = new NotificationServiceStub(false);
        this.ticketManager = new TicketManager(this.notificationService, this.logService, this.ticketRepository);

        Ticket savedTicket = this.ticketManager.createTicket(this.ticket);

        assertEquals(this.ticket, savedTicket);
    }

    @Test
    void testCreateTicketTicketRepositoryUnreachable() {
        this.ticketRepository = new TicketRepositoryStub(false);
        this.ticketManager = new TicketManager(this.notificationService, this.logService, this.ticketRepository);

        assertThrows(RuntimeException.class, () -> this.ticketManager.createTicket(this.ticket));
    }

    @Test
    void testCreateTicketSuccessful() {
        Ticket savedTicket = this.ticketManager.createTicket(this.ticket);

        assertEquals(this.ticket, savedTicket);
    }

}
