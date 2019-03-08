import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class extends BinaryTree class and creates a family tree.<br>
 * @author Hakki Erdem Duman<br>
 */
public class FamilyTree extends BinaryTree<String>{

    private final String ibn = "ibn";
    private final String ebu = "ebu";

    /**
     * Depth of the tree.<br>
     */
    private int depth;

    /**
     * ArrayList to store tree's datas.<br>
     */
    private List<String> FamilyElements;

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
        depth = 0;
        root.data = rootName;
        root.nick = "0";
        root.parent = "0";
    }

    /**
     * This method returns an iterator class object.<br>
     * @return iterator class object.<br>
     *
     */
    public Iterator<String> levelOrderIterator() {
        return new IterClass<String>();
    }
    /**
     * This inner class implements Iterator class and <br>
     * traverses on a tree (level - order). <br>
     *
     */
    private class IterClass<E> implements Iterator<E> {
        /**
         * This data field stores the current index in tree.<br>
         */
        private int indexFamily;

        /**
         * This constructor initializes index to zero.<br>
         */
        public IterClass() {
            indexFamily = 0;
        }

        /**
         * This method is overridden from Iterator class and <br>
         * checks whether the tree is traversed or not.<br>
         * @return false if the tree is traversed already.<br>
         */
        @Override
        public boolean hasNext() {
            return indexFamily < FamilyElements.size();
        }

        /**
         * This method is overridden from Iterator class and <br>
         * reads the next element whenever it is called. <br>
         *
         *
         * @return the element that is passed.<br>
         */
        @Override
        public E next() {
            E willBeReturned = (E) FamilyElements.get(indexFamily);
            indexFamily++;
            return willBeReturned;
        }

        /**
         * This method is overridden from Iterator class but <br>
         * isn't used in this class.<br>
         */
        @Override
        public void remove() {
            System.out.println("This method is not working in this version of iterator.");
        }

    }

    /**
     * This method checks whether the given family member is the same with previous ones or not.<br>
     * @param name name of member<br>
     * @param parentParam name of parent<br>
     * @param nickname nickname of parent<br>
     * @param temp temporary node<br>
     * @param control changes to false whether there is an invalid situation.<br>
     */
    private void isThereAnyEqual(String name,String parentParam, String nickname, Node<String> temp, Boolean[] control){

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
    private void isThereAParent(String parent, Node<String> temp, Boolean[] control){
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
    public void addFamily(String name, String parent, String nickname, Node <String> temp) {

        boolean flag = false;

        if (temp == null)
            return;

        else {
            if (temp.data.equals(parent)) {

                if (nickname.substring(0, 3).equals(ibn)) {

                    if (nickname.substring(4).equals(temp.parent)) {
                        flag = true;
                    }
                }
                else if (nickname.substring(0, 3).equals(ebu)) {
                    if (nickname.substring(4).equals(name)) {
                        flag = true;
                    }
                    else if (!(temp.left == null)) {
                        if (nickname.substring(4).equals(temp.left.data)) {
                            flag = true;
                        }
                    }
                }

                if (flag) {
                    if (temp.left == null) {
                        temp.left = new Node<String>();
                        temp = temp.left;
                        temp.data = name;
                        temp.parent = parent;
                        temp.nick = nickname;
                    }
                    else {
                        temp = temp.left;
                        while (temp.right != null) {
                            temp = temp.right;
                        }
                        temp.right = new Node<String>();
                        temp = temp.right;
                        temp.data = name;
                        temp.parent = parent;
                        temp.nick = nickname;
                    }
                }
            }
            else if(!flag) {
                addFamily(name, parent, nickname, temp.left);
                addFamily(name, parent, nickname, temp.right);
            }
        }
    }
        /**
         * This method compares two integers.<br>
         * @param first first number <br>
         * @param second second number <br>
         * @return the bigger one.<br>
         */
        private int compareInts(int first, int second){
            if(first > second)
                return first;
            else
                return second;
        }

        /**
         * This method creates a node to send it to the<br>
         * same method with constructor.<br>
         */
        private void findDepth(){
            Node<String> temp = new Node<String>();
            temp = root;
            depth = findDepth(root);
        }

        /**
         * This method finds the depth of tree.<br>
         * @param temp node <br>
         * @return depth of tree.<br>
         */
        private int findDepth(Node temp){
            if(temp==null)
                return 0;

            return compareInts(findDepth(temp.left),findDepth(temp.right)) + 1;
        }

        /**
         * This method creates a temp node and ArrayList<br>
         * to send them to the same method with arguments.<br>
         * Also traverses in returned ArrayList, which stores the elements<br>
         * of Tree with the rule of level-order traversing.<br>
         */
        @Override
        public void traverseIterator(){
            findDepth();
            if(depth <= 0){
                System.out.println("There is no tree to show");
            }
            else{
                Node<String> temp = new Node<String>();
                temp = root;
                FamilyElements = new ArrayList<String>();
                for(int current = 0; current < depth; current++){
                    traverseLevelOrder(temp, current, FamilyElements);
                }

                Iterator<String> it = levelOrderIterator();
                while(it.hasNext()){
                    System.out.print(it.next() + " ");
                }
            }

        }

        /**
         * This method traverses the tree with the rule of <br>
         * level-order traversing and stores the elements into the<br>
         * given ArrayList.<br>
         * @param temp the tree.<br>
         * @param current current position of the pointer of three.<br>
         * @param FamilyElements ArrayList to store datas.<br>
         */
        private void traverseLevelOrder(Node<String> temp, int current, List<String> FamilyElements){
            if(temp == null)
                return;

            if(current != 0){
                traverseLevelOrder(temp.left, current-1, FamilyElements );
                traverseLevelOrder(temp.right, current-1, FamilyElements);
            }

            else{
                FamilyElements.add(temp.data);
            }
        }
}
