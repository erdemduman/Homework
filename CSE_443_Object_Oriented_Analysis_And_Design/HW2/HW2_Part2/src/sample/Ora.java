package sample;

/**
 * @author Erdem Duman <br>
 * This class keeps some information about a suit type.<br>
 */
public class Ora implements Suit {
    private final Integer cost = 1500;
    private final Double weight = 30.0;
    private final String name = "Ora";

    /**
     * This method returns the cost of suit.<br>
     * @return cost. <br>
     */
    @Override
    public Integer getCost(){return cost;}

    /**
     * This method returns the weight of suit.<br>
     * @return weight. <br>
     */
    @Override
    public Double getWeight(){return weight;}

    /**
     * This method returns the name of suit.<br>
     * @return name. <br>
     */
    @Override
    public String getName(){return name;}
}
