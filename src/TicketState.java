public enum TicketState {
    BOOKED,     // Đã đặt 
    ACTIVE,     // Đang hoạt động 
    USED,       // Đã sử dụng xong
    CANCELLED;  // Đã hủy

  
    public boolean isUsable() {
        return this == ACTIVE;
    }
}