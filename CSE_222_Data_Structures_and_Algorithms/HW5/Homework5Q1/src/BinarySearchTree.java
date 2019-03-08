import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class extends BinaryTree class and<br>
 * generates a binary search tree.<br>
 * @author Hakki Erdem Duman.<br>
 *
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E>{

    /**
     * Depth of the tree.<br>
     */
    private int depth;
    /**
     * ArrayList to store tree's datas.<br>
     */
    private List<E> bstElements;

    /**
     * This method returns an iterator class object.<br>
     * @return iterator class object.<br>
     *
     */
    public Iterator<E> levelOrderIterator() {
        return new IterClass<E>();
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
        private int indexBst;

        /**
         * This constructor initializes index to zero.<br>
         */
        public IterClass() {
            indexBst = 0;
        }

        /**
         * This method is overridden from Iterator class and <br>
         * checks whether the tree is traversed or not.<br>
         * @return false if the tree is traversed already.<br>
         */
        @Override
        public boolean hasNext() {
            return indexBst < bstElements.size();
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
            E willBeReturned = (E) bstElements.get(indexBst);
            indexBst++;
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
     * This constructor calls the father class' constructor<br>
     * and initializes depth to zero.<br>
     */
    public BinarySearchTree(){
        super();
        depth = 0;
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
        Node<E> temp = new Node<E>();
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
            Node<E> temp = new Node<E>();
            temp = root;
            bstElements = new ArrayList<E>();
            for(int current = 0; current < depth; current++){
                traverseLevelOrder(temp, current, bstElements);
            }

            Iterator<E> it = levelOrderIterator();
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
     * @param bstElements ArrayList to store datas.<br>
     */
    private void traverseLevelOrder(Node<E> temp, int current, List<E> bstElements){
        if(temp == null)
            return;

        if(current != 0){
            traverseLevelOrder(temp.left, current-1, bstElements );
            traverseLevelOrder(temp.right, current-1, bstElements);
        }

        else{
            bstElements.add(temp.data);
        }
    }

}
