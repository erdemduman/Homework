/**
 * This is the main class to test.<br>
 * @author Hakki Erdem Duman<br>
 */
public class SecondQuestion {
    public static void main(String[] Args){
        SingleLinkedList<String> theList = new SingleLinkedList<String>();

        theList.append("They");
        theList.append("Are");
        theList.append("Taking");
        theList.append("The");
        theList.append("Hobbits");
        theList.append("To");
        theList.append("Isengaard");

        System.out.println("Elements from head to tail: " + theList);
        System.out.println("Elements from tail to head: " + theList.reverseToString());
    }
}
