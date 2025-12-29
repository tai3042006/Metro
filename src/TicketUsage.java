import java.time.LocalDateTime;

public class TicketUsage {

    private String usageId;
    private LocalDateTime usedTime;
    private Station usedStation; 

   
    public TicketUsage() {
    }

    
    public TicketUsage(Station usedStation, LocalDateTime usedTime) {
        // Tự sinh ID ngẫu nhiên dựa trên thời gian thực
        this.usageId = "USAGE_" + System.nanoTime(); 
        this.usedStation = usedStation;
        this.usedTime = usedTime;
    }

    // --- Getters & Setters ---

    public String getUsageId() {
        return usageId;
    }

    public void setUsageId(String usageId) {
        this.usageId = usageId;
    }

    public LocalDateTime getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(LocalDateTime usedTime) {
        this.usedTime = usedTime;
    }

    public Station getUsedStation() {
        return usedStation;
    }

    public void setUsedStation(Station usedStation) {
        this.usedStation = usedStation;
    }

    @Override
    public String toString() {
        return "TicketUsage{" +
                "id='" + usageId + '\'' +
                ", time=" + usedTime +
                ", station=" + (usedStation != null ? usedStation.getName() : "null") +
                '}';
    }
}