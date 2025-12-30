import java.io.*;
import java.util.*;

public class Customer {

    private String id;
    private String name;
    private String phone;
    private CustomerType type;
    private StudentCard studentCard;
    private double balance;
    
    // Danh sách lịch sử mua vé
    private List<Order> orderHistory;
    
    // Danh sách lịch sử giao dịch
    private List<Transaction> transactionHistory;

    
    public Customer() {
        this.orderHistory = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
        this.balance = 0.0;
    }

    public Customer(String id, String name, CustomerType type) {
        this(); 
        this.id = id;
        this.name = name;
        this.type = type;
    }

    
     // Nếu là Student
    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
        if (studentCard != null) {
            this.type = CustomerType.STUDENT;
            System.out.println("Đã cập nhật khách hàng " + this.name + " thành loại STUDENT.");
        }
    }

    // Nạp tiền vào ví
    public double topUpBalance(double amount) {
        if (amount > 0) {
            this.balance += amount;
            
            // --- Tạo giao dịch lưu vào lịch sử ---
            Transaction t = new Transaction(
                "TR_" + System.nanoTime(), 
                amount, 
                TransactionType.TOP_UP, 
                "Nap tien vao vi"
            );
            this.transactionHistory.add(t);
            
            System.out.println("Nạp thành công " + String.format("%,.0f", amount) + ". Số dư mới: " + String.format("%,.0f", this.balance));
        }
        return this.balance;
    }

    public void addOrder(Order order) {
        if (this.orderHistory != null) {
            this.orderHistory.add(order);
        }
    }
    
    // Dùng khi thanh toán vé
    public void addTransaction(Transaction transaction) {
        if (this.transactionHistory != null) {
            this.transactionHistory.add(transaction);
        }
    }

    // --- Getters & Setters ---

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
    
    // Getter cho Transaction History
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
}