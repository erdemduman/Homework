import java.util.Iterator;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/** Class for a binary tree that stores type E objects.<br>
 *
*   @author Koffman and Wolfgang<br>
* */

public class BinaryTree < E extends Comparable<E>> implements Serializable, Iterable<E>{

  /**
   * This method returns an iterator class object.<br>
   * @return iterator class object.
   */
  @Override
  public Iterator<E> iterator() {
    return new IterClass<E>();
  }

  /**
   * This inner class implements Iterator class and <br>
   * traverses on a tree (pre - order). <br>
   *
   */
  private class IterClass<E> implements Iterator<E>{

    /**
     * This data field stores the current index in tree.<br>
     */
    private int index;

    /**
     * This constructor initializes index to zero.<br>
     */
    public IterClass(){
      index = 0;
    }

    /**
     * This method is overridden from Iterator class and <br>
     * checks whether the tree is traversed or not.<br>
     * @return false if the tree is traversed already.<br>
     */
    @Override
    public boolean hasNext() {
      return index < treeElements.size();
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
      E willBeReturned = (E) treeElements.get(index);
      index++;
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

  /** Class to encapsulate a tree node. */
  protected static class Node < E >
      implements Serializable {
    // Data Fields
    /** The information stored in this node. */
    protected E data;

    /** Reference to the left child. */
    protected Node < E > left;

    /** Reference to the right child. */
    protected Node < E > right;

    /**
     * Default constructor. <br>
     */
    protected Node() {}
  }

  // Data Field
  /** The root of the binary tree */
  protected Node < E > root;

  /**
   * ArrayList to store tree's datas.<br>
   */
  protected List<E> treeElements;

  /**
   * To check whether there is a tree.<br>
   * If the flag is zero, that means <br>
   * the tree has not been created yet.<br>
   */
  protected int flag;

  /**
   * This constructor creates a new node for the root.<br>
   */
  public BinaryTree() {
    root = new Node<E>();
    flag = 0;
  }

  /**
   * This method adds the given data to tree WITH THE ORDER.<br>
   * OF BINARY SEARCH TREE.<br>
   * @param data the data that will be added.<br>
   * @return true if it is succeed.<br>
   */
  public boolean add(E data){

    if(flag == 0){
      root.data = data;
      root.left = null;
      root.right = null;
      flag = 1;
    }

    else{
      Node < E > temp = new Node<E>();
      temp = root;
      boolean breaker = false;
      while(!breaker){

        if(data.compareTo(temp.data) <= 0){
          if(temp.left != null)
            temp = temp.left;
          else{
            temp.left = new Node<E>();
            temp = temp.left;
            temp.data = data;
            temp.left = null;
            temp.right = null;
            breaker = true;
          }
        }
        else{
          if(temp.right != null)
            temp = temp.right;
          else{
            temp.right = new Node<E>();
            temp = temp.right;
            temp.data = data;
            temp.left = null;
            temp.right = null;
            breaker = true;
          }
        }
      }
    }

    return true;
  }

  /**
   * This method creates a temp node and ArrayList<br>
   * to send them to the same method with arguments.<br>
   * Also traverses in returned ArrayList, which stores the elements<br>
   * of Tree with the rule of pre-order traversing.<br>
   */
  public void traverseIterator(){
    treeElements = new ArrayList<E>();
    Node<E> temp = new Node<E>();
    temp = root;
    traverseIterator(temp, treeElements);

    Iterator<E> it = iterator();
    while(it.hasNext()){
      System.out.print(it.next() + " ");
    }
  }

  /**
   * This method traverses the tree with the rule of <br>
   * pre-order traversing and stores the elements into the<br>
   * given ArrayList.<br>
   * @param temp the tree.<br>
   *
   * @param treeElements ArrayList to store datas.<br>
   */
  public void traverseIterator(Node<E> temp, List<E> treeElements){

    if(temp == null)
      return;
    else{
      treeElements.add(temp.data);
      traverseIterator(temp.left, treeElements);
      traverseIterator(temp.right, treeElements);
    }
  }
}
