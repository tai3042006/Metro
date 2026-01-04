import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Metro {

    static List<Route> cityRoutes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   DO AN CUOI KY: HE THONG QUAN LY VE METRO & BUS ");
        System.out.println("   SINH VIEN THUC HIEN:");
        System.out.println("   1. VO BA MINH QUAN     - MSSV: 24130253");
        System.out.println("   2. TRAN NGUYEN ANH TAI - MSSV: 24130270");
        System.out.println("==========================================================================\n");

        // =======================================================================
        // [1] (INFRASTRUCTURE)
        // =======================================================================
        System.out.println("--- [1. INFRASTRUCTURE] KHOI TAO HA TANG ---");

        // 1.1 Danh sách Trạm Metro & Bus
        Station s1_BenThanh = new Station("S01", "Ga Ben Thanh");
        Station s2_NhaHat   = new Station("S02", "Ga Nha Hat TP");
        Station s3_BaSon    = new Station("S03", "Ga Ba Son");
        Station s5_TanCang  = new Station("S05", "Ga Tan Cang");
        Station s11_ThuDuc  = new Station("S11", "Ga Thu Duc");
        Station s12_KhuCNC  = new Station("S12", "Ga Khu Cong Nghe Cao"); 
        Station s13_SuoiTien= new Station("S13", "Ga Suoi Tien"); 
        
        Station ktxKhuA = new Station("BUS_01", "KTX Khu A");
        Station ktxKhuB = new Station("BUS_02", "KTX Khu B");

        // 1.2 Phương tiện
        Train metroTrain = new Train("TR_M1", true, new Locomotive("LOCO_01", "Electric"));
        
        Bus bus19 = new Bus("BS_19", "51B-1919", 60); 
        Bus bus33 = new Bus("BS_33", "51B-3333", 55); 
        Bus bus53 = new Bus("BS_53", "51B-5353", 50); 

        // 1.3 THIẾT LẬP TUYẾN ĐƯỜNG (ROUTES)

        // --- A. TUYẾN METRO SỐ 1 (Ben Thanh - Suoi Tien) ---
        Route metroLine1 = new Route("R_M1", "Metro Line 1 (Ben Thanh - Suoi Tien)", TransportType.METRO);
        metroLine1.addRoutePart(new RoutePart(s1_BenThanh, s2_NhaHat, 0.8, 2.0));
        metroLine1.addRoutePart(new RoutePart(s2_NhaHat, s3_BaSon, 1.2, 3.0));
        metroLine1.addRoutePart(new RoutePart(s3_BaSon, s5_TanCang, 2.5, 5.0)); // Gộp đoạn giữa
        metroLine1.addRoutePart(new RoutePart(s5_TanCang, s11_ThuDuc, 6.0, 10.0));
        metroLine1.addRoutePart(new RoutePart(s11_ThuDuc, s12_KhuCNC, 2.5, 5.0)); 
        metroLine1.addRoutePart(new RoutePart(s12_KhuCNC, s13_SuoiTien, 2.0, 4.0));
        cityRoutes.add(metroLine1);

        // --- B. TUYẾN BUS 33 (Suối Tiên <-> KTX B) ---
        Route rBus33 = new Route("R_B33", "Bus 33 (Suoi Tien - KTX B)", TransportType.BUS);
        rBus33.addRoutePart(new RoutePart(s13_SuoiTien, ktxKhuB, 5.0, 15.0)); 
        rBus33.addRoutePart(new RoutePart(ktxKhuB, s13_SuoiTien, 5.0, 15.0));
        cityRoutes.add(rBus33);

        // --- C. TUYẾN BUS 53 (Suối Tiên <-> KTX Khu A) ---
        Route rBus53 = new Route("R_B53", "Bus 53 (Suoi Tien - KTX Khu A)", TransportType.BUS);
        rBus53.addRoutePart(new RoutePart(s13_SuoiTien, ktxKhuA, 4.5, 12.0));
        rBus53.addRoutePart(new RoutePart(ktxKhuA, s13_SuoiTien, 4.5, 12.0));
        cityRoutes.add(rBus53);

        System.out.println("-> Da khoi tao he thong: Metro Line 1 & Bus (19, 33, 53).");

        // =======================================================================
        // [2] SERVICES & SCHEDULE
        // =======================================================================
        ScheduleDaily lichTrinh = new ScheduleDaily(LocalDate.now(), ScheduleCategory.WEEKDAY, true);
        lichTrinh.addDetail(new ScheduleDetail(LocalDate.now(), LocalTime.of(5, 0), LocalTime.of(23, 0), metroTrain));

        TicketService ticketService = new TicketService();
        Revenue revenueManager = new Revenue(); 
        System.out.println("-> Services san sang.\n");


        // =======================================================================
        // SCENARIO 1: VO BA MINH QUAN (SINH VIEN)
        // Lộ trình: KTX Khu B -> Ga Ben Thanh
        // =======================================================================
        System.out.println(">>> SCENARIO 1: SINH VIEN VO BA MINH QUAN (Student - Monthly Ticket)");
        Customer quan = new Customer("24130253", "Vo Ba Minh Quan", CustomerType.NORMAL);
        quan.setStudentCard(new StudentCard("SV_24130253", "Vo Ba Minh Quan"));
        
        System.out.println("  1. [TOP-UP] Nap 200k...");
        quan.topUpBalance(200000);

        System.out.println("  2. [ROUTING] Tim duong tu KTX B -> Ben Thanh:");
        
      
        // Giả lập: Đi Bus 33 rồi chuyển sang Metro
        JourneyPlan journeyOfQuan = new JourneyPlan("JP_QUAN_01", ktxKhuB, s1_BenThanh);
        journeyOfQuan.addRoute(rBus33);     
        journeyOfQuan.addRoute(metroLine1); 

        // In ra số liệu JourneyPlan
        System.out.println("     [SYSTEM CALCULATION] Thong tin lo trinh:");
        System.out.println("     + Tong quang duong: " + journeyOfQuan.calculateTotalDistance() + " km");
        System.out.println("     + Tong thoi gian:   " + journeyOfQuan.calculateTotalTime() + " phut");
        System.out.println("     + Chi tiet chang:");
        System.out.println("       -> Chang 1: " + rBus33.getName());
        System.out.println("       -> Chang 2: " + metroLine1.getName());
        
        // Mua vé tháng
        Order orderQuan = new Order("ORD_Q", quan);
        MonthlyTicket veThang = ticketService.issueMonthlyTicket(orderQuan);
        orderQuan.setPayment(new Payment("PAY_Q", orderQuan.getTotalPrice(), "MOMO"));
        orderQuan.setStatus(OrderStatus.COMPLETED);
        revenueManager.addRevenue(orderQuan.getCreatedAt().toLocalDate(), orderQuan.getTotalPrice());

        System.out.println("  3. [PAYMENT] Mua Ve Thang (Giam 50%): " + String.format("%,.0f", orderQuan.getTotalPrice()) + " VND");

        // Checkin 
        System.out.println("  4. [CHECK-IN] Thuc hien hanh trinh:");
        // Quân đi Bus 33
        System.out.print("     -> Check-in Bus 33 tai KTX Khu B: ");
        veThang.use(LocalDateTime.now().minusMinutes(90), ktxKhuB); 
        
        System.out.print("     -> Check-in Metro 1 tai Ga Suoi Tien: ");
        veThang.use(LocalDateTime.now().minusMinutes(60), s13_SuoiTien); 

        System.out.print("     -> Check-out tai Ga Ben Thanh: ");
        veThang.use(LocalDateTime.now(), s1_BenThanh); 
        System.out.println("     (Hanh trinh hoan tat)");


        // =======================================================================
        // SCENARIO 2: TRAN NGUYEN ANH TAI (WORKER)
        // =======================================================================
        System.out.println("\n>>> SCENARIO 2: TRAN NGUYEN ANH TAI (Worker)");
        System.out.println("    [Goal]: Di tu GA KHU CONG NGHE CAO (S12) -> GA TAN CANG (S05)");

        Customer tai = new Customer("24130270", "Tran Nguyen Anh Tai", CustomerType.WORKER);
        tai.topUpBalance(50000);

        Order orderTai = new Order("ORD_T", tai);
        SingleRideTicket veLuot = ticketService.issueSingleRideTicket(orderTai);
        orderTai.setStatus(OrderStatus.COMPLETED);
        revenueManager.addRevenue(orderTai.getCreatedAt().toLocalDate(), orderTai.getTotalPrice());
        System.out.println("  1. [PAYMENT] Tai mua Ve Luot. Gia: " + String.format("%,.0f", orderTai.getTotalPrice()));

        // Check-in tại Trạm Giữa (S12)
        System.out.print("  2. [CHECK-IN 1] Tai Ga Khu CNC (S12): ");
        if(veLuot.getState() == TicketState.ACTIVE) {
            veLuot.use(LocalDateTime.now().minusMinutes(20), s12_KhuCNC);
            veLuot.setState(TicketState.USED); 
            System.out.println(" -> Bat dau di chuyen.");
        }

        // Checkin Gian lận
        System.out.print("  3. [CHECK-IN 2] Thu dung lai ve cu tai Ga Tan Cang: ");
        if(veLuot.getState() == TicketState.USED) {
            System.out.println(" -> [DENIED] HE THONG TU CHOI! (Ticket Used)");
        } else {
            veLuot.use(LocalDateTime.now(), s5_TanCang);
        }


        // =======================================================================
        // SCENARIO 3: AUDIT & REPORTING
        // =======================================================================
        System.out.println("\n>>> SCENARIO 3: AUDIT & REPORTING");
        
       
        quan.addTransaction(new Transaction("TRX_01", 200000, LocalDateTime.now().minusHours(2), TransactionType.TOP_UP));
        quan.addTransaction(new Transaction("TRX_02", -100000, LocalDateTime.now().minusHours(1), TransactionType.PAYMENT)); 

        System.out.println("  1. [WALLET HISTORY] Lich su vi SV Quan:");
        System.out.println("     + NAP TIEN:   +200,000 VND");
        System.out.println("     + MUA VE:     -100,000 VND");

        System.out.println("  2. [REVENUE REPORT] Tong hop doanh thu:");
        List<Order> allOrders = Arrays.asList(orderQuan, orderTai);
        double totalRev = allOrders.stream().mapToDouble(Order::getTotalPrice).sum();

        TreeMap<LocalDate, Double> map = revenueManager.getRevenueMap(); 
        if (map != null) {
            map.forEach((date, amount) -> 
                System.out.println("     -> Ngay " + date + ": " + String.format("%,.0f", amount) + " VND"));
        }
        System.out.println("     => TONG DOANH THU: " + String.format("%,.0f", totalRev) + " VND");

        Map<String, Long> stats = allOrders.stream()
                .flatMap(o -> o.getListOfTicket().stream())
                .collect(Collectors.groupingBy(t -> t.getClass().getSimpleName(), Collectors.counting()));
        System.out.println("  3. [STATISTICS] Ve ban ra: " + stats);

        System.out.println("==========================================================================");
        System.out.println("   KET THUC CHUONG TRINH - NHOM SINH VIEN: QUAN & TAI Cam On Co ");
    }
}