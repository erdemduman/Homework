package sample;

/**
 * @author Erdem Duman <br>
 * This abstract class implements Suit interface to be extended by special decor classes. <br>
 */
public abstract class SuitDecorator implements Suit{
    protected Suit decoratedSuit;

    /**
     * Constructor of class.<br>
     * @param decoratedSuit Suit object. <br>
     */
    public SuitDecorator(Suit decoratedSuit){
        this.decoratedSuit = decoratedSuit;
    }

    /**
     * This method is overridden from Suit interface <br>
     * to be implemented by decor classes. <br>
     * @return cost. <br>
     */
    @Override
    public abstract Integer getCost();

    /**
     * This method is overridden from Suit interface <br>
     * to be implemented by decor classes. <br>
     * @return weight. <br>
     */
    @Override
    public abstract Double getWeight();

}
