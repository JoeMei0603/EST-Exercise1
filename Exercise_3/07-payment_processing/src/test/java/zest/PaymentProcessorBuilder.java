package zest;

import java.util.List;

import static org.mockito.Mockito.*;

public class PaymentProcessorBuilder {
    private EventPublisher eventPublisher = new EventPublisher();
    private TransactionService transactionService = mock(TransactionService.class);
    private FraudDetectionService fraudDetectionService = mock(FraudDetectionService.class);

    public PaymentProcessorBuilder defineTransactionEvaluation() {
        // Any transaction with positive id (int > 0) will return true
        when(fraudDetectionService
                .evaluateTransaction(argThat(
                        transaction -> transaction != null && transaction.getId() > 0)))
                .thenReturn(true);

        // Any transaction with id smaller equal zero (int <= 0) will return false
        when(fraudDetectionService
                .evaluateTransaction(argThat(
                        transaction -> transaction != null && transaction.getId() <= 0)))
                .thenReturn(false);
        return this;
    }

    public PaymentProcessorBuilder subscribeToEventPublisher(List<AuditService> auditServices) {
        for (AuditService auditService: auditServices) {
            eventPublisher.subscribe(auditService);
        }
        return this;
    }

    public PaymentProcessor build() {
        return new PaymentProcessor(eventPublisher, transactionService, fraudDetectionService);
    }
}
