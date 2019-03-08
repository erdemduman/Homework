/**
 * This class keep the information of the Nodes (for StackC)<br>
 * and involves the method that can access private members.<br>
 * @author Hakki Erdem Duman
 */
public class Node<E> {

    private E data;
    private Node next;

    /**
     * This method adds the second parameter to the first parameter.<br>
     * We needed this method to access private data members.<br>
     * @param list our linked list.<br>
     * @param paramData data that will be added.<br>
     * @param sizeOfList size of the list. <br>
     */
    public void setData(Node<E> list, E paramData, int sizeOfList){

        if(sizeOfList <= 0){
            list.data = paramData;
            list.next = null;
        }

        else{
            Node<E> tmp = new Node<E>();
            tmp = list;

            for(int a = 0; a < sizeOfList-1; a++) {
                tmp = tmp.next;
            }
            tmp.next = new Node<E>();
            tmp = tmp.next;
            tmp.data = paramData;
            tmp.next = null;
        }
    }

    /**
     * This method removes the last member of stack.<br>
     * We needed this method to access private data members.<br>
     * @param list our linked list.<br>
     * @param sizeOfList size of the list. <br>
     * @return the data that is removed. <br>
     */
    public E removeData(Node<E> list, int sizeOfList){

        E lastElement = null;

        if(sizeOfList == 1){
            lastElement = list.data;
            list = list.next;
            list = new Node<E>();
        }

        else{
            Node<E> tmp = new Node<E>();
            tmp = list;

            for(int a = 0; a < sizeOfList-2; a++) {
                tmp = tmp.next;
            }

            lastElement = (E) tmp.next.data;
            tmp.next = null;
        }

        return lastElement;
    }

}
