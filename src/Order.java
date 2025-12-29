import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    
    private String id;
    private List<Ticket> listOfTickets;
    private OrderStatus status;
    private double totalPrice;
    private LocalDate createdAt;
    private Payment payment;
    private Customer customer; 

    
    public Order() {
        this.listOfTickets = new ArrayList<>();
        this.createdAt = LocalDate.now();
        this.status = OrderStatus.PENDING;
        this.totalPrice = 0.0;
    }

    /**
     * Constructor có tham số 
     * @param id Mã đơn hàng
     * @param customer Khách hàng mua vé
     */
    public Order(String id, Customer customer) {
        this(); 
        this.id = id;
        this.customer = customer;
        
        
        if (customer != null) {
            customer.addOrder(this);
        }
    }

  
    public void addTicket(Ticket ticket) {
        this.listOfTickets.add(ticket);
        updateTotalPrice();
    }

    
    public void updateTotalPrice() {
        if (this.listOfTickets != null) {
            this.totalPrice = this.listOfTickets.stream()
                                  .mapToDouble(Ticket::getPrice)
                                  .sum();
        }
    }

   
    public boolean validateOrder() {
        boolean hasTickets = this.listOfTickets != null && !this.listOfTickets.isEmpty();
        boolean isNotCancelled = this.status != OrderStatus.CANCELLED;
        return hasTickets && isNotCancelled;
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
        System.out.println("Order " + this.id + " has been cancelled.");
    }

    // --- Getters & Setters 
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getBalance() { return this.totalPrice; }
    public double getTotalPrice() { return this.totalPrice; } 
    
    public Customer getCustomer() { return customer; } 
    public void setCustomer(Customer customer) { this.customer = customer; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public LocalDate getCreatedAt() { return createdAt; }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public List<Ticket> getListOfTickets() { return listOfTickets; }

    @Override
    public String toString() {
        
        return "Order{" +
                "id='" + id + '\'' +
                ", customer=" + (customer != null ? customer.getName() : "null") +
                ", total=" + totalPrice +
                ", status=" + status +
                ", date=" + createdAt +
                '}';
    }
}