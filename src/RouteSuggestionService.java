import  java.util.Arrays;
import java.util.List;
public class RouteSuggestionService{
    private List<Route> routes;
    public RouteSuggestionService(List<Route> routes, BusPathService busService, MetroPathc){
        this.routes = routes;
    }
    public List<JourneyPlan> suggestRoute(Station start, Station end){
}
}