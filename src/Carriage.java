
public class Carriage {

    private int capacity;        
    private int currentLoad;     

    public Carriage(int capacity) {
        this.capacity = capacity;
        this.currentLoad = 0;
    }

    public boolean hasSpace() {
        return currentLoad < capacity;
    }

    public void enterPassenger() {
        if (!hasSpace()) {
            throw new IllegalStateException("Carriage is full");
        }
        currentLoad++;
    }

    public void exitPassenger() {
        if (currentLoad > 0) {
            currentLoad--;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return
     */
    public int totalSeats() {
        // TODO implement here
        return 0;
    }

}