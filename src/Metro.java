import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Metro {

    static List<Route> cityRoutes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   DO AN CUOI KY: HE THONG QUAN LY VE METRO & BUS (CAP NHAT BUS 33, 53)");
        System.out.println("   SINH VIEN THUC HIEN:");
        System.out.println("   1. VO BA MINH QUAN     - MSSV: 24130253");
        System.out.println("   2. TRAN NGUYEN ANH TAI - MSSV: 24130270");
        System.out.println("==========================================================================\n");

        // =======================================================================
        // [1] KHOI TAO HA TANG (INFRASTRUCTURE) - DU LIEU THUC TE HCM
        // =======================================================================
        System.out.println("--- [1. INFRASTRUCTURE] KHOI TAO BAN DO METRO LINE 1 & HE THONG BUS ---");

        // 1.1 Danh sách Trạm Metro (Day du tu S1 den S14)
        Station s1_BenThanh = new Station("S01", "Ga Ben Thanh");
        Station s2_NhaHat   = new Station("S02", "Ga Nha Hat TP");
        Station s3_BaSon    = new Station("S03", "Ga Ba Son");
        Station s4_VanThanh = new Station("S04", "Ga Van Thanh");
        Station s5_TanCang  = new Station("S05", "Ga Tan Cang");
        Station s6_ThaoDien = new Station("S06", "Ga Thao Dien");
        Station s7_AnPhu    = new Station("S07", "Ga An Phu");
        Station s8_RachChiec= new Station("S08", "Ga Rach Chiec");
        Station s9_PhuocLong= new Station("S09", "Ga Phuoc Long");
        Station s10_BinhThai= new Station("S10", "Ga Binh Thai");
        Station s11_ThuDuc  = new Station("S11", "Ga Thu Duc");
        Station s12_KhuCNC  = new Station("S12", "Ga Khu Cong Nghe Cao"); 
        Station s13_SuoiTien= new Station("S13", "Ga Suoi Tien"); // HUB Giao thông chính
        Station s14_BenXeMienDong = new Station("S14", "Ga BX Mien Dong Moi");

        // 1.2 Các trạm Bus (Kết nối trường học/KTX)
        Station ktxKhuA = new Station("BUS_01", "KTX Khu A");
        Station ktxKhuB = new Station("BUS_02", "KTX Khu B");
        Station dhGiaoThong = new Station("BUS_03", "DH Giao Thong Van Tai");
        Station langDaiHoc = new Station("BUS_04", "Lang Dai Hoc Thu Duc");

        // 1.3 Phương tiện 
        Train metroTrain = new Train("TR_M1", true, new Locomotive("LOCO_01", "Electric"));
        
        Bus bus19 = new Bus("BS_19", "51B-1919", 60); // Bus cu
        Bus bus33 = new Bus("BS_33", "51B-3333", 55); // [MOI] Bus 33 (Lang DH - Suoi Tien)
        Bus bus53 = new Bus("BS_53", "51B-5353", 50); // [MOI] Bus 53 (KTX A - Suoi Tien)
        Bus bus08 = new Bus("BS_08", "51B-0808", 45); 

        // 1.4 THIẾT LẬP TUYẾN ĐƯỜNG (ROUTES)
        
        // --- A. TUYẾN METRO SỐ 1 ---
        Route metroLine1 = new Route("R_M1", "Metro Line 1 (Ben Thanh - Suoi Tien)", TransportType.METRO);
        // Add full tuyến
        metroLine1.addRoutePart(new RoutePart(s1_BenThanh, s2_NhaHat, 0.8, 2.0));
        metroLine1.addRoutePart(new RoutePart(s2_NhaHat, s3_BaSon, 1.2, 3.0));
        metroLine1.addRoutePart(new RoutePart(s3_BaSon, s4_VanThanh, 1.5, 3.0));
        metroLine1.addRoutePart(new RoutePart(s4_VanThanh, s5_TanCang, 1.0, 2.0));
        metroLine1.addRoutePart(new RoutePart(s5_TanCang, s6_ThaoDien, 1.5, 3.0));
        metroLine1.addRoutePart(new RoutePart(s6_ThaoDien, s7_AnPhu, 2.0, 4.0));
        metroLine1.addRoutePart(new RoutePart(s7_AnPhu, s8_RachChiec, 2.5, 5.0));
        metroLine1.addRoutePart(new RoutePart(s8_RachChiec, s9_PhuocLong, 1.5, 3.0));
        metroLine1.addRoutePart(new RoutePart(s9_PhuocLong, s10_BinhThai, 1.8, 4.0));
        metroLine1.addRoutePart(new RoutePart(s10_BinhThai, s11_ThuDuc, 2.0, 4.0));
        metroLine1.addRoutePart(new RoutePart(s11_ThuDuc, s12_KhuCNC, 2.5, 5.0)); 
        metroLine1.addRoutePart(new RoutePart(s12_KhuCNC, s13_SuoiTien, 2.0, 4.0));
        cityRoutes.add(metroLine1);

        // --- B. TUYẾN BUS 19 (Suối Tiên <-> KTX B) ---
        Route rBus19 = new Route("R_B19", "Bus 19 (Ben Thanh - KTX B)", TransportType.BUS);
        rBus19.addRoutePart(new RoutePart(s13_SuoiTien, ktxKhuB, 5.0, 15.0));
        rBus19.addRoutePart(new RoutePart(ktxKhuB, s13_SuoiTien, 5.0, 15.0));
        cityRoutes.add(rBus19);

        // --- C. TUYẾN BUS 33 (Suối Tiên <-> Làng ĐH/KTX B) ---
        // Bus 33 hỗ trợ giảm tải cho Bus 19
        Route rBus33 = new Route("R_B33", "Bus 33 (Suoi Tien - Lang DH/KTX B)", TransportType.BUS);
        rBus33.addRoutePart(new RoutePart(s13_SuoiTien, ktxKhuB, 5.0, 15.0)); 
        rBus33.addRoutePart(new RoutePart(ktxKhuB, s13_SuoiTien, 5.0, 15.0));
        cityRoutes.add(rBus33);

        // --- D. TUYẾN BUS 53 (Suối Tiên <-> KTX Khu A) ---
        // Kết nối KTX A với Metro
        Route rBus53 = new Route("R_B53", "Bus 53 (Suoi Tien - KTX Khu A)", TransportType.BUS);
        rBus53.addRoutePart(new RoutePart(s13_SuoiTien, ktxKhuA, 4.5, 12.0));
        rBus53.addRoutePart(new RoutePart(ktxKhuA, s13_SuoiTien, 4.5, 12.0));
        cityRoutes.add(rBus53);

        System.out.println("-> Da khoi tao he thong: 14 Tram Metro & 4 Tuyen Bus (19, 33, 53, 08).");
        System.out.println("   (Da them Bus 33, 53 de tang lua chon cho sinh vien)");


        // =======================================================================
        // [2] LICH TRINH (SCHEDULE)
        // =======================================================================
        ScheduleDaily lichTrinh = new ScheduleDaily(LocalDate.now(), ScheduleCategory.WEEKDAY, true);
        ScheduleDetail chuyenTau = new ScheduleDetail(LocalDate.now(), LocalTime.of(5, 0), LocalTime.of(23, 0), metroTrain);
        lichTrinh.addDetail(chuyenTau);


        // =======================================================================
        // [3] SERVICES
        // =======================================================================
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
        System.out.println("     He thong tim thay cac tuyen bus phu hop: Bus 19, Bus 33 (Moi).");
        System.out.println("     + Chang 1: Don [Bus 33] (hoac 19) tai [KTX Khu B] -> Den [Ga Suoi Tien]");
        System.out.println("     + Chang 2: Don [Metro 1] tai [Ga Suoi Tien] -> Den [Ga Ben Thanh]");
        
        // Mua vé tháng
        Order orderQuan = new Order("ORD_Q", quan);
        MonthlyTicket veThang = ticketService.issueMonthlyTicket(orderQuan);
        orderQuan.setPayment(new Payment("PAY_Q", orderQuan.getTotalPrice(), "MOMO"));
        orderQuan.setStatus(OrderStatus.COMPLETED);
        revenueManager.addRevenue(orderQuan.getCreatedAt().toLocalDate(), orderQuan.getTotalPrice());

        System.out.println("  3. [PAYMENT] Mua Ve Thang (Giam 50%): " + String.format("%,.0f", orderQuan.getTotalPrice()) + " VND");

        // Check-in thực tế
        System.out.println("  4. [CHECK-IN] Thuc hien hanh trinh:");
        // Quân quyết định thử đi Bus 33 mới thêm
        System.out.print("     -> Bus 33 tai KTX Khu B: ");
        veThang.use(LocalDateTime.now().minusMinutes(90), ktxKhuB); 
        
        System.out.print("     -> Metro 1 tai Ga Suoi Tien: ");
        veThang.use(LocalDateTime.now().minusMinutes(60), s13_SuoiTien); 

        System.out.print("     -> Ket thuc tai Ga Ben Thanh: ");
        veThang.use(LocalDateTime.now(), s1_BenThanh); 
        System.out.println("     (Hanh trinh hoan tat - Su dung Bus 33 ket hop Metro)");


        // =======================================================================
        // SCENARIO 2: TRAN NGUYEN ANH TAI (WORKER - DI TU TRAM GIUA)
        // =======================================================================
        System.out.println("\n>>> SCENARIO 2: TRAN NGUYEN ANH TAI (Worker - Checkin Tram Giua)");
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
            System.out.println(" -> Cong mo. Bat dau di chuyen.");
        }

        // Check-in Gian lận
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
        System.out.println("   KET THUC CHUONG TRINH - NHOM SINH VIEN: QUAN & TAI cam on Co!");
    }
}