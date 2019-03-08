/**
 * @author Erdem Duman<br>
 * This is the main class of the program.<br>
 */

public class Main {

    /**
     * Main method of program. Calls other tests.<br>
     * @param args main arguments<br>
     */
    public static void main(String[] args) {
        readyToGraduate();
        readyToChromicIllness();
        readyToAxe();
        wrongState();
    }

    /**
     * First test<br>
     */
    public static void readyToGraduate(){
        System.out.println("Test 1");
        Context ctx = new Context(new Ready());
        ctx.exercise();
        ctx.hardwork();
    }

    /**
     * Second test<br>
     */
    public static void readyToChromicIllness(){
        System.out.println("\nTest 2");
        Context ctx = new Context(new Ready());
        ctx.outTillLate();
        ctx.coffeeAndWork();
    }

    /**
     * Third test<br>
     */
    public static void readyToAxe(){
        System.out.println("\nTest 3");
        Context ctx = new Context(new Ready());
        ctx.gtx1080();
    }

    /**
     * Fourth test<br>
     */
    public static void wrongState(){
        System.out.println("\nTest 4");
        Context ctx = new Context(new Ready());
        ctx.sleep();
    }
}
