/**
 * @author Erdem Duman <br>
 * This abstract class is full of abstract methods but one, <br>
 * which calls abstract methods according to their objects.<br>
 */

public abstract class TemplateAbstract {
    protected Double[] real;
    protected int N;
    protected String filename;
    protected boolean option;
    protected Double[] outreal;

    /**
     * Constructor of class.<br>
     * @param filename input filename<br>
     * @param option will be true whether counter will be used. False otherwise.<br>
     */
    public TemplateAbstract(String filename, boolean option){
        this.N = 0;
        this.filename = filename;
        this.option = option;
    }

    /**
     * This method is the only non-abstract method of this class,<br>
     * which calls other abstract methods according to their object.<br>
     */
    public void startShow(){
        fileOperation();
        perform();
        writeToFile();
    }

    /**
     * This method is explained in their implementation.<br>
     */
    protected abstract void fileOperation();
    /**
     * This method is explained in their implementation.<br>
     */
    protected abstract void perform();
    /**
     * This method is explained in their implementation.<br>
     */
    protected abstract void writeToFile();
}
