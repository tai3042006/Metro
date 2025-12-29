import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Revenue {

   
    private TreeMap<LocalDate, Double> revenueByDate;

    
    public Revenue() {
        this.revenueByDate = new TreeMap<>();
    }

    /**
     * * @param date Ngày phát sinh doanh thu
     * @param amount Số tiền
     */
    public void addRevenue(LocalDate date, double amount) {
       
        this.revenueByDate.merge(date, amount, Double::sum);

        /* // CÁCH 2:
        if (this.revenueByDate.containsKey(date)) {
            double currentAmount = this.revenueByDate.get(date);
            this.revenueByDate.put(date, currentAmount + amount);
        } else {
            this.revenueByDate.put(date, amount);
        }
        */
    }

    public TreeMap<LocalDate, Double> getRevenueByDate() {
        return this.revenueByDate;
    }

    
    public void printReport() {
        System.out.println("--- BÁO CÁO DOANH THU (Tự động sắp xếp theo ngày) ---");
        // Java 8 forEach
        this.revenueByDate.forEach((date, amount) -> 
            System.out.println("Ngày: " + date + " | Doanh thu: " + String.format("%,.0f VNĐ", amount))
        );
    }
}
