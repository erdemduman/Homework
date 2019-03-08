
/**
 * This StackD class implements push, pop, isEmpty <br>
 * and size methods of StackInterface and uses a queue <br>
 * structure to implement a stack. <br>
 * @author Hakki Erdem Duman <br>
 */
public class StackD<E> implements StackInterface<E> {

    private Queue<E> myQueue = null;

    /**
     * This constructor creates an object of the Queue.<br>
     */
    public StackD(){
        myQueue = new Queue<E>();
    }

    /**
     * This method adds the given element to the queue.<br>
     * @param data given element.<br>
     * @return the element that is added.<br>
     */
    @Override
    public E push(E data){
        myQueue.add(data);
        return data;
    }

    /**
     * This method detects the size of stack.<br>
     * @return size of stack.<br>
     */
    @Override
    public int size(){
        return myQueue.size();
    }

    /**
     * This method checks whether the stack is empty.<br>
     * @return true if it is empty, false if not.<br>
     */
    @Override
    public boolean isEmpty(){
        if(myQueue.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method stores the queue to an array and<br>
     * make the delete operation into the array before <br>
     * storing it again.<br>
     * @return the element that is removed.<br>
     * @throws MyException whether size of the queue is 0.<br>
     */
    @Override
    public E pop()throws MyException{

        E obj = null;
        int sizeOfQueue = size();
        E[] tmp = (E[])new Object[size()];

        for(int a = 0; a < sizeOfQueue; a++){
            tmp[a] = myQueue.poll();
            /*System.out.println("tmp: " + tmp[a]);*/
        }

        obj = tmp[sizeOfQueue-1];
        sizeOfQueue--;

        for(int a = 0; a < sizeOfQueue; a++){
            myQueue.add(tmp[a]);
        }

        return obj;
    }
}
