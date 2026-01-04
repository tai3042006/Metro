public class Station {
    private String id;
    private String name;

    // Constructor 
    public Station() {}

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    
    @Override
    public String toString() { return this.name; }
}