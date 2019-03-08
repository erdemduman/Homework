package sample;

/**
 * @author Erdem Duman <br>
 * This class keeps informations about a market type.<br>
 */
public class Other implements Market {

    /**
     * This method returns the engine injection type of plane.<br>
     * @return engine injection type of plane.<br>
     */
    @Override
    public String engineInjectionType() {
        return "Geared Turbofan";
    }

    /**
     * This method returns the seating cover of plane.<br>
     * @return seating cover of plane.<br>
     */
    @Override
    public String seatingCover() {
        return "Leather";
    }
}
