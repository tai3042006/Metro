public class RoutePart {
    
    private Station startStation;
    private Station endStation;
    private double travelTime; // minutes
    private double distance;   // km

  
    public RoutePart(Station startStation, Station endStation, double distance, double travelTime) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.distance = distance;
        this.travelTime = travelTime;
    }

    // --- GETTERS  ---

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }
}