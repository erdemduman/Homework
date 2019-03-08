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
     * ArrayList to store tree's datas.<br>
     */
    private List<E> bstElements;

    /**
     * This method returns an iterator class object.<br>
     * @return iterator class object.<br>
     *
     */
    public Iterator<E> inOrderIterator() {
        return new IterClass<E>();
    }
    /**
     * This inner class implements Iterator class and <br>
     * traverses on a tree (in - order). <br>
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
     * This constructor calls the father class' constructor.<br>
     *
     */
    public BinarySearchTree(){
        super();
    }

    /**
     * This method creates a temp node and ArrayList<br>
     * to send them to the same method with arguments.<br>
     * Also traverses in returned ArrayList, which stores the elements<br>
     * of Tree with the rule of in-order traversing.<br>
     */
    @Override
    public void traverseIterator(){
        bstElements = new ArrayList<E>();
        Node<E> temp = new Node<E>();
        temp = root;
        traverseIterator(temp, bstElements);

        /*Iterator<E> it = inOrderIterator();
        while(it.hasNext()){
            System.out.println(it.next() + " ");
        }*/
    }

    /**
     * This method traverse the tree with the rule of in - order traversing.<br>
     * @param temp the tree.<br>
     *
     * @param bstElements list to store datas of tree.<br>
     */
    public void traverseIterator(Node<E> temp, List<E> bstElements) {

        if (temp != null) {
            traverseIterator(temp.left, bstElements);
            bstElements.add(temp.data);
            traverseIterator(temp.right, bstElements);
        }
    }

    /**
     * This method returns the list of elements.<br>
     * @return list of elements<br>
     */
    public List<E> returnTree(){
        return bstElements;
    }

    /**
     * This method deletes the smallest element of the tree and<br>
     * returns it.
     * @return the smallest element.<br>
     */
    public E deleteFirst(){

        E willBeDeleted = bstElements.get(0);
        bstElements.remove(0);
        root = null;
        root = new Node<E>();
        flag = 0;
        for(E each : bstElements){
            add(each);
        }

        return willBeDeleted;
    }

    /**
     * This method deletes the largest element of the tree and<br>
     * returns it.
     * @return the largest element.<br>
     */
    public E deleteLast(){

        E willBeDeleted = bstElements.get(bstElements.size()-1);
        bstElements.remove(bstElements.size()-1);
        root = null;
        root = new Node<E>();
        flag = 0;
        for(E each : bstElements){
            add(each);
        }

        return willBeDeleted;

    }
}
