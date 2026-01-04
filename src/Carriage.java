public class Carriage {
    private String id;
    private int numberOfSeats;
    private int totalSeats;
   
    private int availableSeats; 

    public Carriage(String id, int numberOfSeats, int totalSeats) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.totalSeats = totalSeats;
    
        this.availableSeats = totalSeats; 
    }

    
    public int getTotalSeats() {
        return this.totalSeats; 
    }
    
    // Getter id
    public String getId() { 
        return id; 
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    // Getter/Setter cho availableSeats 
    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}