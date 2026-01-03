public class Bus extends Vehicle {
    private String licensePlate;
    private int capacity;

    
    public Bus(String id, String licensePlate, int capacity) {
        super(id, true); 
        this.licensePlate = licensePlate;
        this.capacity = capacity;
    }

    public String getLicensePlate() { return licensePlate; }
    public int getCapacity() { return capacity; }
}