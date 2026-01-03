import java.time.LocalDate;
import java.util.TreeMap; 

public class Revenue {
    
    private TreeMap<LocalDate, Double> revenueByDate = new TreeMap<>();

    public void addRevenue(LocalDate date, double amount) {
      
        revenueByDate.merge(date, amount, Double::sum);
    }

    public void printReport() {
        System.out.println("\n--- BÁO CÁO DOANH THU (SẮP XẾP THEO NGÀY - TREEMAP) ---");
       
        revenueByDate.forEach((date, amount) -> 
            System.out.println("   Ngày: " + date + " | Doanh thu: " + String.format("%,.0f VNĐ", amount))
        );
    }
}