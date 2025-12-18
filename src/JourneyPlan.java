
import java.io.*;
import java.util.*;

/**
 * 
 */
public class JourneyPlan {

    /**
     * Default constructor
     */
    public JourneyPlan() {
    }

    /**
     * 
     */
    private String planId;

    /**
     * 
     */
    private Station startStation;

    /**
     * 
     */
    private Station endStation;

    /**
     * 
     */
    private List<Route> routes;

    /**
     * 
     */
    private List<Station> transferStations;

    /**
     * 
     */
    private double totalDistance;

    /**
     * 
     */
    private double totalTime;



    /**
     * @return
     */
    public double calculateTotalDistance() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public double calculateTotalTime() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public boolean hasTransfer() {
        // TODO implement here
        return false;
    }

}