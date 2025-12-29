import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleRideTicket extends Ticket { // [QUAN TRỌNG] Phải kế thừa Ticket

    private LocalDate startDate;
    private LocalDate endDate;
    private Station originStation;      // Trạm đi (Tuỳ chọn)
    private Station destinationStation; // Trạm đến (Tuỳ chọn)

    /**
     * Default constructor
     */
    public SingleRideTicket() {
        super();
    }

    /**
     * Constructor đầy đủ (Dùng khi phát hành vé)
     * Mặc định vé lượt có hiệu lực trong ngày mua (hoặc 24h)
     */
    public SingleRideTicket(String id, double price, Station origin, Station destination) {
        super(id, price);
        this.originStation = origin;
        this.destinationStation = destination;
        
        // Giả sử vé lượt có giá trị trong 1 ngày kể từ ngày mua
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now(); 
    }

    // Constructor đơn giản (để tương thích với code cũ nếu không cần trạm)
    public SingleRideTicket(String id, double price) {
        this(id, price, null, null);
    }

    /**
     * Kiểm tra vé có hợp lệ không
     * Logic: 
     * 1. Trạng thái phải là ACTIVE
     * 2. Thời gian sử dụng phải nằm trong khoảng startDate và endDate
     */
    @Override
    public boolean isValid(LocalDateTime time, Station station) {
        LocalDate checkDate = time.toLocalDate();

        boolean isActive = (this.state == TicketState.ACTIVE);
        
        // Kiểm tra ngày: startDate <= checkDate <= endDate
        boolean isAfterStart = checkDate.isEqual(startDate) || checkDate.isAfter(startDate);
        boolean isBeforeEnd = checkDate.isEqual(endDate) || checkDate.isBefore(endDate);

        return isActive && isAfterStart && isBeforeEnd;
    }

    /**
     * Ghi đè hàm sử dụng vé
     * Đặc điểm vé lượt: Dùng 1 lần là HẾT (chuyển sang USED)
     */
    @Override
    public void use(LocalDateTime time, Station station) {
        super.use(time, station); // Ghi lịch sử

        // Nếu vé đang Active -> Chuyển sang Used (Đã dùng)
        if (this.state == TicketState.ACTIVE) {
            this.state = TicketState.USED;
            System.out.println("-> Vé lượt đã được gạch soát (Đổi trạng thái sang USED).");
        }
    }

    // --- Getters & Setters ---

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Station getOriginStation() { return originStation; }
    public void setOriginStation(Station originStation) { this.originStation = originStation; }

    public Station getDestinationStation() { return destinationStation; }
    public void setDestinationStation(Station destinationStation) { this.destinationStation = destinationStation; }
}