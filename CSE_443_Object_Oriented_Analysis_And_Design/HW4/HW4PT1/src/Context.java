/**
 * @author Erdem Duman<br>
 * This class is used by Main class to access States.<br>
 *
 */

public class Context{

    private State state;

    /**
     * Constructor of class.<br>
     * @param state state object<br>
     */
    public Context(State state){
        this.state = state;
    }

    /**
     * This method helps to change state<br>
     * @param state new state<br>
     */
    public void setState(State state){
        this.state = state;
    }

    /**
     * This method returns the state<br>
     * @return state<br>
     */
    public State getState(){return this.state;}


    /**
     * This method calls the corresponding method from state.<br>
     */
    public void hardwork() {
        state.hardwork(this);
    }

    /**
     * This method calls the corresponding method from state.<br>
     */
    public void exercise() {
        state.exercise(this);
    }

    /**
     * This method calls the corresponding method from state.<br>
     */
    public void cheating() {
        state.cheating(this);
    }

    /**
     * This method calls the corresponding method from state.<br>
     */
    public void gtx1080() {
        state.gtx1080(this);
    }

    /**
     * This method calls the corresponding method from state.<br>
     */
    public void outTillLate() {
        state.outTillLate(this);
    }

    /**
     * This method calls the corresponding method from state.<br>
     */
    public void sleep() {
        state.sleep(this);
    }

    /**
     * This method calls the corresponding method from state.<br>
     */
    public void coffeeAndWork() {
        state.coffeeAndWork(this);
    }

}
