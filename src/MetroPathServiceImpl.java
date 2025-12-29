import  java.util.List;
public class MetroPathServiceImpl implements MetroPathService {
 private List<Route> routes;
 public MetroPathServiceImpl(List<Route> routes){
    this.routes = routes;
 }   

    @Override
    public JourneyPlan findJourney(Station start, Station end) {
       for (Route routes : routes) {
            if(routes.getType() != TransportType.METRO){
                continue;
            }
            List<Station> stations = routes.getOrderStationList();
            int startIndex = stations.indexOf(start);
            int endIndex = stations.indexOf(end);
       }
    }
 
}