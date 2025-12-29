import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * Class Vé Tháng
 * Kế thừa từ Ticket để tái sử dụng các thuộc tính cơ bản
 */
public class MonthlyTicket extends Ticket {

    /**
     * Java 8 Feature: YearMonth
     * Dùng để lưu trữ tháng và năm hiệu lực (Ví dụ: 2025-12)
     * Private để đảm bảo tính đóng gói (Encapsulation)
     */
    private YearMonth validMonth;

    /**
     * Default constructor
     */
    public MonthlyTicket() {
        super();
        this.validMonth = YearMonth.now();
    }

    /**
     * Constructor đầy đủ tham số
     * Logic: Khi khởi tạo vé tháng, mặc định vé có hiệu lực trong tháng hiện tại
     */
    public MonthlyTicket(String id, double price) {
        super(id, price);
        this.validMonth = YearMonth.now(); // Lấy tháng/năm hiện tại theo giờ hệ thống
    }

    /**
     * Kiểm tra vé có hợp lệ không
     * Logic:
     * 1. Trạng thái vé phải là ACTIVE (Chưa bị hủy)
     * 2. Thời gian sử dụng (time) phải thuộc cùng Tháng và Năm với validMonth
     */
    @Override
    public boolean isValid(LocalDateTime time, Station station) {
        // 1. Check trạng thái
        if (this.state != TicketState.ACTIVE) {
            return false;
        }

        // 2. Check thời gian (Dùng Java 8 API)
        // Chuyển đổi thời gian quẹt thẻ (LocalDateTime) sang YearMonth
        YearMonth checkMonth = YearMonth.from(time);
        
        // So sánh: Nếu trùng khớp thì trả về true
        return checkMonth.equals(this.validMonth);
    }

    // --- Getters & Setters ---

    public YearMonth getValidMonth() {
        return validMonth;
    }

    public void setValidMonth(YearMonth validMonth) {
        this.validMonth = validMonth;
    }
}