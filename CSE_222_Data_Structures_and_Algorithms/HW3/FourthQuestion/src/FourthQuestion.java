
/**
 * This class appends 100 datas to the linked list, remmoves 50 and <br>
 * appends 100 again. No nodes will be deleted.<br>
 * @author Hakki Erdem Duman<br>
 */
public class FourthQuestion {

    public static void main(String[] args){
        SingleLinkedList<Integer> theList = new SingleLinkedList<Integer>();

            for(int a = 0; a<100; a++){
                theList.append(a+1);
            }

            for(int a = 0; a < 50; a++){
                theList.remove();
            }

            for(int a = 0; a < 100; a++){
                theList.append(a+1);
            }

            System.out.println(theList);
            System.out.println(theList.deletedToString());

    }
}
