import java.time.LocalDate;

public class Payment {

    private String id;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod; // Ví dụ: "Cash", "Credit Card", "Momo"
    private boolean isSuccessful;

    /**
     * Default constructor
     */
    public Payment() {
        // Tự động gán ngày thanh toán là hôm nay
        this.paymentDate = LocalDate.now();
        // Giả lập thanh toán luôn thành công (để test cho dễ)
        this.isSuccessful = true;
    }

    /**
     * Constructor đầy đủ tham số
     * @param id Mã giao dịch
     * @param amount Số tiền
     * @param paymentMethod Phương thức thanh toán
     */
    public Payment(String id, double amount, String paymentMethod) {
        this(); // Gọi constructor mặc định để set ngày
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // --- Getters & Setters (Bắt buộc để các class khác lấy dữ liệu) ---

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