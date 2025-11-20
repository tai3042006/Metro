
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Order {

    /**
     * Default constructor
     */
    public Order() {
    }

    /**
     * 
     */
    public String id;

    /**
     * 
     */
    public List<Ticket> ListOfTicket;

    /**
     * 
     */
    public OrderStatus << enum>> status;

    /**
     * 
     */
    public double totalPrice;

    /**
     * 
     */
    public LocalDate createAt;

    /**
     * 
     */
    public payment Payment;






    /**
     * @return
     */
    public boolean validateOrder() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public void CancelOrder() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public double topUpBalance() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public double deductBalance() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public double getBalance() {
        // TODO implement here
        return 0.0d;
    }

}