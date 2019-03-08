package sample;

/**
 * @author Erdem Duman <br>
 * This is an abstract class that is extended by <br>
 * market and plane factories. This class will be used by <br>
 * FactoryGenerator class.<br>
 */
public abstract class AbstractFactory {
    /**
     * This method generates a plane object according to <br>
     * the given string. <br>
     * @param name name of the plane.<br>
     * @return plane object. Null if there is no related plane.<br>
     */
    abstract Plane getPlane(String name);

    /**
     * This method generates a market object according to <br>
     * the given string. <br>
     * @param name name of the market.<br>
     * @return market object. Null if there is no related market.<br>
     */
    abstract Market getMarket(String name);
}
