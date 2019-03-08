import java.util.*;

/**
 * This is the class that involves the main menu. You can<br>
 * choose one of these three storing structures to see their<br>
 * running time in nanosecond.<br>
 * @author 151044005 Hakki_Erdem_Duman.<br>
 */
public class ProgMenu {

    /**
     * This is the main menu of the program. You are allowed<br>
     * to choose one of three choice to compare running times.<br>
     */
    public void mainMenu(){
        Scanner getInput = new Scanner(System.in);
        int choice;

        System.out.println("1) Add book with Array");
        System.out.println("2) Add book with ArrayList");
        System.out.println("3) Add book with LinkedList");
        System.out.print("Your choice: ");

        choice = getInput.nextInt();

        if(choice == 1){
            TryArray obj = new TryArray();
            obj.addBook("bookList.csv");
        }
        else if(choice == 2){
            TryArrayList obj = new TryArrayList();
            obj.addBook("bookList.csv");
        }
        else if (choice == 3) {
            TryLinkedList obj = new TryLinkedList();
            obj.addBook("bookList.csv");
        }
        else{
            System.out.println("There is no choice like that.");
        }

    }
}

