package sample;

/**
 * @author Erdem Duman <br>
 * This class is used to generate unique error messages<br>
 * for each error type.<br>
 */
public class Error {
    public Error(){

    }

    /**
     * This method is called whether content of the matrix <br>
     * is not compatible with number of variables.<br>
     * @return error message<br>
     */
    public String variableError(){return "\n\tSome variables are missing or extra!";}

    /**
     * This method is called whether there is no number of <br>
     * variables.<br>
     * @return error message<br>
     */
    public String noVariable(){return "\n\tPlease enter the number of variables!";}

    /**
     * This method is called whether there is no solution.<br>
     * @return error message<br>
     */
    public String nanError(){return "\n\tThere is no solution for these equations!";}
}
