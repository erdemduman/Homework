import java.util.ArrayList;
import java.util.List;

/**
 * @author Hakki Erdem Duman<br>
 */
public class Homework5Q2 {
    public static void main(String[] args){

        FileOperations fileObj = new FileOperations();
        List<String> list = new ArrayList<String>();

        list = fileObj.readFile("family.txt");
        String rootName = fileObj.getRoot();

        FamilyTree treeObj = new FamilyTree(rootName);

        for(int counter = 0; counter < list.size(); counter+=3){
            try{
                treeObj.addFamily(list.get(counter),list.get(counter+1),list.get(counter+2));
            }
            catch(MyException e){
                System.out.println(e);
            }
        }
        treeObj.traverseIterator();
    }
}
