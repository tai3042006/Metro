import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleRideTicket extends Ticket { 

    private LocalDate startDate;
    private LocalDate endDate;
    private Station originStation;      // Trạm đi 
    private Station destinationStation; // Trạm đến 

    
    public SingleRideTicket() {
        super();
    }

    
    public SingleRideTicket(String id, double price, Station origin, Station destination) {
        super(id, price);
        this.originStation = origin;
        this.destinationStation = destination;
        
       
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now(); 
    }

    
    public SingleRideTicket(String id, double price) {
        this(id, price, null, null);
    }

    
    @Override
    public boolean isValid(LocalDateTime time, Station station) {
        LocalDate checkDate = time.toLocalDate();

        boolean isActive = (this.state == TicketState.ACTIVE);
        
        // Kiểm tra ngày: startDate <= checkDate <= endDate
        boolean isAfterStart = checkDate.isEqual(startDate) || checkDate.isAfter(startDate);
        boolean isBeforeEnd = checkDate.isEqual(endDate) || checkDate.isBefore(endDate);

        return isActive && isAfterStart && isBeforeEnd;
    }

    @Override
    public void use(LocalDateTime time, Station station) {
        super.use(time, station); // Ghi lịch sử

        // Nếu vé đang Active -> Chuyển sang Used 
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