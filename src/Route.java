
import java.util.*;

/**
 * 
 */
public class Route {
    private String id;
    private String name;
    private List<RoutePart> routePartList;
    private TransportType transportType;

Route(String id, String name, LinkedList<RoutePart> routePartList, TransportType TransportType){
    this.id = id;
    this.name = name;
    this.routePartList = new LinkedList<>();
    this.transportType = transportType;
}
    public Station getBeginStation() {
        if(routePartList.isEmpty()) return null;
return routePartList.get(0).getBeginStation();
    }

    /**
     * @return
     */
    public Station getEndStation(){
        if(routePartList.isEmpty()) return null;
        return routePartList.get(routePartList.size()-1).getEndStation();
    }
    public Set<Station> getSetOfStation() {
        // TODO implement here
        Set<Station> stations = new LinkedHashSet<>();
        for (RoutePart rp : routePartList) {
            stations.add(rp.getBeginStation());
            stations.add(rp.getEndStation());
            
        }
        return stations;
    }

    /**
     * @return
     */
    public LinkedList<Station> getOrderStationList() {
        // TODO implement here
        LinkedList<Station> stations = new LinkedList<>();
        if(routePartList.isEmpty()){

         return  stations;
        }
        stations.add(routePartList.get(0).getBeginStation());
        for(RoutePart rp : routePartList){
            stations.add(rp.getEndStation());
        }
        return  stations;
    }

    /**
     * @return
     */
    public void addRoutePart(RoutePart rp) {
        // TODO implement here
        routePartList.add(rp);
    }

    /**
     * @return
     */
    public void removeRoutePart(RoutePart rp) {
        // TODO implement here
        routePartList.remove(rp);
    }

    /**
     * @return
     */
    public double getTotalTravelTime() {
        // TODO implement here
        double total = 0.0;
        for(RoutePart rp : routePartList){
            total += rp.getDistance_Km();
        }
        return  total;
    }

    /**
     * @return
     */
    public double getTotalDistance_Km() {
        // TODO implement here
        double total = 0.0;
        for(RoutePart rp : routePartList){
            total += rp.getDistance_Km();
        }
        return total;
    }

}