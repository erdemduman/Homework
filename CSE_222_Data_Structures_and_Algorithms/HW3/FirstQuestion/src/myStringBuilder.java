import java.util.Iterator;

/**
 * This class stores a linked list and methods<br>
 * of different styles of toString method.<br>
 * Implements iterable.<br>
 * @author Hakki Erdem Duman<br>
 */
public class myStringBuilder<E> implements Iterable<E>{
    /**
     * Linked list data and next<br>
     */
    private class MyNode{
        private E data;
        private MyNode next;
    }

    private MyNode list = new MyNode();
    private MyNode tempList = new MyNode();
    private int sizeOfList;

    /**
     * This is the iterator to use at toStringWithIterator method.<br>
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator(){
        Iterator<E> it = new Iterator<E>() {
            int a = 0;
            E willBeReturned;

            @Override
            public boolean hasNext() {
                if (a >= 0 && a < sizeOfList)
                    return true;
                else
                    return false;
            }

            @Override
            public E next() {
                try {
                    willBeReturned = get(a);
                    a++;
                } catch (MyException e) {
                    System.out.println(e);
                }

                return willBeReturned;
            }
        };

        return it;
    }

    /**
     * constructor to initialize the size of the list to 0.<br>
     */
    public myStringBuilder(){
        sizeOfList = 0;
    }

    /**
     * This method appends a data to the linked list.<br>
     * @param paramData data.<br>
     */
    public void append(E paramData){
        if(sizeOfList <= 0){

            list.data = paramData;
            list.next = null;
            tempList = list;
            sizeOfList++;
        }
        else{
            tempList.next = new MyNode();
            tempList = tempList.next;
            tempList.data = paramData;
            tempList.next = null;
            sizeOfList++;
        }

    }

    /**
     * This method returns the data at the given index.<br>
     * @param index index value.<br>
     * @return data at the given index.<br>
     * @throws MyException throws exception whether the index is invalid.<br>
     */
    public E get(int index) throws MyException{

        MyNode tmp = new MyNode();

        if(index < 0 || index >= sizeOfList)
            throw new MyException("Invalid index.");
        else{

            tmp = list;
            for(int a = 0; a < index; a++) {
                tmp = tmp.next;
            }
        }

        return tmp.data;

    }

    /**
     * toString method with using index.<br>
     * @return all of datas of linked list.
     */
    public String toStringWithGet(){
        String willBeReturned = "";
        try {
            for (int a = 0; a < sizeOfList; a++) {
                willBeReturned += "Data " + a + " : " + get(a) + "\n";
            }
        }

        catch(MyException e){
            System.out.println(e);
        }

        return willBeReturned;
    }

    /**
     * toString method with using iterator.<br>
     * @return all of datas of linked list.
     */
    public String toStringWithIterator(){
        Iterator<E> it = iterator();
        String willBeReturned  = "";
        int a = 0;
        while(it.hasNext()){
            willBeReturned += "Data " + a + " : " + it.next() + "\n";
            a++;
        }

        return willBeReturned;
    }

    /**
     * Classical toString method that is overridden from <br>
     * Object class.<br>
     * @return all of datas of linked list.<br>
     */
    @Override
    public String toString(){
        String willBeReturned = "";
        try {
            for (int a = 0; a < sizeOfList; a++) {
                willBeReturned += "Data " + a + " : " + get(a) + "\n";
            }
        }

        catch(MyException e){
            System.out.println(e);
        }

        return willBeReturned;
    }

}


