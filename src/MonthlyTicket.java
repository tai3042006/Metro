import java.time.LocalDateTime;
import java.time.YearMonth;


public class MonthlyTicket extends Ticket {

    private YearMonth validMonth;

    public MonthlyTicket() {
        super();
        this.validMonth = YearMonth.now();
    }

   
    public MonthlyTicket(String id, double price) {
        super(id, price);
        this.validMonth = YearMonth.now(); 
    }

    
    @Override
    public boolean isValid(LocalDateTime time, Station station) {
        // 1. Check trạng thái
        if (this.state != TicketState.ACTIVE) {
            return false;
        }

        // 2. Check thời gian 
        
        YearMonth checkMonth = YearMonth.from(time);
        
    
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