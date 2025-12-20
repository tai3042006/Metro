
import java.io.*;
import java.util.*;
import javax.swing.text.StyledEditorKit;

/**
 * 
 */
public class Train {
    public String name;
    public boolean status;
    public Locomotive locomotive;
    public LinkedList<Carriage> carriageList;
        public Train() {
        this.carriageList = new LinkedList<>();
        }
        public Train(String name, boolean status, Locomotive locomotive){
            this.name = name;
            this.locomotive = locomotive;
            this.carriageList = new LinkedList<>();
            this.status = true; 
        }
        public String getName(){
            return name;
        }
    public void setName(String name){
        this.name = name;
    }
    public boolean isStatus(){
        return status;
    }
    public void setstatus(boolean setstatus) {
        // TODO implement here
        this.status = status;
    }
    public Locomotive getLocomotive(){
        return locomotive;
    }
    public void setLocomotive(Locomotive locomotive){
        this.locomotive = locomotive;
    }
    public LinkedList<Carriage> GetCarriageList(){
        return  carriageList;
    }
    public void addCarriage(Carriage carriage){
        this.carriageList.add(carriage);
    }
    /**
     * @return
     */
    public int getSeatQuantity() {
        // TODO implement here
        int totalSeats = 0;
        for (Carriage carriage : carriageList) {
            totalSeat += carriage.totalSeats
        }
    }

}