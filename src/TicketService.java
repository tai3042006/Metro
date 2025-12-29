import java.time.LocalDateTime;

public class TicketService {

    // Định nghĩa bảng giá cơ bản (Giá chưa giảm)
    private static final double PRICE_SINGLE_RIDE = 10000.0; // 10k
    private static final double PRICE_DAILY       = 30000.0; // 30k
    private static final double PRICE_MONTHLY     = 200000.0; // 200k

    /**
     * Default constructor
     */
    public TicketService() {
    }

    /**
     * Hàm tính giá vé (Core Logic)
     * Yêu cầu: Nếu là sinh viên -> Giảm 50%
     */
    private double calculatePrice(Customer customer, double basePrice) {
        // Kiểm tra: Nếu Type là STUDENT HOẶC có thẻ StudentCard
        boolean isStudent = (customer.getType() == CustomerType.STUDENT) 
                         || (customer.getStudentCard() != null);

        if (isStudent) {
            // [SỬA LẠI]: Dùng customer.getName() thay vì customer.name
            System.out.println("   -> [GIẢM GIÁ] Áp dụng giảm 50% cho sinh viên: " + customer.getName());
            return basePrice * 0.5;
        }
        
        return basePrice;
    }

    /**
     * Tạo ID ngẫu nhiên cho vé
     */
    private String generateTicketId(String prefix) {
        return prefix + "_" + System.currentTimeMillis();
    }

    /**
     * Phát hành vé ngày (DailyTicket)
     */
    public DailyTicket issueDailyTicket(Order order) {
        double finalPrice = calculatePrice(order.getCustomer(), PRICE_DAILY);
        
        DailyTicket ticket = new DailyTicket(generateTicketId("D"), finalPrice);
        order.addTicket(ticket); // Thêm vé vào đơn hàng
        
        System.out.println("Đã phát hành Vé Ngày. Giá: " + finalPrice);
        return ticket;
    }

    /**
     * Phát hành vé lượt (SingleRideTicket)
     */
    public SingleRideTicket issueSingleRideTicket(Order order) {
        double finalPrice = calculatePrice(order.getCustomer(), PRICE_SINGLE_RIDE);
        
        // Khớp với constructor mới của SingleRideTicket (có trạm đi/đến)
        // Truyền null vì lúc mua chưa biết đi từ đâu đến đâu
        SingleRideTicket ticket = new SingleRideTicket(generateTicketId("S"), finalPrice, null, null);
        
        order.addTicket(ticket);
        System.out.println("Đã phát hành Vé Lượt. Giá: " + finalPrice);
        return ticket;
    }

    /**
     * Phát hành vé tháng (MonthlyTicket)
     */
    public MonthlyTicket issueMonthlyTicket(Order order) {
        double finalPrice = calculatePrice(order.getCustomer(), PRICE_MONTHLY);
        
        MonthlyTicket ticket = new MonthlyTicket(generateTicketId("M"), finalPrice);
        order.addTicket(ticket);
        
        System.out.println("Đã phát hành Vé Tháng. Giá: " + finalPrice);
        return ticket;
    }

    /**
     * Kiểm tra vé có hợp lệ tại trạm và thời điểm cụ thể không
     * Sử dụng tính đa hình (Polymorphism) gọi hàm isValid của từng loại vé
     */
    public boolean validateTicket(Ticket ticket, LocalDateTime time, Station station) {
        if (ticket == null) return false;
        
        boolean isValid = ticket.isValid(time, station);
        if (isValid) {
            // Nếu hợp lệ thì ghi nhận việc sử dụng
            ticket.use(time, station);
        }
        return isValid;
    }

    /**
     * Hủy vé
     */
    public void cancelTicket(String ticketId) {
        // Trong thực tế sẽ tìm vé trong Database để update status
        // Ở đây mình giả lập in ra console
        System.out.println("Yêu cầu hủy vé ID: " + ticketId + " đã được tiếp nhận.");
    }
}