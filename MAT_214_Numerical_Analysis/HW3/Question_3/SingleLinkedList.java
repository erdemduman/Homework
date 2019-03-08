/**
 * This class has a list and deleted list. List stores the <br>
 * datas and deleted list stores the deleted datas. If needed,<br>
 * nodes of deleted datas will be manupilated and used again.<br>
 *
 * @author Hakki Erdem Duman<br>
 */
public class SingleLinkedList<E> {
    /**
     *This class keeps the data and the node for linked list.<br>
     */
    private class MyNode{
        private E data;
        private MyNode next;
    }

    private MyNode list = new MyNode();
    private int sizeOfList;


    /**
     * This constructor initialized size of the list<br>
     * and size of the deleted list to zero.<br>
     *
     */
    public SingleLinkedList(){
        sizeOfList = 0;
    }

    /**
     * This method takes an unknown parameter and adds it<br>
     * to the linked list.<br>
     *
     * @param paramData data that will be added.<br>
     */
    public void append(E paramData){


        if(sizeOfList <= 0){
            list.data = paramData;
            list.next = null;
            sizeOfList++;
        }
        else {
            MyNode tmp = new MyNode();
            tmp = list;
            for (int a = 0; a < sizeOfList - 1; a++) {
                tmp = tmp.next;
            }
            tmp.next = new MyNode();
            tmp = tmp.next;
            tmp.data = paramData;
            tmp.next = null;
            sizeOfList++;
        }
    }


    /**
     * This method takes an index as parameter and<br>
     * returns the data of the linked list at the given index.<br>
     * @param index index.<br>
     * @return data at the given index.<br>
     * @throws MyException throws a unique exception whether the size is invalid.<br>
     *
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

    public void set(E value,int index) throws MyException{

        MyNode tmp = new MyNode();

        if(index < 0 || index >= sizeOfList)
            throw new MyException("Invalid index.");
        else{

            tmp = list;
            for(int a = 0; a < index; a++) {
                tmp = tmp.next;
            }
            tmp.data = value;
        }

    }



    /**
     * This method is overridden from the Object class and<br>
     * returns the datas of the linkedlist.<br>
     * @return datas of linkedlist.<br>
     */
    @Override
    public String toString(){
        String willBeReturned = "";
        try {
            for (int a = 0; a < sizeOfList; a++) {
                willBeReturned += "Data [" + a + "]: " + get(a) + "\n";
            }
        }

        catch(MyException e){
            System.out.println(e);
        }

        return willBeReturned;
    }

    public int size(){
        return sizeOfList;
    }

}


