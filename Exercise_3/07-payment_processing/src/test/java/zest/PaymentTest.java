package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PaymentTest {

    private PaymentProcessor paymentProcessor;

    private List<AuditService> auditServices;

    @BeforeEach
    public void setupPaymentProcessor() {
        // Setup AuditService
        this.auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));

        // Setup PaymentProcessor
        PaymentProcessorBuilder paymentProcessorBuilder = new PaymentProcessorBuilder();
        this.paymentProcessor = paymentProcessorBuilder
                 .defineTransactionEvaluation()
                 .subscribeToEventPublisher(auditServices)
                 .build();
    }

    @Test
    public void testOnTransactionCompleteOnlyValidTransactions () {
        // Setup valid Transactions
        Transaction transactionOne = createTransaction(1);
        Transaction transactionTwo = createTransaction(2);

        // Process valid Transactions
        paymentProcessor.processPayment(transactionOne);
        paymentProcessor.processPayment(transactionTwo);

        // Assert number of onTransactionComplete calls
        for (AuditService auditService: auditServices) {
            verify(auditService, times(2)).onTransactionComplete(any(Transaction.class));
        }
    }

    @Test
    public void testOnTransactionCompleteOnlyInvalidTransactions () {
        // Setup invalid Transactions
        Transaction transactionOne = createTransaction(0);
        Transaction transactionTwo = createTransaction(-1);

        // Process invalid Transactions
        paymentProcessor.processPayment(transactionOne);
        paymentProcessor.processPayment(transactionTwo);

        // Assert number of onTransactionComplete calls
        for (AuditService auditService: auditServices) {
            verify(auditService, times(0)).onTransactionComplete(any(Transaction.class));
        }
    }

    @Test
    public void testOnTransactionCompleteMixedTransactions () {
        // Setup mixed Transactions
        Transaction transactionOne = createTransaction(1);
        Transaction transactionTwo = createTransaction(-1);

        // Process invalid Transactions
        paymentProcessor.processPayment(transactionOne);
        paymentProcessor.processPayment(transactionTwo);

        // Assert number of onTransactionComplete calls
        for (AuditService auditService: auditServices) {
            verify(auditService, times(1)).onTransactionComplete(any(Transaction.class));
        }
    }

    @Test
    public void testOnTransactionCompleteArgumentCaptorSeveralTransactions() {
        // Setup variables
        Transaction firstValidTransaction = createTransaction(5);
        Transaction secondValidTransaction = createTransaction(3);
        Transaction firstInvalidTransaction = createTransaction(-7);
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);

        // Process Transactions
        paymentProcessor.processPayment(firstValidTransaction);
        paymentProcessor.processPayment(secondValidTransaction);
        paymentProcessor.processPayment(firstInvalidTransaction);

        // Assert captured arguments
        for (AuditService auditService: auditServices) {
            // Make sure a transaction was completed 2 out of 3 times
            verify(auditService, times(2)).onTransactionComplete(transactionCaptor.capture());

            // Get and assert all transactions which were captured
            List<Transaction> validTransactionList = transactionCaptor.getAllValues();
            assertEquals(validTransactionList.size(), 2);
            assertEquals(validTransactionList.get(0).getId(), 5);
            assertEquals(validTransactionList.get(1).getId(), 3);
        }
    }

    @Test
    public void testOnTransactionCompleteReturnValidTransactions() {
        // Setup variables
        Transaction firstValidTransaction = createTransaction(2);
        Transaction secondValidTransaction = createTransaction(4);

        // Process Transactions
        Transaction firstReturnedTransaction = paymentProcessor.processPayment(firstValidTransaction);
        Transaction secondReturnedTransaction = paymentProcessor.processPayment(secondValidTransaction);

        // Assert returned arguments
        assertEquals(firstReturnedTransaction.getId(), firstValidTransaction.getId());
        assertEquals(secondReturnedTransaction.getId(), secondValidTransaction.getId());
    }

    @Test
    public void testOnTransactionCompleteReturnMixedTransactions() {
        // Setup variables
        Transaction firstValidTransaction = createTransaction(10);
        Transaction firstInvalidTransaction = createTransaction(-3);

        // Process Transactions
        Transaction firstReturnedTransaction = paymentProcessor.processPayment(firstValidTransaction);
        Transaction secondReturnedTransaction = paymentProcessor.processPayment(firstInvalidTransaction);

        // Assert returned arguments
        assertEquals(firstReturnedTransaction.getId(), firstValidTransaction.getId());
        assertNull(secondReturnedTransaction);
    }

    private Transaction createTransaction(int Id) {
        return new Transaction(Id);
    }
}
