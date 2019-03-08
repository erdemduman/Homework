/**
 * @author Erdem Duman<br>
 * This class implements State interface to change methods<br>
 * for itself.<br>
 */

public class Graduate implements State{

    /**
     * Method implementation for this state.<br>
     * @param ctx context<br>
     */
    @Override
    public void hardwork(Context ctx) {
        System.out.println("You can't do that.");
    }

    /**
     * Method implementation for this state.<br>
     * @param ctx context<br>
     */
    @Override
    public void exercise(Context ctx) {
        System.out.println("You can't do that.");
    }

    /**
     * Method implementation for this state.<br>
     * @param ctx context<br>
     */
    @Override
    public void cheating(Context ctx) {
        System.out.println("You can't do that.");
    }

    /**
     * Method implementation for this state.<br>
     * @param ctx context<br>
     */
    @Override
    public void gtx1080(Context ctx) {
        System.out.println("You can't do that.");
    }

    /**
     * Method implementation for this state.<br>
     * @param ctx context<br>
     */
    @Override
    public void outTillLate(Context ctx) {
        System.out.println("You can't do that.");
    }

    /**
     * Method implementation for this state.<br>
     * @param ctx context<br>
     */
    @Override
    public void sleep(Context ctx) {
        System.out.println("You can't do that.");
    }

    /**
     * Method implementation for this state.<br>
     * @param ctx context<br>
     */
    @Override
    public void coffeeAndWork(Context ctx) {
        System.out.println("You can't do that.");
    }
}
