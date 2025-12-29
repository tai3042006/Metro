import java.util.*;

public class JourneyPlan {

    private final String planId;
    private Station startStation;
    private Station endStation;
    private List<Route> routes;
    private double totalDistance;
    private double totalTime;

    public JourneyPlan() {
        this.planId = UUID.randomUUID().toString();
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getPlanId() {
        return planId;
    }

    public List<Route> getRoutes() {
        return routes;
    }
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
        return "JourneyPlan:\n" +
               "from: " + startStation + "\n" +
               "to: " + endStation + "\n" +
               "distance: " + totalDistance + "\n" +
               "time: " + totalTime;
    }
}
