import java.time.LocalDate;

public class Payment {

    private String id;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod; 
    private boolean isSuccessful;

    public Payment() {
       
        this.paymentDate = LocalDate.now();
        this.isSuccessful = true;
    }

    public Payment(String id, double amount, String paymentMethod) {
        this(); 
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // --- Getters & Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}