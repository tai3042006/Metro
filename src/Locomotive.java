public class Locomotive {
    private String id;
    private String engineType; 

    
    public Locomotive(String id, String engineType) {
        this.id = id;
        this.engineType = engineType;
    }
    
    public String getId() { return id; }
	public String getEngineType() {
    return engineType;
}
}