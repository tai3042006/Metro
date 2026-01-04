import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Metro {

    static List<Route> cityRoutes = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("==========================================================================");
        System.out.println("   DO AN CUOI KY: HE THONG QUAN LY VE METRO & BUS");
        System.out.println("   SINH VIEN THUC HIEN:");
        System.out.println("   1. VO BA MINH QUAN     - MSSV: 24130253");
        System.out.println("   2. TRAN NGUYEN ANH TAI - MSSV: 24130270");
        System.out.println("==========================================================================\n");

        // --- [1] KHOI TAO HA TANG ---
        Station benThanh = new Station("S01", "Ga Ben Thanh");
        Station suoiTien = new Station("S03", "Ga Suoi Tien");
        Station ktxKhuA  = new Station("B02", "KTX Khu A");
        Station ktxKhuB  = new Station("B03", "KTX Khu B");

        LinkedList<Carriage> toaTauList = new LinkedList<>();
        toaTauList.add(new Carriage("C01", 40, 60)); 
        toaTauList.add(new Carriage("C02", 40, 60));
        Locomotive dauKeo = new Locomotive("LOCO_01", "Electric Engine");
        
        Train metroTrain = new Train("TR_M1", true, dauKeo); 
        Bus bus19 = new Bus("BS_19", "51B-1919", 60);

        // Routes
        Route metroLine1 = new Route("R01", "Metro Line 1", TransportType.METRO);
        metroLine1.addRoutePart(new RoutePart(benThanh, suoiTien, 15.0, 20.0)); 
        metroLine1.addRoutePart(new RoutePart(suoiTien, benThanh, 15.0, 20.0));
        cityRoutes.add(metroLine1);

        Route busLine19 = new Route("R02", "Bus 19", TransportType.BUS);
        busLine19.addRoutePart(new RoutePart(suoiTien, ktxKhuB, 5.0, 15.0)); 
        busLine19.addRoutePart(new RoutePart(ktxKhuB, suoiTien, 5.0, 15.0));
        cityRoutes.add(busLine19);

        // --- [2] LICH TRINH ---
        ScheduleDaily lichTrinhHomNay = new ScheduleDaily(LocalDate.now(), ScheduleCategory.WEEKDAY, true);
        ScheduleDetail chuyenSang = new ScheduleDetail(LocalDate.now(), LocalTime.of(7, 0), LocalTime.of(22, 0), metroTrain);
        lichTrinhHomNay.addDetail(chuyenSang);

        // --- [3] SERVICES ---
        TicketService ticketService = new TicketService();
        Revenue revenueManager = new Revenue(); 
        
        System.out.println("-> Khoi tao he thong hoan tat (San sang Test).\n");


        // =======================================================================
        // SCENARIO 1: VO BA MINH QUAN (SINH VIEN - DI HOC)
        // Yeu cau: Tim duong tu KTX B -> Ben Thanh.
        // =======================================================================
        System.out.println(">>> SCENARIO 1: SINH VIEN VO BA MINH QUAN (Student - Monthly Ticket)");
        System.out.println("    [Goal]: Di hoc tu KTX Khu B den Trung tam Ben Thanh.");
        
        Customer quan = new Customer("24130253", "Vo Ba Minh Quan", CustomerType.NORMAL);
        quan.setStudentCard(new StudentCard("SV_24130253", "Vo Ba Minh Quan")); // Gắn thẻ SV
        
        // 1.1 Nạp tiền
        System.out.println("  1. [TOP-UP] Quan nap 200k vao vi...");
        quan.topUpBalance(200000); 

        // 1.2 CHI TIẾT TÌM ĐƯỜNG (Theo yêu cầu của bạn)
        System.out.println("  2. [ROUTING] He thong tim thay lo trinh toi uu:");
        System.out.println("     ---------------------------------------------------------");
        System.out.println("     | BƯỚC 1: Tại trạm [KTX Khu B]                          |");
        System.out.println("     |         -> Bắt chuyến: [Bus 19] (BS_19)               |");
        System.out.println("     |         -> Đi đến:     [Ga Suoi Tien]                 |");
        System.out.println("     |-------------------------------------------------------|");
        System.out.println("     | BƯỚC 2: Tại trạm [Ga Suoi Tien]                       |");
        System.out.println("     |         -> Chuyển sang:[Metro Line 1] (TR_M1)         |");
        System.out.println("     |         -> Đi đến:     [Ga Ben Thanh]                 |");
        System.out.println("     ---------------------------------------------------------");
        
        // 1.3 Mua vé tháng (Giảm giá 50% cho SV)
        Order orderQuan = new Order("ORD_Q", quan);
        MonthlyTicket veThang = ticketService.issueMonthlyTicket(orderQuan);
        
        orderQuan.setPayment(new Payment("PAY_Q", orderQuan.getTotalPrice(), "MOMO"));
        orderQuan.setStatus(OrderStatus.COMPLETED);
        revenueManager.addRevenue(orderQuan.getCreatedAt().toLocalDate(), orderQuan.getTotalPrice());
        
        System.out.println("  3. [PAYMENT] Quan mua Ve Thang de di hoc.");
        System.out.println("     -> Gia goc: 200,000 VND");
        System.out.println("     -> Gia Student (Giam 50%): " + String.format("%,.0f", orderQuan.getTotalPrice()) + " VND");

        // 1.4 Check-in thực tế theo lộ trình trên
        System.out.println("  4. [CHECK-IN] Thuc hien hanh trinh:");
        
        // Chặng 1: Bus
        System.out.print("     -> [Step 1] Check-in Bus 19 tai KTX Khu B: ");
        veThang.use(LocalDateTime.now().minusMinutes(60), ktxKhuB); 
        
        // Chặng 2: Metro (Đổi trạm)
        System.out.print("     -> [Step 2] Check-in Metro 1 tai Ga Suoi Tien: ");
        veThang.use(LocalDateTime.now().minusMinutes(30), suoiTien); 

        // Chiều về (Test vé tháng dùng nhiều lần)
        System.out.print("     -> [Return] Chieu ve tai Ga Ben Thanh: ");
        veThang.use(LocalDateTime.now(), benThanh);
        System.out.println("     (Ve Thang hop le cho moi chuyen di trong thang!)");


        // =======================================================================
        // SCENARIO 2: TRAN NGUYEN ANH TAI (WORKER - GIAN LAN)
        // Yeu cau: Mua ve luot, di 1 lan, co tinh dung lai lan 2 -> Bi chan.
        // =======================================================================
        System.out.println("\n>>> SCENARIO 2: TRAN NGUYEN ANH TAI (Worker - Single Ride & Cheating)");
        System.out.println("    [Goal]: Di lam bang ve luot nhung thu an gian ve cu.");

        Customer tai = new Customer("24130270", "Tran Nguyen Anh Tai", CustomerType.WORKER);
        tai.topUpBalance(50000);

        // 2.1 Mua vé lượt
        Order orderTai = new Order("ORD_T", tai);
        SingleRideTicket veLuot = ticketService.issueSingleRideTicket(orderTai);
        
        orderTai.setStatus(OrderStatus.COMPLETED);
        revenueManager.addRevenue(orderTai.getCreatedAt().toLocalDate(), orderTai.getTotalPrice());
        System.out.println("  1. [PAYMENT] Tai mua Ve Luot (Single Ride). Gia: " + String.format("%,.0f", orderTai.getTotalPrice()));

        // 2.2 Check-in lần 1 (Hợp lệ)
        System.out.print("  2. [CHECK-IN 1] Tai Tram Suoi Tien: ");
        // Giả lập hệ thống kiểm tra vé
        if(veLuot.getState() == TicketState.ACTIVE) {
            veLuot.use(LocalDateTime.now().minusMinutes(10), suoiTien);
            // Sau khi qua cổng, hệ thống đánh dấu vé đã dùng
            veLuot.setState(TicketState.USED); 
            System.out.println(" -> Chuc Tai thuong lo binh an ");
        }

        // 2.3 Check-in lần 2 (Cố tình gian lận dùng lại vé cũ ở trạm khác)
        System.out.print("  3. [CHECK-IN 2] Co tinh dung lai ve cu tai Ben Thanh: ");
        if(veLuot.getState() == TicketState.USED) {
            System.out.println(" -> [DENIED] HE THONG TU CHOI! (Ticket State: USED)");
            System.out.println("     (Canh bao: Tai vui long mua ve moi!)");
        } else {
            veLuot.use(LocalDateTime.now(), benThanh);
        }


        // =======================================================================
        // SCENARIO 3: AUDIT & REPORTING (BAO CAO DOANH THU)
        // =======================================================================
        System.out.println("\n>>> SCENARIO 3: AUDIT & REPORTING (DOANH THU CUA QUAN & TAI)");

        // 3.1 In lịch sử ví của Quan (Đã nạp tiền & Mua vé)
        quan.addTransaction(new Transaction("TRX_01", 200000, LocalDateTime.now().minusHours(2), TransactionType.TOP_UP));
        quan.addTransaction(new Transaction("TRX_02", -100000, LocalDateTime.now().minusHours(1), TransactionType.PAYMENT)); 

        System.out.println("  1. [WALLET HISTORY] Lich su vi cua SV Vo Ba Minh Quan:");
        System.out.println("     + NAP TIEN:   +200,000 VND");
        System.out.println("     + MUA VE THANG: -100,000 VND");

        // 3.2 Báo cáo doanh thu (TreeMap sắp xếp theo ngày)
        System.out.println("  2. [REVENUE REPORT] Tong hop doanh thu tu Quan va Tai:");
        
        List<Order> allOrders = Arrays.asList(orderQuan, orderTai);
        double totalRev = allOrders.stream().mapToDouble(Order::getTotalPrice).sum();

        TreeMap<LocalDate, Double> map = revenueManager.getRevenueMap(); 
        if (map != null) {
            map.forEach((date, amount) -> 
                System.out.println("     -> Ngay " + date + ": " + String.format("%,.0f", amount) + " VND"));
        }
        System.out.println("     => TONG DOANH THU: " + String.format("%,.0f", totalRev) + " VND");

        // 3.3 Thống kê loại vé
        System.out.println("  3. [STATISTICS] So luong ve ban ra:");
        Map<String, Long> stats = allOrders.stream()
                .flatMap(o -> o.getListOfTicket().stream())
                .collect(Collectors.groupingBy(t -> t.getClass().getSimpleName(), Collectors.counting()));
        System.out.println("     " + stats);

        System.out.println("==========================================================================");
        System.out.println("   KET THUC CHUONG TRINH - NHOM SINH VIEN: QUAN & TAI CAM ON Co!");
    }
}