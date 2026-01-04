import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTicket extends Ticket {

    private LocalDate validDate;

    public DailyTicket() {
        super();
        this.validDate = LocalDate.now();
    }

    
    public DailyTicket(String id, double price) {
        super(id, price);
        this.validDate = LocalDate.now(); 
    }

    @Override
    public boolean isValid(LocalDateTime time, Station station) {
        
        if (this.state != TicketState.ACTIVE) {
            return false;
        }

        // Kiểm tra ngày: 
        LocalDate usageDate = time.toLocalDate();
        return usageDate.isEqual(this.validDate);
    }

    // --- Getters & Setters ---

    public LocalDate getValidDate() {
        return validDate;
    }

    public void setValidDate(LocalDate validDate) {
        this.validDate = validDate;
    }
}