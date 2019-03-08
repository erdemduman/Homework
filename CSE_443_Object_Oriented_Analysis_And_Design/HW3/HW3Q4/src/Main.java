import java.util.Scanner;

/**
 * @author Erdem Duman <br>
 * Main class of program. <br>
 */
public class Main {

    /**
     * This method is the main method of the program.<br>
     *
     * @param args main arguments<br>
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TemplateAbstract ta = null;
        int method;
        String counter;
        System.out.println("1)DFT\n2)DCT\n");
        System.out.println("Your choice: ");
        method = scanner.nextInt();
        scanner.nextLine();
        if(method == 1){
            System.out.println("Wanna see counter?(y/n): ");
            counter = scanner.nextLine();
            ta = new DFT("values.txt", (counter.equals("y")) ? true : false);
        } else{
            ta = new DCT("values.txt");
        }

        System.out.println();
        ta.startShow();

        System.out.println("\n<Press Enter to Finish>");
        scanner.nextLine();


    }
}
