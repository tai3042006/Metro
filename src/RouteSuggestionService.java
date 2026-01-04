import java.util.ArrayList;
import java.util.List;

public class RouteSuggestionService {
    
    public RouteSuggestionService() {}

    
    public List<Route> suggestRoutes(Station start, Station end, List<Route> allRoutes) {
        List<Route> results = new ArrayList<>();
        if (allRoutes == null) return results;

        for (Route route : allRoutes) {
            // Tuyến nào đi qua cả điểm ĐI và điểm ĐẾN thì gợi ý
            if (routeHasStations(route, start, end)) {
                results.add(route);
            }
        }
        return results;
    }

    private boolean routeHasStations(Route r, Station start, Station end) {
        if (r.getRouteParts() == null) return false;
        boolean hasStart = false, hasEnd = false;

        for (RoutePart part : r.getRouteParts()) {
            // Kiểm tra trạm đi
            if (part.getStartStation().getId().equals(start.getId()) || 
                part.getEndStation().getId().equals(start.getId())) hasStart = true;
            // Kiểm tra trạm đến
            if (part.getStartStation().getId().equals(end.getId()) || 
                part.getEndStation().getId().equals(end.getId())) hasEnd = true;
        }
        return hasStart && hasEnd;
    }
}