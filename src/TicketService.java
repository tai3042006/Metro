import java.time.LocalDateTime;

public class TicketService {

    // Định nghĩa bảng giá cơ bản 
    private static final double PRICE_SINGLE_RIDE = 10000.0; // 10k
    private static final double PRICE_DAILY       = 30000.0; // 30k
    private static final double PRICE_MONTHLY     = 200000.0; // 200k

    
    public TicketService() {
    }

    
    private double calculatePrice(Customer customer, double basePrice) {
        // Kiểm tra: Nếu Type là STUDENT HOẶC có thẻ StudentCard
        boolean isStudent = (customer.getType() == CustomerType.STUDENT) 
                         || (customer.getStudentCard() != null);

        if (isStudent) {
            System.out.println("   -> [GIẢM GIÁ] Áp dụng giảm 50% cho sinh viên: " + customer.getName());
            return basePrice * 0.5;
        }
        
        return basePrice;
    }

    
    private String generateTicketId(String prefix) {
        return prefix + "_" + System.currentTimeMillis();
    }

    public DailyTicket issueDailyTicket(Order order) {
        double finalPrice = calculatePrice(order.getCustomer(), PRICE_DAILY);
        
        DailyTicket ticket = new DailyTicket(generateTicketId("D"), finalPrice);
        order.addTicket(ticket);
        
        System.out.println("Đã phát hành Vé Ngày. Giá: " + finalPrice);
        return ticket;
    }

    
    public SingleRideTicket issueSingleRideTicket(Order order) {
        double finalPrice = calculatePrice(order.getCustomer(), PRICE_SINGLE_RIDE);
        
      
        SingleRideTicket ticket = new SingleRideTicket(generateTicketId("S"), finalPrice, null, null);
        
        order.addTicket(ticket);
        System.out.println("Đã phát hành Vé Lượt. Giá: " + finalPrice);
        return ticket;
    }

    
    public MonthlyTicket issueMonthlyTicket(Order order) {
        double finalPrice = calculatePrice(order.getCustomer(), PRICE_MONTHLY);
        
        MonthlyTicket ticket = new MonthlyTicket(generateTicketId("M"), finalPrice);
        order.addTicket(ticket);
        
        System.out.println("Đã phát hành Vé Tháng. Giá: " + finalPrice);
        return ticket;
    }

    
    public boolean validateTicket(Ticket ticket, LocalDateTime time, Station station) {
        if (ticket == null) return false;
        
        boolean isValid = ticket.isValid(time, station);
        if (isValid) {
            ticket.use(time, station);
        }
        return isValid;
    }

    
    public void cancelTicket(String ticketId) {
        
        System.out.println("Yêu cầu hủy vé ID: " + ticketId + " đã được tiếp nhận.");
    }
}