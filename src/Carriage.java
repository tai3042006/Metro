public class Carriage {
    private String id;
    private int numberOfSeats;
    private int availableSeats;

    public Carriage(String id, int numberOfSeats) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.availableSeats = numberOfSeats; // Mặc định lúc mới tạo xe thì số ghế trống = tổng số ghế
    }

    // phương thức lấy tổng số ghế
    public int getTotalSeats() {
        return this.numberOfSeats;
    }
    
    // Getter id
    public String getId() { 
        return id; 
    }

    // Getter/Setter cho availableSeats )
    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}