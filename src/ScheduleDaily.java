
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class ScheduleDaily {
    private  LocalDate date;
    private  ScheduleCategory category;
    private boolean isActive;

    public ScheduleDaily(LocalDate date, ScheduleCategory category, boolean isActive) {
        this.date = date;
        this.category = category;
        this.isActive = isActive;
    }

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


}