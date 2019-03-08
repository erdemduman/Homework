/**
 * This class implements a queue data structure.<br>
 * @author Hakki Erdem Duman <br>
 */
public class Queue<E>{

    private E[] queueArr = null;
    private int sizeOfQueue;

    /**
     * This constructor creates a generic array to start.<br>
     */
    public Queue(){
        queueArr = (E[]) new Object[1];
        sizeOfQueue = 0;
    }

    /**
     * Add method adds the given data to the queue.<br>
     * @param paramData given data<br>
     */
    public void add(E paramData){
        E[] tmpArr = (E[]) new Object[sizeOfQueue+1];
        for(int a = 0; a < sizeOfQueue; a++){
            tmpArr[a] = queueArr[a];
        }
        sizeOfQueue++;
        queueArr = (E[]) new Object[sizeOfQueue+1];
        for(int a = 0; a < sizeOfQueue-1; a++){
            queueArr[a] = tmpArr[a];
        }
        queueArr[sizeOfQueue-1] = paramData;

    }

    /**
     * This method deletes the first element from queue.<br>
     * @return deleted data.<br>
     * @throws MyException whether the queue is empty.<br>
     */
    public E poll()throws MyException{

        E returnable = null;

        if(size() == 0){
            throw new MyException("There is nothing to delete.");
        }
        else{
            returnable = queueArr[0];
            E[] tmpArr = (E[]) new Object[sizeOfQueue+1];
            for(int a = 0; a < sizeOfQueue; a++){
                tmpArr[a] = queueArr[a];
            }
            sizeOfQueue--;
            queueArr = (E[]) new Object[sizeOfQueue+1];
            for(int a = 0; a < sizeOfQueue; a++){
                queueArr[a] = tmpArr[a+1];
            }
        }

        return returnable;
    }

    public int size(){
        return sizeOfQueue;
    }
}