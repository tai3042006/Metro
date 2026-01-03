public abstract class Vehicle {
    protected String id;
    protected boolean status; 

    public Vehicle(String id, boolean status) {
        this.id = id;
        this.status = status;
    }
    public String getId() { return id; }
}