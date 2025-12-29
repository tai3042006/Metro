import java.util.ArrayList;
import java.util.List;

public class BusPathServiceImpl implements BusPathService {

    private List<Route> routes;
    private MetroPathService metroService;

    public BusPathServiceImpl(MetroPathService metroService, List<Route> routes) {
        this.metroService = metroService;
        this.routes = routes;
    }
//only bus
    @Override
    public JourneyPlan findBusJourney(Station start, Station end) {

        for (Route route : routes) {

            if (route.getType() != TransportType.BUS) continue;

            List<Station> stations = route.getOrderStationList();
            int st = stations.indexOf(start);
            int en = stations.indexOf(end);

            if (st >= 0 && en >= 0 && st < en) {

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

//bus then metro and bus
    @Override
    public JourneyPlan findBusMetroBusJourney(Station start, Station end) {

        for (Route bus1 : routes) {

            if (bus1.getType() != TransportType.BUS) continue;

            List<Station> busStations = bus1.getOrderStationList();
            int startIndex = busStations.indexOf(start);
            if (startIndex < 0) continue;

            for (int i = startIndex + 1; i < busStations.size(); i++) {

                Station transfer = busStations.get(i);
                JourneyPlan metroPlan =
                        metroService.findJourney(transfer, end);

                if (metroPlan != null) {

                    JourneyPlan plan = new JourneyPlan();
                    plan.setStartStation(start);
                    plan.setEndStation(end);

                    List<Route> planRoutes = new ArrayList<>();
                    planRoutes.add(bus1);
                    planRoutes.addAll(metroPlan.getRoutes());

                    plan.setRoutes(planRoutes);

                    plan.calculateTotalDistance();
                    plan.calculateTotalTime();

                    return plan;
                }
            }
        }
        return null;
    }
}
