import java.time.LocalDate;
import java.time.LocalTime; 
import java.util.ArrayList; 
import java.util.List;      

public class ScheduleDaily {
    private LocalDate date;
    private ScheduleCategory category;
    private boolean isActive; 
    
    
    private List<ScheduleDetail> details; 

    //Constructor
    public ScheduleDaily(LocalDate date, ScheduleCategory category, boolean isActive) {
        this.date = date;
        this.category = category;
        this.isActive = isActive;
        this.details = new ArrayList<>(); 
    }

   

   
    public void addDetail(ScheduleDetail detail) {
        this.details.add(detail);
    }

    // 2. Hàm kiểm tra giờ (Overloading - Nạp chồng phương thức)
   
    public boolean isAvailable(LocalTime time) {
        if (!this.isActive) return false;
        
        // Duyệt qua các chuyến trong ngày
        for (ScheduleDetail d : details) {
            // Giờ khách đến phải nằm trong khoảng tgian chuyến đi 
            // Trạng thái chuyến là RUNNING (giả sử ScheduleDetail có hàm isRunning hoặc getState)
            if (time.isAfter(d.getStartTime()) && 
                time.isBefore(d.getEndTime())) {
                // Có thể check thêm d.getState() == ScheduleState.RUNNING 
                return true;
            }
        }
        return false;
    }

    // --- GETTERS & SETTERS ---

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ScheduleCategory getCategory() {
        return category;
    }

    public void setCategory(ScheduleCategory category) {
        this.category = category;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
 
  
    public boolean isAvailable() {
        return this.isActive;
    }
}