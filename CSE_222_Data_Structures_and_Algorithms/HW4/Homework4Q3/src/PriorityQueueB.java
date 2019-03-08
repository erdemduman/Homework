import java.util.LinkedList;

/**
 * This class implements a Priority Class with creating<br>
 * an object of the LinkedList class.<br>
 * @author Hakki Erdem Duman <br>
 */

public class PriorityQueueB<E>{

    LinkedList<E> list = null;

    /**
     * This constructor creates the object of LinkedList.<br>
     */
    public PriorityQueueB(){
        list = new LinkedList<E>();
    }

    /**
     * This method adds an element to the Queue<br>
     * @param data data to be added.<br>
     */
    public void insert(E data){list.add(data);}

    /**
     * This method returns the size of queue.<br>
     * @return size of queue.<br>
     */
    public int size(){return list.size();}

    /**
     * This method checks whether the queue is empty.<br>
     * @return true whether the queue is empty.<br>
     */
    public boolean isEmpty(){
        if(size() == 0)
            return true;
        else
            return false;
    }

    /**
     * This method deletes the smallest element in queue.<br>
     * While doing this, every element in queue has been casted<br>
     * to string before compared.<br>
     * @return the element that will be removed.<br>
     * @throws MyException whether the queue is empty.<br>
     */
    public E deleteMin()throws MyException{

        E returnable = null;

        if(isEmpty())
            throw new MyException("Queue has nothing to remove.");
        else{
            int smallest = 0;
            for(int a = 1; a < size(); a++){
                if(list.get(a).toString().compareTo(list.get(smallest).toString()) <= 0){
                    smallest = a;
                }
            }
            returnable = list.get(smallest);
            list.remove(smallest);
        }

        return returnable;
    }
}
