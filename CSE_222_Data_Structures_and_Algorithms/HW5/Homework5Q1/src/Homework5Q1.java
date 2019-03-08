import java.util.ArrayList;
import java.util.List;

/**
 * @author Hakki Erdem Duman.<br>
 *
 */
public class Homework5Q1 {
    public static void main(String[]args){

        FileOperations fileObj = new FileOperations();
        List<Integer> list = new ArrayList<Integer>();
        list = fileObj.readFile("test.txt");

        BinaryTree<Integer> treeObj = new BinaryTree<Integer>();
        BinaryTree<Integer> bsTreeObj = new BinarySearchTree<Integer>();
        for(Integer data : list){
            treeObj.add(data);
            bsTreeObj.add(data);
        }
        System.out.print("This one is pre - order traversing: ");
        treeObj.traverseIterator();
        System.out.println("\n");
        System.out.print("This one is level - order traversing: ");
        bsTreeObj.traverseIterator();
        System.out.println("\n");
    }
}
