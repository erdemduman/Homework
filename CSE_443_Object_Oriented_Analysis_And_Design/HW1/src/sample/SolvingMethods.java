package sample;

import java.util.ArrayList;

/**
 * @author Erdem Duman
 * This interface keeps common methods <br>
 * of its implementors. <br>
 */
public interface SolvingMethods {
    /**
     * This method is implemented by its implementors to <br>
     * solve equations. <br>
     * @return ArrayList of results<br>
     */
    ArrayList<Double> solveEquation();

    /**
     * This method returns the method name.<br>
     * @return method name <br>
     */
    String methodName();
}
