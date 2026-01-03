import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Metro {

    // Gia lap Co so du lieu cac tuyen duong toan thanh pho
    static List<Route> cityRoutes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   HE THONG GIAO THONG CONG CONG THONG MINH (METRO & BUS)");
        System.out.println("   Mo phong mang luoi: Ben Thanh - Lang DHQG");
        System.out.println("   Sinh vien thuc hien: Vo Ba Minh Quan & Tran Nguyen Anh Tai");
        System.out.println("==========================================================================\n");

        // =======================================================================
        // PHAN 1: KHOI TAO HA TANG MANG LUOI (INFRASTRUCTURE SETUP)
        // =======================================================================
        System.out.println("--- [1. SETUP] KHOI TAO BAN DO TRAM & TUYEN XE ---");

        // 1. KHOI TAO CAC NHA GA & TRAM XE BUYT (Location Nodes)
        Station benThanh = new Station("S01", "Ga Ben Thanh (Q1)");
        Station hangXanh = new Station("S02", "Nga 4 Hang Xanh");
        Station suoiTien = new Station("S03", "Ga Suoi Tien");
        Station dhQuocGia = new Station("B01", "Tram DH Quoc Gia");
        Station ktxKhuA   = new Station("B02", "KTX Khu A");
        Station ktxKhuB   = new Station("B03", "KTX Khu B");

        // 2. KHOI TAO PHUONG TIEN (Vehicles)
        Train trainM1 = new Train("TR_M1", true, new Locomotive("L1", "Electric"));
        Bus bus19 = new Bus("BS_19", "51B-1919", 60);
        Bus bus53 = new Bus("BS_53", "51B-5353", 50);
        Bus bus33 = new Bus("BS_33", "51B-3333", 80);

        // 3. THIET LAP CAC TUYEN DUONG (ROUTES)
        
        // --- TUYEN 1: METRO LINE 1 (Ben Thanh <-> Suoi Tien) ---
        Route metroLine1 = new Route("R_METRO_01", "Metro Line 1: Ben Thanh - Suoi Tien", TransportType.METRO);
        metroLine1.addRoutePart(new RoutePart(benThanh, hangXanh, 10, 5));
        metroLine1.addRoutePart(new RoutePart(hangXanh, suoiTien, 15, 12));
        cityRoutes.add(metroLine1);

        // --- TUYEN 2: BUS 19 (Ben Thanh <-> KTX Khu B) ---
        Route route19 = new Route("R_BUS_19", "Bus 19: Ben Thanh - KTX Khu B", TransportType.BUS);
        route19.addRoutePart(new RoutePart(benThanh, hangXanh, 20, 5));
        route19.addRoutePart(new RoutePart(hangXanh, dhQuocGia, 30, 15));
        route19.addRoutePart(new RoutePart(dhQuocGia, ktxKhuB, 5, 2));
        cityRoutes.add(route19);

        // --- TUYEN 3: BUS 53 (Le Hong Phong <-> KTX Khu A) ---
        // Gia lap doan Hang Xanh -> KTX A
        Route route53 = new Route("R_BUS_53", "Bus 53: Trung tam - KTX Khu A", TransportType.BUS);
        route53.addRoutePart(new RoutePart(hangXanh, suoiTien, 40, 12)); // Bus chay cham hon Metro
        route53.addRoutePart(new RoutePart(suoiTien, ktxKhuA, 10, 3));
        cityRoutes.add(route53);
        
        // --- TUYEN 4: BUS 33 (Suoi Tien <-> DH Quoc Gia - Shuttle Bus) ---
        Route route33 = new Route("R_BUS_33", "Bus 33: Shuttle Lang DH", TransportType.BUS);
        route33.addRoutePart(new RoutePart(suoiTien, dhQuocGia, 15, 4));
        cityRoutes.add(route33);

        System.out.println("-> Da load xong ban do thanh pho: " + cityRoutes.size() + " tuyen xe hoat dong.");
        System.out.println("-> San sang phuc vu hanh khach.\n");


        // =======================================================================
        // PHAN 2: KHOI TAO SERVICE & ACTORS
        // =======================================================================
        TicketService ticketService = new TicketService();
        Revenue revenueManager = new Revenue();
        RouteSuggestionService routeApp = new RouteSuggestionService();

        // --- DIEN VIEN 1: VO BA MINH QUAN (Sinh vien Lang DH) ---
        Customer quan = new Customer("C_QUAN", "Vo Ba Minh Quan", CustomerType.NORMAL);
        quan.setStudentCard(new StudentCard("SV_2024", "Minh Quan")); // Co the SV
        quan.topUpBalance(200000); // Nap 200k

        // --- DIEN VIEN 2: TRAN NGUYEN ANH TAI (Dan van phong Q1) ---
        Customer tai = new Customer("C_TAI", "Tran Nguyen Anh Tai", CustomerType.WORKER);
        tai.topUpBalance(50000); // Nap 50k


        // =======================================================================
        // PHAN 3: KICH BAN KIEM THU THUC TE (REALISTIC SCENARIOS)
        // =======================================================================
        
        // -----------------------------------------------------------------------
        // KICH BAN A: SINH VIEN DI HOC (Bus ket hop Metro)
        // Quan muon di tu KTX Khu A ra tram Metro Suoi Tien de vao trung tam.
        // -----------------------------------------------------------------------
        System.out.println(">>> SCENARIO 1: SINH VIEN QUAN TIM DUONG DI HOC");
        
        // 1. Tim chuyen xe tu KTX A ra Suoi Tien
        System.out.println("   [SEARCH] Tim xe tu: " + ktxKhuA.getName() + " -> " + suoiTien.getName());
        List<Route> suggestedRoutes = routeApp.suggestRoutes(ktxKhuA, suoiTien, cityRoutes);
        
        if (!suggestedRoutes.isEmpty()) {
            Route chosenRoute = suggestedRoutes.get(0); // Lay tuyen dau tien tim duoc
            System.out.println("   [RESULT] He thong goi y: " + chosenRoute.getName() + " (" + chosenRoute.getTransportType() + ")");
            
            // 2. Quan quyet dinh mua Ve Thang de di hoc cho re
            Order orderQuan = new Order("ORD_Q1", quan);
            MonthlyTicket ticketQuan = ticketService.issueMonthlyTicket(orderQuan); // Mua ve thang
            
            // 3. Thanh toan
            Payment payQuan = new Payment("PAY_Q1", orderQuan.getTotalPrice(), "MOMO_QR");
            orderQuan.setPayment(payQuan);
            orderQuan.setStatus(OrderStatus.COMPLETED);
            revenueManager.addRevenue(orderQuan.getCreatedAt(), orderQuan.getTotalPrice());
            
            System.out.println("   [PAYMENT] Quan da mua ve thang. Gia: " + String.format("%,.0f", orderQuan.getTotalPrice()) + " (Da giam 50% SV)");

            // 4. Thuc hien hanh trinh (Check-in)
            System.out.print("   [TRIP 1] Len xe tai KTX A: ");
            ticketQuan.use(LocalDateTime.now(), ktxKhuA); // Quet the len Bus 53

            System.out.print("   [TRIP 2] Xuong tram Suoi Tien & Chuyen sang Metro: ");
            ticketQuan.use(LocalDateTime.now().plusMinutes(20), suoiTien); // Quet the vao ga Metro
            
        } else {
            System.out.println("   [!] Khong tim thay tuyen xe.");
        }


        // -----------------------------------------------------------------------
        // KICH BAN B: NGUOI DI LAM (Di ve luot - Thu cac case loi)
        // Tai di lam tu Ben Thanh ve KTX Khu B bang Bus 19.
        // -----------------------------------------------------------------------
        System.out.println("\n>>> SCENARIO 2: ANH TAI DI LAM & CAC CASE LOI");

        // 1. Tim xe
        System.out.println("   [SEARCH] Tim xe tu: " + benThanh.getName() + " -> " + ktxKhuB.getName());
        List<Route> routesTai = routeApp.suggestRoutes(benThanh, ktxKhuB, cityRoutes);
        
        if (!routesTai.isEmpty()) {
            System.out.println("   [RESULT] He thong goi y: " + routesTai.get(0).getName());
            
            // 2. Tai mua ve LUOT (Single Ride)
            Order orderTai = new Order("ORD_T1", tai);
            SingleRideTicket ticketTai = ticketService.issueSingleRideTicket(orderTai);
            
            orderTai.setPayment(new Payment("PAY_T1", orderTai.getTotalPrice(), "CASH"));
            orderTai.setStatus(OrderStatus.COMPLETED);
            revenueManager.addRevenue(LocalDate.now(), orderTai.getTotalPrice());

            System.out.println("   [PAYMENT] Tai mua ve luot. Gia: " + String.format("%,.0f", orderTai.getTotalPrice()));

            // 3. Di xe
            System.out.print("   [CHECK-IN] Tai Ben Thanh: ");
            ticketTai.use(LocalDateTime.now(), benThanh); // Hop le -> Ve chuyen sang USED

            // --- TEST CASE GIAN LAN ---
            System.out.println("   ... (Tai di den noi, xuong xe di an toi) ...");
            System.out.println("   ... (Tai thu dung lai ve cu de di tiep chuyen khac) ...");
            System.out.print("   [CHECK-IN LAN 2 - GIAN LAN]: ");
            ticketTai.use(LocalDateTime.now().plusHours(2), ktxKhuB); // Mong doi: Bao loi ve da dung
        }


        // -----------------------------------------------------------------------
        // KICH BAN C: KIEM TRA TAI CHINH & BAO CAO
        // -----------------------------------------------------------------------
        System.out.println("\n>>> SCENARIO 3: AUDIT & REPORTING");
        
        // 1. Kiem tra lich su giao dich cua khach hang (Transaction History)
        System.out.println("--- Lich su vi cua Quan (SV) ---");
        for (Transaction t : quan.getTransactionHistory()) {
            System.out.println("   " + t.getDetails());
        }

        System.out.println("--- Lich su vi cua Tai (Worker) ---");
        for (Transaction t : tai.getTransactionHistory()) {
            System.out.println("   " + t.getDetails());
        }

        // 2. Bao cao doanh thu cuoi ngay cho cong ty Metro
        revenueManager.printReport();

        System.out.println("\n==========================================================================");
        System.out.println("   KET THUC MO PHONG - HE THONG HOAT DONG ON DINH");
        System.out.println("==========================================================================");
    }
}