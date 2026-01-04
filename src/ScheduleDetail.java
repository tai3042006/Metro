import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDetail {
   
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Vehicle vehicle;   
    private ScheduleState state;

    // 1. Constructor rỗng
    public ScheduleDetail() {
    }

    // 2. Constructor đầy đủ 
    public ScheduleDetail(LocalDate date, LocalTime timeStart, LocalTime timeEnd, ScheduleState state) {
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.state = state;
    }

   
    public ScheduleDetail(LocalDate date, LocalTime timeStart, LocalTime timeEnd, Vehicle vehicle) {
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.vehicle = vehicle;
        this.state = ScheduleState.RUNNING; 
    }

    // --- GETTER & SETTER ---
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

   
    public LocalTime getTimeStart() {
        return timeStart;
    }
    
   
    public LocalTime getStartTime() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }
    
   
    public LocalTime getEndTime() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ScheduleState getState() {
        return state;
    }

    public void setState(ScheduleState state) {
        this.state = state;
    }

    // Tính khoảng thời gian di chuyển (End - Start)
    public LocalTime getTravelTime() {
        if (timeStart != null && timeEnd != null) {
            // Logic trừ giờ phút đơn giản để hiển thị
            int hour = timeEnd.getHour() - timeStart.getHour();
            int minute = timeEnd.getMinute() - timeStart.getMinute();
            if (minute < 0) {
                minute += 60;
                hour--;
            }
            return LocalTime.of(hour, minute);
        }
        return LocalTime.of(0, 0); 
    }

    // Hàm isRunning check Enum
    public boolean isRunning() {
        return this.state == ScheduleState.RUNNING;
    }
}