import java.io.*;

/**
 * This class extends BinaryTree class and creates a family tree.<br>
 * @author Hakki Erdem Duman<br>
 */
public class FamilyTree extends BinaryTree<String> {

    private final String ibn = "ibn-";
    private final String ebu = "ebu-";

    /**
     * This constructor calls the constructor with parameter<br>
     * and initialize the root with Hasan.<br>"
     */
    public FamilyTree(){
        new FamilyTree("Hasan");
    }

    /**
     * This constructor calls the father constructor and initialize the<br>
     * root name with the given parameter.<br>
     * @param rootName name of the root.<br>
     */
    public FamilyTree(String rootName){
        super();
        root.data = rootName;
        root.nick = "0";
        root.parent = "0";
    }

    /**
     * This method checks whether the given family member is the same with previous ones or not.<br>
     * @param name name of member<br>
     * @param parentParam name of parent<br>
     * @param nickname nickname of parent<br>
     * @param temp temporary node<br>
     * @param control changes to false whether there is an invalid situation.<br>
     */
    public void isThereAnyEqual(String name,String parentParam, String nickname, Node<String> temp, Boolean[] control){

        if(temp == null)
            return;

        else{
            if(temp.parent.equals(parentParam) && temp.nick.equals(nickname) && temp.data.equals(name))
                control[0] = false;
        }

            isThereAnyEqual(name,parentParam, nickname,temp.left,control);
            isThereAnyEqual(name,parentParam, nickname,temp.right,control);
    }


    /**
     * This method checks whether there is a parent like the given parameter.<br>
     * @param parent parent name<br>
     * @param temp temporary node<br>
     * @param control changes to true whether the condition is valid.<br>
     */
    public void isThereAParent(String parent, Node<String> temp, Boolean[] control){
        if(temp == null)
            return;

        else{
            if(temp.data.equals(parent)){
                control[1] = true;
            }

            isThereAParent(parent,temp.left,control);
            isThereAParent(parent,temp.right,control);
        }
    }

    /**
     * This method checks the possible invalid situation and calls the<br>
     * other addFamily method to add the member to the tree.<br>
     * @param name name of member<br>
     * @param parent name of parent<br>
     * @param nickname parent's nickname<br>
     * @throws MyException whether there is an invalid situation.<br>
     */
    public void addFamily(String name, String parent, String nickname) throws MyException{

        Node<String> temp = new Node<String>();
        Boolean[] control = new Boolean[2];
        control[0] = true;
        control[1] = false;
        temp = root;
        isThereAnyEqual(name,parent,nickname,temp,control);
        temp = root;
        isThereAParent(parent,temp,control);

        if(!control[0]){
            throw new MyException("There is a weird inserting.");
        }
        else if(!control[1]){
            throw new MyException("There is no parent like this.");
        }
        else{
            temp = root;
            addFamily(name, parent, nickname, temp);
        }
    }

    /**
     * This method adds the given member to the tree if there is no<br>
     * invalid situation.<br>
     * @param name name of member<br>
     * @param parent name of parent<br>
     * @param nickname parent's nickname<br>
     * @param temp temporary node<br>
     */
    public void addFamily(String name, String parent, String nickname, Node <String> temp){

        if(temp == null)
            return;

        else{
            if(temp.data.equals(parent)){
                if(temp.left == null){
                    temp.left = new Node<String>();
                    temp = temp.left;
                    temp.data = name;
                    temp.parent = parent;
                    temp.nick = nickname;
                }
                else{
                    temp = temp.left;
                    while(temp.right!=null){
                        temp = temp.right;
                    }
                    temp.right = new Node<String>();
                    temp = temp.right;
                    temp.data = name;
                    temp.parent = parent;
                    temp.nick = nickname;
                }
            }
            else{
                addFamily(name,parent, nickname,temp.left);
                addFamily(name,parent, nickname,temp.right);
            }

        }
    }
}
