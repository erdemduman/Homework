package sample;

/**
 * @author Erdem Duman <br>
 * Suit interface to be implemented by SuitDecorator class.<br>
 */
public interface Suit {
    /**
     *
     * @return cost.<br>
     */
    Integer getCost();
    /**
     *
     * @return weight.<br>
     */
    Double getWeight();
    /**
     *
     * @return name.<br>
     */
    String getName();
}
