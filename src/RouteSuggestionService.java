import java.util.ArrayList;
import java.util.List;

public class RouteSuggestionService {

    private List<Route> routes;
    private BusPathService busService;
    private MetroPathService metroService;

    public RouteSuggestionService(
            List<Route> routes,
            BusPathService busService,
            MetroPathService metroService) {

        this.routes = routes;
        this.busService = busService;
        this.metroService = metroService;
    }

    public List<JourneyPlan> suggestRoute(Station start, Station end) {
        List<JourneyPlan> result = new ArrayList<>();
        JourneyPlan metroPlan = metroService.findJourney(start, end);
        if (metroPlan != null) {
            result.add(metroPlan);
        }
        JourneyPlan mixedPlan =
                busService.findBusMetroBusJourney(start, end);
        if (mixedPlan != null) {
            result.add(mixedPlan);
        }

        return result;
    }
}
