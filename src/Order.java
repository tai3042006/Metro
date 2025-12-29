import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public String id;
    public List<Ticket> listOfTickets;
    public OrderStatus status;
    public double totalPrice;
    public LocalDate createdAt;
    public Payment payment;
    
    // [QUAN TRỌNG] Thêm biến này để biết đơn hàng của ai -> Tính giảm giá
    public Customer customer; 

    /**
     * Default constructor
     */
    public Order() {
        this.listOfTickets = new ArrayList<>();
        this.createdAt = LocalDate.now();
        this.status = OrderStatus.PENDING;
        this.totalPrice = 0.0;
    }

    /**
     * Constructor có tham số (Cập nhật thêm Customer)
     * @param id Mã đơn hàng
     * @param customer Khách hàng mua vé
     */
    public Order(String id, Customer customer) {
        this(); // Gọi constructor mặc định để khởi tạo List
        this.id = id;
        this.customer = customer;
        
        // Logic phụ: Thêm đơn hàng này vào lịch sử của khách
        if (customer != null) {
            customer.addOrder(this);
        }
    }

    /**
     * Thêm vé vào đơn hàng và tự động cập nhật tổng tiền
     */
    public void addTicket(Ticket ticket) {
        this.listOfTickets.add(ticket);
        updateTotalPrice();
    }

    /**
     * Cập nhật tổng tiền sử dụng JAVA 8 STREAM (Điểm cộng 7đ+)
     */
    public void updateTotalPrice() {
        if (this.listOfTickets != null) {
            this.totalPrice = this.listOfTickets.stream()
                                  .mapToDouble(Ticket::getPrice)
                                  .sum();
        }
    }

    /**
     * Validate đơn hàng
     */
    public boolean validateOrder() {
        boolean hasTickets = this.listOfTickets != null && !this.listOfTickets.isEmpty();
        boolean isNotCancelled = this.status != OrderStatus.CANCELLED;
        return hasTickets && isNotCancelled;
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
        System.out.println("Order " + this.id + " has been cancelled.");
    }

    // --- Getters & Setters ---
    
    public double getBalance() { return this.totalPrice; }
    
    public Customer getCustomer() { return customer; } // Cần hàm này cho TicketService gọi
    public void setCustomer(Customer customer) { this.customer = customer; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public LocalDate getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customer=" + (customer != null ? customer.name : "null") +
                ", total=" + totalPrice +
                ", status=" + status +
                ", date=" + createdAt +
                '}';
    }
}