import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ScheduleDetail {
   
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Vehicle vehicle;
    private ScheduleState state;

 
    public ScheduleDetail() {
    }

  
    public ScheduleDetail(LocalDate date, LocalTime timeStart, LocalTime timeEnd, ScheduleState state) {
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.state = state;
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

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
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

    // Tính khoảng thời gian di chuyển (End - Start) và trả về dưới dạng LocalTime
    public LocalTime getTravelTime() {
        if (timeStart != null && timeEnd != null) {
            //  lấy giờ kết thúc trừ đi giờ bắt đầu
            return timeEnd.minusHours(timeStart.getHour())
                          .minusMinutes(timeStart.getMinute());
        }
        return LocalTime.of(0, 0); // Trả về 00:00 nếu chưa có dữ liệu
    }

    //  Hàm isRunning
    public boolean isRunning() {
        // Kiểm tra trạng thái trong Enum
        return this.state == ScheduleState.RUNNING;
    }
}