
import java.io.*;
import java.util.*;

/**
 * 
 */
public class TicketService {

    /**
     * Default constructor
     */
    public TicketService() {
    }

    /**
     * @param order 
     * @return
     */
    public DailyTicket issueDailyTicket(Order order) {
        // TODO implement here
        return null;
    }

    /**
     * @param order 
     * @return
     */
    public SingleRideTicket issueWeeklyTicket(Order order) {
        // TODO implement here
        return null;
    }

    /**
     * @param order 
     * @return
     */
    public MonthlyTicket issueMonthlyTicket(Order order) {
        // TODO implement here
        return null;
    }

    /**
     * @param ticket 
     * @param time 
     * @param station 
     * @return
     */
    public boolean validateTicket(Ticket ticket, LocalDateTime time, Station station) {
        // TODO implement here
        return false;
    }

    /**
     * @param ticketId 
     * @return
     */
    public void cancelTicket(String ticketId) {
        // TODO implement here
        return null;
    }

}