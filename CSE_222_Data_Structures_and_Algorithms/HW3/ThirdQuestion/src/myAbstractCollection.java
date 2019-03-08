import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * This class combines to AbstractCollection object.<br>
 * @author Hakki Erdem Duman.<br>
 */
public class myAbstractCollection<E> extends AbstractCollection<E> {

    /**
     * This method gets an AbstractCollection object as parameter <br>
     * and combine it with the owner object.<br>
     * @param other other AbstractCollection object.<br>
     */
    public void appendAnything(AbstractCollection<E> other){
        addAll(other);
    }

    /**
     * This method is overridden to avoid from an error.<br>
     * @return null;
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * This method is overridden to avoid from an error.<br>
     * @return 0;
     */
    @Override
    public int size(){
        return 0;
    }
}
