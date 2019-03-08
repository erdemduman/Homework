import java.util.ArrayList;

/**
 * @author Hakki Erdem Duman.<br>
 */
public class HashTableChaining<K, V> implements HashMap<K, V> {

    /** The table */
    private HashTableOpen<Entry<K, V>>[] table;

    /** number of keys*/
    private int keyNumber;

    //Do not forget you can use more class and methods to do this homework,
    // this project gives you an initial classes an methods to see easily
    //....
    //.... other class members

    /**
     * This inner class stores an ArrayList to make chaining.<br>
     * @param <E> entry
     */
    private class HashTableOpen<E>{
        /**arraylist*/
        private ArrayList<E> arr;

        public HashTableOpen(){
            arr = new ArrayList<E>();
        }

        /**
         * This method returns the arraylist.<br>
         * @return the arraylist.<br>
         */
        public ArrayList<E> getArray(){return arr;}

        /**
         * This method returns the entry of the given index.<br>
         * @param index given index.<br>
         * @return entry of the given index.<br>
         */
        public E getIndex(int index){return arr.get(index);}

        /**
         * This method insert the given value into the given index.<br>
         * @param index the given index.<br>
         * @param value the given value.<br>
         */
        public void setArray(int index, E value){arr.add(value);}
    }

    /**
     * This inner class keeps the datafield of key and value.<br>
     * @param <K> key
     * @param <V> value
     */
    private class Entry<K,V>{

        private K key;

        private V value;

    }

    public HashTableChaining(){
        table = new HashTableOpen[10];
        keyNumber = 0;
    }

    /**
     * This method returns the value of the given key.<br>
     * @param key given key.<br>
     * @return value of the given key.<br>
     */
    @Override
    public V get(Object key) {
       int index = hashCodeGenerator((K)key);
       int helperVar = whereIs((K)key);

       if(index < 0){
           index += table.length;
       }

       if(helperVar < 0){
           return null;
       }

       else{
           return table[index].getIndex(helperVar).value;
       }
    }

    /**
     * This method inserts the given entry into the hash table.<br>
     * @param key key of entry
     * @param value value of entry
     * @return value of the inserted entry.<br>
     */
    @Override
    public V put(K key, V value) {

        int index = hashCodeGenerator(key);
        Entry<K,V> willBeAdded = new Entry<K,V>();
        V returnVal = null;

        willBeAdded.key = key;
        willBeAdded.value = value;

        if(index < 0)
            index += table.length;

        int keyFoundIn = whereIs(key);

        if(keyFoundIn == -1){
            table[index] = new HashTableOpen<Entry<K,V>>();
            table[index].setArray(0,willBeAdded);
            ++keyNumber;
        }

        else if(keyFoundIn == -2){
            int innerIndex = table[index].getArray().size();
            table[index].setArray(innerIndex-1,willBeAdded);
            ++keyNumber;
        }

        else{
            V old = table[index].getIndex(keyFoundIn).value;
            table[index].getIndex(keyFoundIn).value = value;
            returnVal = old;
        }

        return returnVal;
    }

    /**
     * This method removes the entry of given key.<br>
     * @param key the given key.<br>
     * @return deleted element.<br>
     */
    @Override
    public V remove(Object key) {
        int index = hashCodeGenerator((K)key);
        int helperVar = whereIs((K)key);

        if(index < 0){
            index += table.length;
        }

        if(helperVar < 0){
            return null;
        }

        else{
            V retVal = table[index].getIndex(helperVar).value;
            table[index].getArray().remove(helperVar);
            --keyNumber;
            return retVal;
        }
    }

    /**
     * This method returns the number of keys.<br>
     * @return the number of keys.<br>
     */
    @Override
    public int size() {
        return keyNumber;
    }

    /**
     * This method checks whether the hash table is empty.<br>
     * @return true if the hash table is empty.<br>
     */
    @Override
    public boolean isEmpty() {
        if(size() > 0)
            return false;
        else
            return true;
    }

    /**
     * This method checks whether the given key is in the hash table.<br>
     * @param paramKey the given key.<br>
     * @return -2 if the given key is not in hash table,<br>
     *         -1 if the given key's index is not even created,<br>
     *          the index of key if the given key is in the hash table.<br>
     */
    private int whereIs(K paramKey){

        int index = hashCodeGenerator(paramKey);
        int returnIndex = -2;

        if(index < 0)
            index += table.length;

        if(table[index] == null)
            returnIndex = -1;

        if(returnIndex != -1){
            for(int a = 0; a < table[index].getArray().size(); a++){
                if(table[index].getIndex(a).key.equals(paramKey)){
                    returnIndex = a;
                }
            }
        }

        return returnIndex;
    }

    /**
     * Generates hash code.<br>
     * @param paramKey the given key.<br>
     * @return hash code.<br>
     */
    private int hashCodeGenerator(K paramKey){
        return paramKey.hashCode()%table.length;
    }

    @Override
    public String toString(){
        String str = "";
        for(int a = 0; a < table.length; a++){
            if(table[a] != null){
                for(int b = 0; b < table[a].getArray().size(); b++){
                    str += "\nTable["+a+"]" + " --- " + " " + b + ".Element" + " --- " + " Key: " + table[a].getIndex(b).key + ", Value: " + table[a].getIndex(b).value;
                }
            }
        }

        return str;
    }
}
