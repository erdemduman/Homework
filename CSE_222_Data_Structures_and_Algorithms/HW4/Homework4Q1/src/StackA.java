import java.util.ArrayList;

/**
 * This StackA class implements push, pop, isEmpty <br>
 * and size methods of StackInterface and extends ArrayList <br>
 * class to implement a stack. <br>
 * @author Hakki Erdem Duman <br>
 *
 */

class StackA <E> extends ArrayList<E> implements StackInterface<E>{

    /**
     * This constructor calls the ArrayList's one.<br>
     */
    public StackA(){
        super();
    }

    /**
     * This method adds the given element to the ArrayList.<br>
     * @param data given element.<br>
     * @return the element that is added.<br>
     */
    @Override
    public E push(E data){
        add(data);
        return data;
    }

    /**
     * This method removes the last element of the ArrayList.<br>
     * @return the element that is removed.<br>
     * @throws MyException whether size of the stack is 0.<br>
     */
    @Override
    public E pop()throws MyException{
        int sizeOfList = size();
        E theLastElement = get(sizeOfList-1);
        if(isEmpty()){
            throw new MyException("Can not delete because the list is empty");
        }
        else{
            remove(sizeOfList-1);
            return theLastElement;
        }
    }

    /**
     * This method checks whether the stack is empty.<br>
     * @return true if it is empty, false if not.<br>
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else
            return false;
    }

    /**
     * This method detects the size of stack.<br>
     * @return size of stack.<br>
     */
    @Override
    public int size(){
        return super.size();
    }
}

