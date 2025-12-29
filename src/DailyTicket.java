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
        this.validDate = LocalDate.now(); // Gán ngày hiệu lực là hôm nay
    }

    @Override
    public boolean isValid(LocalDateTime time, Station station) {
        // Kiểm tra trạng thái
        if (this.state != TicketState.ACTIVE) {
            return false;
        }

        // Kiểm tra ngày: convert thời gian quẹt thẻ sang LocalDate và so sánh
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