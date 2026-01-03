import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Route {

    private String id;
    private String name;
    private LinkedList<RoutePart> routePartList; 
    private TransportType transportType;

    
    public Route(String id, String name, TransportType transportType) {
        this.id = id;
        this.name = name;
        this.transportType = transportType;
        this.routePartList = new LinkedList<>(); // Khởi tạo list rỗng
    }

   
    public void addRoutePart(RoutePart part) {
        this.routePartList.add(part);
    }

   
    public void removeRoutePart(RoutePart part) {
        this.routePartList.remove(part);
    }

    // --- CÁC PHƯƠNG THỨC TÍNH TOÁN (Dùng Java 8) ---

   
    public double getTotalDistance_Km() {
        // Dùng Stream API để cộng dồn distance
        return routePartList.stream()
                .mapToDouble(RoutePart::getDistance)
                .sum();
    }

   
    public double getTotalTravelTime() {
        // Dùng Stream API để cộng dồn thời gian
        return routePartList.stream()
                .mapToDouble(RoutePart::getTravelTime)
                .sum();
    }

   
   
    public TransportType getTransportType() {
        return transportType;
    }
    
 
    public TransportType getType() {
        return getTransportType();
    }

    
    // Phương thức này trả về danh sách các trạm đi qua theo thứ tự
    public List<Station> getOrderStationList() {
        List<Station> stations = new ArrayList<>();
        if (routePartList.isEmpty()) return stations;

        // Thêm trạm đầu tiên của chặng đầu
        stations.add(routePartList.getFirst().getStartStation());

      
        List<Station> endStations = routePartList.stream()
                .map(RoutePart::getEndStation)
                .collect(Collectors.toList());
        
        stations.addAll(endStations);
        return stations;
    }

    // Getter cơ bản khác
    public String getId() { return id; }
    public String getName() { return name; }
    public LinkedList<RoutePart> getRouteParts() {
    return this.routePartList;
}
   
    public Station getBeginStation() {
        return routePartList.isEmpty() ? null : routePartList.getFirst().getStartStation();
    }
    
  
    public Station getEndStation() {
        return routePartList.isEmpty() ? null : routePartList.getLast().getEndStation();
    }
}