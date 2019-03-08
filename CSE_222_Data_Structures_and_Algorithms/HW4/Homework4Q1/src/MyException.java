/**
 * This class is written to throw unique error messages.<br>
 * @author Hakki Erdem Duman
 */
public class MyException extends Exception {
    /**
     * This constructor calls the Exception's one.<br>
     * @param message the error message.<br>
     */
    public MyException(String message){
        super(message);
    }
}
