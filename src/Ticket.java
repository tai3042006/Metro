import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Ticket {

    protected String ticketId;
    protected double price;
    protected TicketState state;
    protected LocalDate issueDate; // Ngày phát hành
    protected List<TicketUsage> usageHistory; // Lưu lịch sử dùng vé

    /**
     * Default constructor
     */
    public Ticket() {
        this.usageHistory = new ArrayList<>();
        this.issueDate = LocalDate.now();
        this.state = TicketState.ACTIVE;
    }

    /**
     * Constructor đầy đủ tham số
     */
    public Ticket(String ticketId, double price) {
        this(); // Gọi constructor mặc định để khởi tạo List và Date
        this.ticketId = ticketId;
        this.price = price;
    }

    /**
     * Phương thức trừu tượng (Abstract Method)
     * Các lớp con (Vé ngày, Vé lượt) BẮT BUỘC phải override hàm này
     */
    public abstract boolean isValid(LocalDateTime time, Station station);

    /**
     * Sử dụng vé
     * @param time Thời gian quẹt thẻ
     * @param station Trạm quẹt thẻ
     */
    public void use(LocalDateTime time, Station station) {
        // 1. Kiểm tra vé có hợp lệ không (Gọi hàm isValid của lớp con)
        if (isValid(time, station)) {
            
            // --- [ĐOẠN ĐÃ SỬA] ---
            // Truyền đối tượng 'station' vào constructor TicketUsage
            // (Lúc trước truyền station.getName() là sai kiểu dữ liệu)
            TicketUsage usage = new TicketUsage(station, time);
            
            this.usageHistory.add(usage);
            System.out.println("Check-in thành công tại trạm: " + station.getName());
        } else {
            System.out.println("Vé không hợp lệ hoặc đã hết hạn!");
        }
    }

    // --- Getters & Setters ---
    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public TicketState getState() { return state; }
    public void setState(TicketState state) { this.state = state; }
    
    public LocalDate getIssueDate() { return issueDate; }
    
    public List<TicketUsage> getUsageHistory() { return usageHistory; }
}