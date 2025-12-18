
import java.io.*;
import java.util.*;


public class Carriage {
    public Carriage() {
    private  String id;
    private int NumberOfSeats;
    private  int avaliableSeats;
    public Carriage()

    }
    public Carriage(String id, int NumberOfSeats, int avaliableSeats){
        this.id = id;
        this.NumberOfSeats = NumberOfSeats;
        this.avaliableSeats = avaliableSeats;
    }
    public String getId(){
        return id;
    }
    public int getAvaliableSeats(){
        return avaliableSeats;
    }
    public void setavaliableSeats(int avaliableSeats){
        this.avaliableSeats = avaliableSeats;
    }
    public int totalSeats() {
        return NumberOfSeats;
}
}