import java.io.*;
import java.util.*;

public class Customer {


    private String id;
    private String name;
    private String phone;
    private List<Order> orderHistory;
    private CustomerType type;
    private StudentCard studentCard;
    private double balance;

   
    public Customer() {
        this.orderHistory = new ArrayList<>();
        this.balance = 0.0;
    }

  
     
    public Customer(String id, String name, CustomerType type) {
        this(); 
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Gán thẻ sinh viên cho khách hàng
     * Logic: Nếu gán thẻ, tự động chuyển loại khách thành STUDENT
     * @param studentCard
     */
    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
        if (studentCard != null) {
            this.type = CustomerType.STUDENT; // Tự động cập nhật loại khách
            System.out.println("Đã cập nhật khách hàng " + this.name + " thành loại STUDENT.");
        }
    }

    /**
     * Nạp tiền vào tài khoản
     * @param amount Số tiền cần nạp
     * @return Số dư mới
     */
    public double topUpBalance(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Nạp thành công " + String.format("%,.0f", amount) + ". Số dư mới: " + String.format("%,.0f", this.balance));
        }
        return this.balance;
    }

    
    public void addOrder(Order order) {
        if (this.orderHistory != null) {
            this.orderHistory.add(order);
        }
    }

    // --- Getters & Setter ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public CustomerType getType() { return type; } 
    public void setType(CustomerType type) { this.type = type; }

    public StudentCard getStudentCard() { return studentCard; }
    
    public double getBalance() { return balance; }

    public List<Order> getOrderHistory() { return orderHistory; }
}