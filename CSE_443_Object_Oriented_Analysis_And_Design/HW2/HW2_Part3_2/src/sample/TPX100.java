package sample;

/**
 * @author Erdem Duman <br>
 * This class keeps informations about a plane type.<br>
 */
public class TPX100 implements Plane{

    /**
     * This method returns the purpose of plane.<br>
     * @return purpose of plane.<br>
     */
    @Override
    public String purpose(){
        return "Domestic Flights";
    }

    /**
     * This method returns the skeleton of plane.<br>
     * @return skeleton of plane.<br>
     */
    @Override
    public String constructSkeleton(){
        return "Aluminium Alloy";
    }

    /**
     * This method returns the engine of plane.<br>
     * @return engine of plane.<br>
     */
    @Override
    public String placeEngines(){
        return "Single Jet Engine";
    }

    /**
     * This method returns the seat number of plane.<br>
     * @return seat number of plane.<br>
     */
    @Override
    public String placeSeats(){
        return "50 Seats";
    }
}
