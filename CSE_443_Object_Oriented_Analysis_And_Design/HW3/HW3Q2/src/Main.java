import java.util.Scanner;

/**
 * @author Erdem Duman <br>
 * This is the Main class of the program.<br>
 */
public class Main {

    /**
     * Main method of the program.<br>
     * @param args main arguments <br>
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Composite user1 = new AddressBook("siriusblack@hogwarts.com", "Sirius Black");
        Composite user2 = new AddressBook("gryffindor@hogwarts.com", "Gryffindor");
        Composite user3 = new AddressBook("harrypotter@hogwarts.com", "Harry Potter");
        Composite user4 = new AddressBook("carrot@hogwarts.com", "Ron Weasley");
        Composite user5 = new AddressBook("muggle@hogwarts.com", "Hermione Granger");
        Composite user6 = new AddressBook("slytherin@hogwarts.com", "Slytherin");
        Composite user7 = new AddressBook("dracomalfoy@hogwarts.com", "Draco Malfoy");

        user2.add(user3);
        user2.add(user4);
        user2.add(user5);

        user6.add(user7);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user6);

        System.out.print("<Press Enter to Finish>");
        scanner.nextLine();
    }
}
