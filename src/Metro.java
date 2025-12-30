import java.util.*;
import java.time.LocalDateTime;

public class Metro {

    public static void main(String[] args) {

        System.out.println("===== METRO SYSTEM DEMO =====");

        // =================================================
        // 1. TẠO STATION (FIX CONSTRUCTOR)
        // =================================================
        Station A = new Station("Bến Thành");
        Station B = new Station("Suối Tiên");
        Station C = new Station("Thảo Điền");
        Station D = new Station("ĐHQG");
        Station E = new Station("Bình Thái");

        // =================================================
        // 2. TẠO ROUTE PART
        // =================================================
        RoutePart ab = new RoutePart(A, B, 15, 10.5);
        RoutePart bc = new RoutePart(B, C, 12, 8.0);
        RoutePart cd = new RoutePart(C, D, 10, 6.0);
        RoutePart de = new RoutePart(D, E, 8, 4.0);

        // =================================================
        // 3. TẠO ROUTE
        // =================================================
        Route metroLine1 = new Route(
                "M1",
                "Metro Line 1",
                new LinkedList<>(List.of(ab, bc, cd, de)),
                TransportType.METRO
        );

        Route bus1 = new Route(
                "B1",
                "Bus Bến Thành → Suối Tiên",
                new LinkedList<>(List.of(ab)),
                TransportType.BUS
        );

        Route bus2 = new Route(
                "B2",
                "Bus ĐHQG → Bình Thái",
                new LinkedList<>(List.of(de)),
                TransportType.BUS
        );

        List<Route> allRoutes = List.of(metroLine1, bus1, bus2);

        // =================================================
        // 4. KHỞI TẠO SERVICE
        // =================================================
        MetroPathService metroService =
                new MetroPathServiceImpl(allRoutes);

        BusPathService busService =
                new BusPathServiceImpl(metroService, allRoutes);

        RouteSuggestionService suggestionService =
                new RouteSuggestionService(allRoutes, busService, metroService);

        // =================================================
        // 5. TEST METRO / BUS
        // =================================================
        System.out.println("\n--- TEST 1: METRO A → E ---");
        System.out.println(metroService.findJourney(A, E));

        System.out.println("\n--- TEST 2: BUS → METRO → BUS A → E ---");
        System.out.println(busService.findBusMetroBusJourney(A, E));

        System.out.println("\n--- TEST 3: ROUTE SUGGESTION ---");
        for (JourneyPlan plan : suggestionService.suggestRoute(A, E)) {
            System.out.println(plan);
        }

        // =================================================
        // 6. TEST KHÁCH HỌC SINH (STUDENT)
        // =================================================
        System.out.println("\n===== TEST CUSTOMER: STUDENT =====");

        Customer student = new Customer("CUS01", "Nguyễn Văn A", CustomerType.STUDENT);
        student.setStudentCard(new StudentCard("SV01", "Nguyễn Văn A"));
        student.topUpBalance(200_000);

        TicketService ticketService = new TicketService();

        Order stuOrder1 = new Order("ORD_STU_1", student);
        ticketService.issueSingleRideTicket(stuOrder1);

        Order stuOrder2 = new Order("ORD_STU_2", student);
        ticketService.issueDailyTicket(stuOrder2);

        Order stuOrder3 = new Order("ORD_STU_3", student);
        ticketService.issueMonthlyTicket(stuOrder3);

        // =================================================
        // 7. TEST KHÁCH WORKER (NGƯỜI ĐI LÀM)
        // =================================================
        System.out.println("\n===== TEST CUSTOMER: WORKER =====");

        Customer worker = new Customer("", "Võ Bá Minh Quân", CustomerType.WORKER);
        worker.topUpBalance(500_000);

        Order wkOrder1 = new Order("ORD_WK_1", worker);
        ticketService.issueSingleRideTicket(wkOrder1);

        Order wkOrder2 = new Order("ORD_WK_2", worker);
        ticketService.issueDailyTicket(wkOrder2);

        Order wkOrder3 = new Order("ORD_WK_3", worker);
        ticketService.issueMonthlyTicket(wkOrder3);

        // =================================================
        // 8. CHECK-IN DEMO
        // =================================================
        System.out.println("\n--- CHECK-IN DEMO ---");
        SingleRideTicket demoTicket =
                ticketService.issueSingleRideTicket(new Order("ORD_CHECK", student));

        ticketService.validateTicket(
                demoTicket,
                LocalDateTime.now(),
                A
        );

        System.out.println("\n===== DEMO FINISHED =====");
    }
}
