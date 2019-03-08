package sample;

/**
 * @author Erdem Duman <br>
 * This class generates a factory according to method parameters.<br>
 */
public class FactoryGenerator {
    /**
     * This method returns a factory object according to <br>
     * given string.<br>
     * @param name name of factory.<br>
     * @return factory object. Null, whether there is no related factory.<br>
     */
    public static AbstractFactory getFactory(String name){

        if(name.equals("Plane")){
            return new PlaneFactory();
        }

        else if(name.equals("Market")){
            return new MarketFactory();
        }

        return null;
    }
}
