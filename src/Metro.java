public class Metro {
    public static void main(String[] args) {
        Station A = new Station("Bến Thành");
        Station B = new Station("Suối Tiên", null);
        Station C = new Station("Thảo Điền");
        Station D = new Station("Đại học Quốc Gia");
        Station E = new Station("Bình Thái");

        Carriage carriage1 = new Carriage("C1", 100);
        Carriage carriage2 = new Carriage("C2", 120);      
        Locomotive loco1 = new Locomotive("Loco1", "Diesel");
        Train train1 = new Train("Express", true, loco1);
        train1.addCarriage(carriage1);
        train1.addCarriage(carriage2);
        Route route1 = new Route(A, C);
        route1.addRoutePart(new RoutePart(A, B, 10.5, 15));
        route1.addRoutePart(new RoutePart(B, C, 8.0, 12));      
        JourneyPlan plan = new JourneyPlan();
        plan.setStartStation(A);
        plan.setEndStation(C);
        plan.setRoutes(new ArrayList<>(List.of(route1)));
        plan.calculateTotalDistance();
        plan.calculateTotalTime();
        System.out.println(plan);
        
        
    }
}
