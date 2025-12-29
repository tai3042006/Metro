/**
 * TransactionType.java
 * Enum định nghĩa các loại giao dịch trong hệ thống
 */
public enum TransactionType {
    TOP_UP,     // Nạp tiền vào tài khoản/thẻ
    PAYMENT,    // Thanh toán tiền mua vé
    REFUND      // Hoàn tiền (khi hủy vé hoặc lỗi hệ thống)
}