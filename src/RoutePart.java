


/**
 * 
 */
public class RoutePart {
    private  Station beginStation;
    private  Station endStation;
    private  double travelTime; // minute
    private  double distance_Km; //km
    public RoutePart(Station beginStation, Station endStation, double travelTime, double distance_Km) {
        this.beginStation = beginStation;
        this.endStation = endStation;
        this.travelTime = travelTime;
        this.distance_Km = distance_Km;
    }
    public Station getBeginStation(){
        return beginStation;
    }
    public Station getEndStation(){
        return endStation;
    }
    public  double getTravelTime() {
    return travelTime;
    }

    public double getDistance_Km() {
        return distance_Km;
    }
    }