/**
 * This StackC class implements push, pop, isEmpty <br>
 * and size methods of StackInterface class to implement <br>
 * a stack. Creates an object of the Node class while doing <br>
 * that.<br>
 * @author Hakki Erdem Duman <br>
 */
public class StackC<E> implements StackInterface<E> {

    private Node<E> list = null;
    private int sizeOfList;

    /**
     * This constructor initialized the size information to<br>
     * zero and creates a node for "list" variable<br>
     */
    public StackC(){
        list = new Node<E>();
        sizeOfList = 0;
    }

    /**
     * This method adds the given element to the ArrayList.<br>
     * @param paramData given element.<br>
     * @return the element that is added.<br>
     */
    @Override
    public E push(E paramData){
        list.setData(list,paramData,sizeOfList);
        sizeOfList++;
        return paramData;
    }

    /**
     * This method removes the last element of the ArrayList.<br>
     * @return the element that is removed.<br>
     * @throws MyException whether size of the stack is 0.<br>
     */
    @Override
    public E pop()throws MyException{

        E lastElement = null;

        if(isEmpty()) {
            throw new MyException("List is empty.");
        }

        else{
            lastElement = list.removeData(list,sizeOfList);
            sizeOfList--;
        }

        return lastElement;
    }

    /**
     * This method detects the size of stack.<br>
     * @return size of stack.<br>
     */
    @Override
    public int size(){
        return sizeOfList;
    }

    /**
     * This method checks whether the stack is empty.<br>
     * @return true if it is empty, false if not.<br>
     */
    @Override
    public boolean isEmpty(){
        if(size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
