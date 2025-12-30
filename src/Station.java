import java.util.Objects;
import java.util.Random;

public class Station {

    private String id;
    private String name;

    public Station(String name) {
        this.id = generateRandomId();
        this.name = name;
    }

    private String generateRandomId() {
        Random random = new Random();
        int number = random.nextInt(1000); 
        return String.format("%03d", number); 
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return Objects.equals(id, station.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
