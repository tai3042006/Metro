import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private double amount;
    private LocalDateTime transactionDate;
    private TransactionType type;
    private String description;

    public Transaction(String id, double amount, TransactionType type, String description) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
    }
    
    public String getDetails() {
        return "Trans[" + id + "]: " + type + " - " + amount;
    }
}