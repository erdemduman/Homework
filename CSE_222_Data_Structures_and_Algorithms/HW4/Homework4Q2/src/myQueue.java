import java.util.ListIterator;
import java.util.Queue;

/**
 * This class extends KWLinkedList and involves <br>
 * some methods to reverse datas.<br>
 * @author Hakki Erdem Duman
 */
public class myQueue<E> extends KWLinkedList<E>{


    public myQueue(){
        super();
    }

    /**
     * This method reverses the elements of the queue.<br>
     */
    public void reverse(){
        ListIterator<E> firstElement = listIterator(0);
        ListIterator<E> lastElement = listIterator(size);
        E first;
        E last;

        for(int a = 0; a < size / 2; a++){
            first = firstElement.next();
            last = lastElement.previous();
            firstElement.previous();
            firstElement.set(last);
            lastElement.next();
            lastElement.set(first);
            firstElement.next();
            lastElement.previous();
        }
    }
    /**
     * This method reverses the elements of the queue recursively.<br>
     * @param willBeReversed queue object that will be reversed.<br>
     */
    public void reverseQueue(Queue<E> willBeReversed){

        E element = null;

        if(willBeReversed.size() == 0){
            return;
        }
        else{
            element = willBeReversed.poll();
            reverseQueue(willBeReversed);
            willBeReversed.add(element);
        }
    }

    @Override
    public String toString(){
        String report = "";
        ListIterator<E> firstElement = listIterator(0);
        for(int a = 0; a < size-1; a++){
            report += firstElement.next() + ",";
        }
        report += firstElement.next();
        return report;
    }

}
