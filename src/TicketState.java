/**
 * TicketState.java
 * Enum quản lý trạng thái của vé
 */
public enum TicketState {
    BOOKED,     // Đã đặt (chưa kích hoạt/chưa thanh toán xong)
    ACTIVE,     // Đang hoạt động (Có thể dùng để đi tàu)
    USED,       // Đã sử dụng xong (Hết lượt hoặc hết hạn)
    CANCELLED;  // Đã hủy

    /**
     * Kiểm tra xem trạng thái này có dùng được vé không.
     * Logic: Chỉ khi vé đang ACTIVE thì mới dùng được.
     * @return true nếu dùng được
     */
    public boolean isUsable() {
        // Trong Enum, từ khóa 'this' đại diện cho trạng thái hiện tại
        return this == ACTIVE;
    }
}