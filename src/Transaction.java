import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private double amount;
    private LocalDateTime transactionDate;
    private TransactionType type;
    private String description;

    
    public Transaction() {
        this.transactionDate = LocalDateTime.now();
    }

    
    public Transaction(String id, double amount, TransactionType type, String description) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.transactionDate = LocalDateTime.now(); // Tự động lấy giờ hiện tại
    }
    
    // --- Getters & Setters  ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

   
    public String getDetails() {
        return "Trans[" + id + "]: " + type + " - " + String.format("%,.0f", amount) + " (" + description + ")";
    }
    
    @Override
    public String toString() {
        return getDetails();
    }
}