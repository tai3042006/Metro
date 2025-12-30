@ -1,59 +1,63 @@
import java.util.LinkedList;
import java.util.List;

public class Train {

    private String name;
    private boolean active;
    private Locomotive locomotive;
    private List<Carriage> carriageList;

    public Train() {
        this.carriageList = new LinkedList<>();
        this.active = true;
    }

    public Train(String name, boolean active, Locomotive locomotive) {
        this.name = name;
        this.active = active;
        this.locomotive = locomotive;
        this.carriageList = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        int total = 0;
        for (Carriage c : carriageList) {
            total += c.getCapacity();
        }
        return total;
    }
}