import java.io.*;
import java.util.*;

public class Customer {

    public String id;
    public String name;
    public String phone;
    
    // Sửa type: List<Order> thay vì List<Order> OrderHistory (đặt tên biến camelCase)
    public List<Order> orderHistory;

    // Sửa type: Dùng Enum CustomerType
    public CustomerType type;

    // Sửa type: Dùng Class StudentCard (viết hoa chữ cái đầu)
    public StudentCard studentCard;

    public double balance;

    /**
     * Default constructor
     */
    public Customer() {
        // Luôn khởi tạo danh sách để tránh lỗi Null
        this.orderHistory = new ArrayList<>();
        this.balance = 0.0;
    }

    /**
     * Constructor có tham số (Nên dùng cái này)
     */
    public Customer(String id, String name, CustomerType type) {
        this(); // Gọi constructor mặc định để khởi tạo List
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
            this.type = CustomerType.STUDENT;
        }
    }

    /**
     * Lấy thông tin thẻ sinh viên
     * Sửa kiểu trả về từ void -> StudentCard
     */
    public StudentCard getStudentCard() {
        return this.studentCard;
    }

    /**
     * Nạp tiền vào tài khoản
     * @param amount Số tiền cần nạp
     * @return Số dư mới
     */
    public double topUpBalance(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Nạp thành công " + amount + ". Số dư mới: " + this.balance);
        }
        return this.balance;
    }

    /**
     * Hàm thêm đơn hàng vào lịch sử (Hỗ trợ logic Order)
     */
    public void addOrder(Order order) {
        if (this.orderHistory != null) {
            this.orderHistory.add(order);
        }
    }
}