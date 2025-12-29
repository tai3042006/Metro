import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Revenue {

    /**
     * TreeMap dùng để lưu trữ:
     * Key: Ngày (LocalDate) -> Tự động sắp xếp tăng dần
     * Value: Tổng tiền (Double)
     */
    private TreeMap<LocalDate, Double> revenueByDate;

    /**
     * Default constructor
     */
    public Revenue() {
        // Khởi tạo TreeMap để tránh lỗi NullPointerException
        this.revenueByDate = new TreeMap<>();
    }

    /**
     * Thêm doanh thu vào một ngày cụ thể.
     * Nếu ngày đó đã có doanh thu -> Cộng dồn.
     * Nếu chưa có -> Tạo mới.
     * * @param date Ngày phát sinh doanh thu
     * @param amount Số tiền
     */
    public void addRevenue(LocalDate date, double amount) {
        // CÁCH 1: Dùng Java 8 (Khuyên dùng để lấy điểm cao)
        // Hàm merge: Nếu key 'date' chưa có thì put 'amount'. 
        // Nếu có rồi thì lấy giá trị cũ + 'amount' (Double::sum)
        this.revenueByDate.merge(date, amount, Double::sum);

        /* // CÁCH 2: Dùng Java 7 (Cổ điển) - Tham khảo thôi
        if (this.revenueByDate.containsKey(date)) {
            double currentAmount = this.revenueByDate.get(date);
            this.revenueByDate.put(date, currentAmount + amount);
        } else {
            this.revenueByDate.put(date, amount);
        }
        */
    }

    /**
     * Lấy map doanh thu
     * Sửa kiểu trả về từ void -> TreeMap<LocalDate, Double>
     */
    public TreeMap<LocalDate, Double> getRevenueByDate() {
        return this.revenueByDate;
    }

    /**
     * (Tùy chọn) Hàm hỗ trợ in báo cáo để test
     */
    public void printReport() {
        System.out.println("--- BÁO CÁO DOANH THU (Tự động sắp xếp theo ngày) ---");
        // Java 8 forEach
        this.revenueByDate.forEach((date, amount) -> 
            System.out.println("Ngày: " + date + " | Doanh thu: " + String.format("%,.0f VNĐ", amount))
        );
    }
}
