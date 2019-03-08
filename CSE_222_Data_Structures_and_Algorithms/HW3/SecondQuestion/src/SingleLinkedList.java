/**
 * This class involves a linked list and <br>
 * methods to get the job done.<br>
 */
public class SingleLinkedList<E>{

    /**
     * Data and next of linked list.<br>
     */
    private class MyNode{
        private E data;
        private MyNode next;
    }

    private MyNode list = new MyNode();
    private MyNode tempList = new MyNode();
    private int sizeOfList;
    private int counter;
    private String reversedOne = "";

    /**
     * This constructor initialized data fields of sizes.<br>
     */
    public SingleLinkedList(){
        sizeOfList = 0;
        counter = -1;
    }

    /**
     * This method appends the given data to the linked list.<br>
     * @param paramData the given data.<br>
     */
    public void append(E paramData){
        if(sizeOfList <= 0){

            list.data = paramData;
            list.next = null;
            tempList = list;
            sizeOfList++;
            counter++;
        }
        else{
            tempList.next = new MyNode();
            tempList = tempList.next;
            tempList.data = paramData;
            tempList.next = null;
            sizeOfList++;
            counter++;
        }

    }

    /**
     * This method returns the data at the given index.<br>
     * @param index given index.<br>
     * @return data at the given index.<br>
     * @throws MyException throws exception whether the given index is invalid.<br>
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
     * This method returns the datas of the linked list from tail to head.<br>
     * @return datas of the linked list.<br>
     */
    public String reverseToString(){

        if(counter < 0)
            return reversedOne;
        else{
            try{
                reversedOne += get(counter) + " ";
                counter--;
            }
            catch(MyException e){
                System.out.println(e);
            }

            return reverseToString();
        }


    }

    /**
     * Typical toString method to return datas of linked list. <br>
     * @return datas of the linked list.<br>
     */
    @Override
    public String toString(){
        String line = "";
        try{
            for(int a = 0; a < sizeOfList; a++){
                line += get(a) + " ";
            }
        }
        catch (MyException e){
            System.out.println(e);
        }

        return line;
    }



}
