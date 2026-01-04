import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID; 

public abstract class Ticket {

   
    protected String id;
    protected double price;
    protected TicketState state;
    protected LocalDate issueDate; 
    protected List<TicketUsage> usageHistory; 

    public Ticket() {
        this.usageHistory = new ArrayList<>();
        this.issueDate = LocalDate.now();
        this.state = TicketState.ACTIVE;
    }

    public Ticket(String id, double price) {
        this(); 
        this.id = id; 
        this.price = price;
    }

    public abstract boolean isValid(LocalDateTime time, Station station);

   
    public void use(LocalDateTime time, Station station) {
        // 1. Kiểm tra vé có hợp lệ không
        if (isValid(time, station)) {


            // ID ngẫu nhiên cho lượt dùng này
            String usageId = UUID.randomUUID().toString();
            
            // new TicketUsage(String id, LocalDateTime time, Station station)
            TicketUsage usage = new TicketUsage(usageId, time, station);
            
            this.usageHistory.add(usage);
            System.out.println("Check-in thành công tại trạm: " + station.getName());
        } else {
            System.out.println("Vé không hợp lệ hoặc đã hết hạn!");
        }
    }

    // --- Getters & Setters ---
    
   
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public TicketState getState() { return state; }
    public void setState(TicketState state) { this.state = state; }
    
    public LocalDate getIssueDate() { return issueDate; }
    
    public List<TicketUsage> getUsageHistory() { return usageHistory; }
}