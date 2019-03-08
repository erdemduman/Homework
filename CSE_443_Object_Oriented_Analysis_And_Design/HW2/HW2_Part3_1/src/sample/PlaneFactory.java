package sample;

/**
 * @author Erdem Duman
 * This class is a factory class that generates<br>
 * a plane object.<br>
 */
public class PlaneFactory {

    /**
     * This method generates a plane object according to <br>
     * the given string. <br>
     * @param name name of the plane.<br>
     * @return plane object. Null if there is no related plane.<br>
     */
    public Plane getPlane(String name){

        if(name.equals("TPX 100"))
            return new TPX100();
        else if(name.equals("TPX 200"))
            return new TPX200();
        else if(name.equals("TPX 300"))
            return new TPX300();

        return null;
    }
}
