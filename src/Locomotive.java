

/**
 * 
 */
public class Locomotive {

    private String id;
    private boolean status;
    private double speed;
    private Integer year;
    public Locomotive(String id, boolean status, double speed, Integer year) {
        this.id = id;
        this.status = status;
        this.speed = speed;
        this.year = year;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

    
}
