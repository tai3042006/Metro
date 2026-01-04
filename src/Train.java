import java.util.LinkedList;
import java.util.List;


public class Train extends Vehicle {

    private String name;
   
    private Locomotive locomotive;
    private List<Carriage> carriageList;

    public Train() {
        super("", true); 
        this.carriageList = new LinkedList<>();
    }

   
    public Train(String name, boolean active, Locomotive locomotive) {
      
        // Giả sử name cũng là ID của tàu
        super(name, active); 
        
        this.name = name;
        this.locomotive = locomotive;
        this.carriageList = new LinkedList<>();
    }

    // --- GETTERS & SETTERS ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
    
    public boolean isActive() {
        return this.status; 
    }

    public void setActive(boolean active) {
        this.status = active;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public List<Carriage> getCarriageList() {
        return carriageList;
    }

    public void addCarriage(Carriage carriage) {
        this.carriageList.add(carriage);
    }

   
    public int getTotalCapacity() {
        return carriageList.stream()
                .mapToInt(Carriage::getTotalSeats) 
                .sum();
    }
}