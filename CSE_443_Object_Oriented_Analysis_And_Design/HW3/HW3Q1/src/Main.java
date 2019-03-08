import java.util.Scanner;

/**
 * @author Erdem Duman <br>
 * This is the main class that runs the program. <br>
 */

public class Main {

    /**
     * Main method of the program. <br>
     * @param args main arguments.<br>
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ModernPayment mp = new CardA();
        TurboPayment tp = new CardB();

        System.out.println("Here is modern payment object:");
        mp.pay("1", 150, "Istanbul", "12");
        System.out.println();

        System.out.println("Here is turbo payment object:");
        tp.payInTurbo("2", 120, "Gebze", "10");
        System.out.println();

        TurboPayment adapter = new PaymentAdapter(mp);

        System.out.println("Here is a payment with adapter");
        adapter.payInTurbo("2",120, "Gebze", "10");

        System.out.println("\n<Press Enter to Exit>");
        scanner.nextLine();
    }
}
