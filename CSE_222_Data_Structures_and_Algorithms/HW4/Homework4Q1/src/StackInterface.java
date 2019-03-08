/**
 * This interface will be implemented by StackA,<br>
 * StackB, StackC, StackD classes.<br>
 * @author Hakki Erdem Duman<br>
 */
public interface StackInterface<E> {
    public E push (E data);
    public E pop ()throws MyException;
    public boolean isEmpty();
    public int size();
}
