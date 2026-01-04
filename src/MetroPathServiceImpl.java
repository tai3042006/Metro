import java.util.ArrayList;
import java.util.List;

public class MetroPathServiceImpl implements MetroPathService {

    private List<Route> routes;

    public MetroPathServiceImpl(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public JourneyPlan findJourney(Station start, Station end) {

        for (Route route : routes) {

          
            if (route.getType() != TransportType.METRO) {
                continue;
            }

            List<Station> stations = route.getOrderStationList();
            int startIndex = stations.indexOf(start);
            int endIndex = stations.indexOf(end);

          
            if (startIndex >= 0 && endIndex >= 0 && startIndex < endIndex) {

                JourneyPlan plan = new JourneyPlan();
                plan.setStartStation(start);
                plan.setEndStation(end);

                
                plan.setRoutes(new ArrayList<>(List.of(route)));

                plan.calculateTotalDistance();
                plan.calculateTotalTime();

                return plan;
            }
        }
       
        return null;
    }
}
