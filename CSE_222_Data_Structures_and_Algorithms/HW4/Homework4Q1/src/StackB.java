import java.util.*;
/**
 * This StackB class implements push, pop, isEmpty <br>
 * and size methods of StackInterface class to implement <br>
 * a stack. Creates an object of the ArrayList while doing <br>
 * that.<br>
 * @author Hakki Erdem Duman <br>
 */
public class StackB<E> implements StackInterface<E> {

    private ArrayList<E> myList = new ArrayList<E>();

    /**
     * This method adds the given element to the ArrayList.<br>
     * @param data given element.<br>
     * @return the element that is added.<br>
     */
    @Override
    public E push(E data){
        myList.add(data);
        return data;
    }

    /**
     * This method removes the last element of the ArrayList.<br>
     * @return the element that is removed.<br>
     * @throws MyException whether size of the stack is 0.<br>
     */
    @Override
    public E pop()throws MyException{
        int sizeOfList = myList.size();
        E theLastElement = myList.get(sizeOfList-1);
        if(isEmpty()){
            throw new MyException("Can not delete because the list is empty");
        }
        else{
            myList.remove(sizeOfList-1);
            return theLastElement;
        }
    }

    /**
     * This method checks whether the stack is empty.<br>
     * @return true if it is empty, false if not.<br>
     */
    @Override
    public boolean isEmpty(){
        if(myList.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method detects the size of stack.<br>
     * @return size of stack.<br>
     */
    @Override
    public int size(){
        return myList.size();
    }



}
