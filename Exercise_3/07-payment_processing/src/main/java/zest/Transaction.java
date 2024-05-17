package zest;

public class Transaction {
    public int transactionId;

    public Transaction (int transactionId) {
        this.transactionId = transactionId;
    }

    public int getId () {
        return this.transactionId;
    }
}
