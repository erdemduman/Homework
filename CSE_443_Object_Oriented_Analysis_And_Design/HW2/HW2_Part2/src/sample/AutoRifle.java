package sample;

/**
 * @author Erdem Duman <br>
 * This class extends a decorator class to decorate suit. <br>
 */
public class AutoRifle extends SuitDecorator {
    final private Integer cost = 30;
    final private Double weight = 1.5;
    final private String name = "Auto Rifle";

    /**
     * Constructor of class.<br>
     * @param decoratedSuit Suit object to decorate <br>
     */
    public AutoRifle(Suit decoratedSuit){
        super(decoratedSuit);
    }

    /**
     * This method is overridden from SuitDecorator class<br>
     * and calculates the cost of suit.<br>
     * @return cost.<br>
     */
    @Override
    public Integer getCost(){return cost + this.decoratedSuit.getCost();}

    /**
     * This method is overridden from SuitDecorator class<br>
     * and calculates the weight of suit.<br>
     * @return weight.<br>
     */
    @Override
    public Double getWeight(){return weight + this.decoratedSuit.getWeight();}

    /**
     * This method is overridden from Suit interface<br>
     * and gives the name of product.<br>
     * @return name of product.<br>
     */
    @Override
    public String getName(){return name;}

}
