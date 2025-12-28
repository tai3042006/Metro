
import java.io.*;
import java.util.*;

/**
 * 
 */
public abstract class Ticket {

    /**
     * Default constructor
     */
    public Ticket() {
    }

    /**
     * 
     */
    private String ticketId;

    /**
     * 
     */
    private double price;

    /**
     * 
     */
    private TicketState state;

    /**
     * 
     */
    private LocalDate issueDate;







    /**
     * @param time 
     * @param station 
     * @return
     */
    public boolean isValid(LocalDateTime time, Station station) {
        // TODO implement here
        return false;
    }

    /**
     * @param time 
     * @param station 
     * @return
     */
    public void use(LocalDateTime time, Station station) {
        // TODO implement here
        return null;
    }

}