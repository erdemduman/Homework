import java.util.LinkedList;

/**
 * This class implements a Priority Class with extending<br>
 * LinkedList class.<br>
 * @author Hakki Erdem Duman <br>
 */
public class PriorityQueueA<E> extends LinkedList<E> {

    /**
     * This constructor calls the LinkedList's one.<br>
     */
    public PriorityQueueA(){
        super();
    }

    /**
     * This method adds an element to the Queue<br>
     * @param data data to be added.<br>
     */
    public void insert(E data){
        add(data);
    }

    /**
     * This method returns the size of queue.<br>
     * @return size of queue.<br>
     */
    @Override
    public int size(){return super.size();}

    /**
     * This method checks whether the queue is empty.<br>
     * @return true whether the queue is empty.<br>
     */
    @Override
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
                if(get(a).toString().compareTo(get(smallest).toString()) <= 0){
                    smallest = a;
                }
            }
            returnable = get(smallest);
            remove(smallest);
        }

        return returnable;
    }

}
