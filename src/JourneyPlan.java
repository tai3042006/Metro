import java.util.*;

public class JourneyPlan {

    private String planId; 
    private Station startStation;
    private Station endStation;
    private List<Route> routes;
    private double totalDistance;
    private double totalTime;

    // 1. Constructor 
    public JourneyPlan() {
        this.planId = UUID.randomUUID().toString();
        this.routes = new ArrayList<>(); 
    }

    // 2.
    // Main gọi: new JourneyPlan("JP01", ktxKhuB, benThanh);
    public JourneyPlan(String planId, Station startStation, Station endStation) {
        this.planId = planId;
        this.startStation = startStation;
        this.endStation = endStation;
        this.routes = new ArrayList<>();
    }

    // 3. Phương thức thêm Route 
    public void addRoute(Route route) {
        if (this.routes == null) {
            this.routes = new ArrayList<>();
        }
        this.routes.add(route);
    }

    // --- GETTERS & SETTERS ---

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    
    public String getId() {
        return planId;
    }

    public String getPlanId() {
        return planId;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    // --- TÍNH TOÁN (LOGIC) ---

    public double calculateTotalDistance() {
        double distance = 0.0;
        if (routes != null) {
            for (Route route : routes) {
                
                distance += route.getTotalDistance_Km(); 
            }
        }
        this.totalDistance = distance;
        return distance;
    }

    public double calculateTotalTime() {
        double time = 0.0;
        if (routes != null) {
            for (Route route : routes) {
              
                time += route.getTotalTravelTime();
            }
        }
        this.totalTime = time;
        return time;
    }

    public boolean hasTransfer() {
        return routes != null && routes.size() > 1;
    }

    @Override
    public String toString() {
        return "JourneyPlan [ID=" + planId + ", From=" + startStation.getName() + 
               ", To=" + endStation.getName() + ", Routes=" + (routes != null ? routes.size() : 0) + "]";
    }
}