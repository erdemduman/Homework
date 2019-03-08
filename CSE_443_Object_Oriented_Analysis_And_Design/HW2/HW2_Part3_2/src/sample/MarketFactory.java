package sample;

/**
 * @author Erdem Duman
 * This class is a factory class that generates<br>
 * a market object.<br>
 */
public class MarketFactory extends AbstractFactory {

    /**
     * This method generates a market object according to <br>
     * the given string. <br>
     * @param name name of the market.<br>
     * @return market object. Null if there is no related market.<br>
     */
    @Override
    public Market getMarket(String name){

        if(name.equals("Eurasia"))
            return new Eurasia();
        else if(name.equals("Domestic"))
            return new Domestic();
        else if(name.equals("Other"))
            return new Other();

        return null;
    }

    /**
     * This method is needed to be implemented since <br>
     * it is overridden from an abstract method.<br>
     * @param name name of the plane.<br>
     * @return null <br>
     */
    @Override
    Plane getPlane(String name) {
        return null;
    }
}
