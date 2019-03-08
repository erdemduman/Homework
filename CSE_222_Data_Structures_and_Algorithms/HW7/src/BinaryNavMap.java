/**
 * Created by syucer on 4/24/2017.
 */
import java.util.*;

/**
 * @author Hakki Erdem Duman.<br>
 *
 */
public class BinaryNavMap<K extends Comparable<K>,V extends Comparable<V>> extends AbstractMap<K,V>
        implements NavigableMap<K,V>, Cloneable, java.io.Serializable
{
    /** Data field*/
    private BinarySearchTree<Entries<K,V>> myBst;

    /** Constructor to create a tree*/
    public BinaryNavMap(){
        myBst = new BinarySearchTree<Entries<K,V>>();
    }

    /**
     * This inner class involves the K and V generic types.<br>
     * @param <K> key.<br>
     * @param <V> value. <br>
     */
    private class Entries<K extends Comparable,V extends Comparable> implements Comparable<Entries<K,V>>,Entry<K,V>{

        private K key;

        private V value;

        @Override
        public int compareTo(Entries<K, V> o) {

            return key.compareTo(o.key);
        }

        @Override
        public String toString(){

            String str = "";

            str += "{" + key + "=" + value + "}";

            return str;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }

    /**
     * This method puts the given entry into the tree.<br>
     * @param paramKey key of entry.<br>
     * @param paramValue value of entry.<br>
     * @return value of the inserted entry.<br>
     */
    @Override
    public V put(K paramKey, V paramValue){
        Entries<K,V> temp = new Entries<K,V>();
        temp.key = paramKey;
        temp.value = paramValue;
        myBst.add(temp);

        return paramValue;
    }

    /**
     * This method returns a set that involves the elements of tree.<br>
     * @return a set that involves the elements of tree.<br>
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();
        Set<Entry<K, V>> tempSet = new TreeSet<Entry<K, V>>();
        for(Entries<K,V> a : tempList){
            tempSet.add(a);
        }

        return tempSet;
    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * strictly less than the given key, or {@code null} if there is
     * no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> lowerEntry(K key) throws NullPointerException{

        if(key == null){
            throw new NullPointerException();
        }

        if(!isThereAnyKey(key)){
            return null;
        }

        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        Entries<K,V> willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) < 0){
                willBeReturned = new Entries<K,V>();
                willBeReturned.key = var.key;
                willBeReturned.value = var.value;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns the greatest key strictly less than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K lowerKey(K key) {

        if(key == null){
            throw new NullPointerException();
        }
        if(!isThereAnyKey(key)){
            return null;
        }
        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        K willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) < 0){
                willBeReturned= var.key;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns a key-value mapping associated with the greatest key
     * less than or equal to the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the greatest key less than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> floorEntry(K key) {

        if(!isThereAnyKey(key)){
            return null;
        }
        if(key == null){
            throw new NullPointerException();
        }

        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        Entries<K,V> willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) <= 0){
                willBeReturned = new Entries<K,V>();
                willBeReturned.key = var.key;
                willBeReturned.value = var.value;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns the greatest key less than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the greatest key less than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K floorKey(K key) {

        if(key == null){
            throw new NullPointerException();
        }

        if(!isThereAnyKey(key)){
            return null;
        }

        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        K willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) <= 0){
                willBeReturned= var.key;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns a key-value mapping associated with the least key
     * greater than or equal to the given key, or {@code null} if
     * there is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than or equal to
     * {@code key}, or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) {

        if(key == null){
            throw new NullPointerException();
        }

        if(!isThereAnyKey(key)){
            return null;
        }

        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        Entries<K,V> willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) >= 0){
                willBeReturned = new Entries<K,V>();
                willBeReturned.key = var.key;
                willBeReturned.value = var.value;
                break;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns the least key greater than or equal to the given key,
     * or {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than or equal to {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K ceilingKey(K key) {

        if(key == null){
            throw new NullPointerException();
        }

        if(!isThereAnyKey(key)){
            return null;
        }

        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        K willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) >= 0){
                willBeReturned= var.key;
                break;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns a key-value mapping associated with the least key
     * strictly greater than the given key, or {@code null} if there
     * is no such key.
     *
     * @param key the key
     * @return an entry with the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public Entry<K, V> higherEntry(K key) {

        if(key == null){
            throw new NullPointerException();
        }

        if(!isThereAnyKey(key)){
            return null;
        }

        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        Entries<K,V> willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) > 0){
                willBeReturned = new Entries<K,V>();
                willBeReturned.key = var.key;
                willBeReturned.value = var.value;
                break;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns the least key strictly greater than the given key, or
     * {@code null} if there is no such key.
     *
     * @param key the key
     * @return the least key greater than {@code key},
     * or {@code null} if there is no such key
     * @throws ClassCastException   if the specified key cannot be compared
     *                              with the keys currently in the map
     * @throws NullPointerException if the specified key is null
     *                              and this map does not permit null keys
     */
    @Override
    public K higherKey(K key) {

        if(key == null){
            throw new NullPointerException();
        }

        if(!isThereAnyKey(key)){
            return null;
        }

        List<Entries<K,V>> tempList = new ArrayList<Entries<K,V>>();
        K willBeReturned = null;
        myBst.traverseIterator();
        tempList = myBst.returnTree();

        for(Entries<K,V> var : tempList){
            if(var.key.compareTo(key) > 0){
                willBeReturned= var.key;
                break;
            }
        }

        return willBeReturned;
    }

    /**
     * Returns a key-value mapping associated with the least
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the least key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> firstEntry() {
        myBst.traverseIterator();
        List<Entries<K,V>> tempBst = myBst.returnTree();

        if(tempBst.size() <= 0)
            return null;

        return tempBst.get(0);

    }

    /**
     * Returns a key-value mapping associated with the greatest
     * key in this map, or {@code null} if the map is empty.
     *
     * @return an entry with the greatest key,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> lastEntry() {


        myBst.traverseIterator();
        List<Entries<K,V>> tempBst = myBst.returnTree();

        if(tempBst.size() <= 0)
            return null;

        return tempBst.get(tempBst.size()-1);
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the least key in this map, or {@code null} if the map is empty.
     *
     * @return the removed first entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollFirstEntry() {

        myBst.traverseIterator();
        List<Entries<K,V>> tempBst = myBst.returnTree();

        if(tempBst.size() <= 0)
            return null;

        Entries<K,V> willBeReturned = myBst.deleteFirst();

        return willBeReturned;
    }

    /**
     * Removes and returns a key-value mapping associated with
     * the greatest key in this map, or {@code null} if the map is empty.
     *
     * @return the removed last entry of this map,
     * or {@code null} if this map is empty
     */
    @Override
    public Entry<K, V> pollLastEntry() {

        myBst.traverseIterator();
        List<Entries<K,V>> tempBst = myBst.returnTree();

        if(tempBst.size() <= 0)
            return null;

        Entries<K,V> willBeReturned = myBst.deleteLast();

        return willBeReturned;
    }

    /**
     * Returns a reverse order view of the mappings contained in this map.
     * The descending map is backed by this map, so changes to the map are
     * reflected in the descending map, and vice-versa.  If either map is
     * modified while an iteration over a collection view of either map
     * is in progress (except through the iterator's own {@code remove}
     * operation), the results of the iteration are undefined.
     * <p>
     * <p>The returned map has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code m.descendingMap().descendingMap()} returns a
     * view of {@code m} essentially equivalent to {@code m}.
     *
     * @return a reverse order view of this map
     */
    @Override
    public NavigableMap<K,V> descendingMap() {
        myBst.traverseIterator();
        NavigableMap<K,V> navMap = new BinaryNavMap<K, V>();
        List<Entries<K,V>> tempList = myBst.returnTree();
        int index = tempList.size()-1;
        for(int a = index; a >= 0; a--){
            navMap.put(tempList.get(a).key,tempList.get(a).value);
        }

        return navMap;
    }

    /**
     * Returns a {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in ascending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> navigableKeySet() {

        NavigableSet<K> navSet = new TreeSet<K>();
        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();

        for(Entries<K,V> each : tempList){
            navSet.add(each.key);
        }

        return navSet;
    }

    /**
     * Returns a reverse order {@link NavigableSet} view of the keys contained in this map.
     * The set's iterator returns the keys in descending order.
     * The set is backed by the map, so changes to the map are reflected in
     * the set, and vice-versa.  If the map is modified while an iteration
     * over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear} operations.
     * It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a reverse order navigable set view of the keys in this map
     */
    @Override
    public NavigableSet<K> descendingKeySet() {
        return null;
    }

    /**
     * Returns a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}.  If {@code fromKey} and
     * {@code toKey} are equal, the returned map is empty unless
     * {@code fromInclusive} and {@code toInclusive} are both true.  The
     * returned map is backed by this map, so changes in the returned map are
     * reflected in this map, and vice-versa.  The returned map supports all
     * optional map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside of its range, or to construct a
     * submap either of whose endpoints lie outside its range.
     *
     * @param fromKey       low endpoint of the keys in the returned map
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toKey         high endpoint of the keys in the returned map
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this map whose keys range from
     * {@code fromKey} to {@code toKey}
     * @throws ClassCastException       if {@code fromKey} and {@code toKey}
     *                                  cannot be compared to one another using this map's comparator
     *                                  (or, if the map has no comparator, using natural ordering).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} or {@code toKey}
     *                                  cannot be compared to keys currently in the map.
     * @throws NullPointerException     if {@code fromKey} or {@code toKey}
     *                                  is null and this map does not permit null keys
     * @throws IllegalArgumentException if {@code fromKey} is greater than
     *                                  {@code toKey}; or if this map itself has a restricted
     *                                  range, and {@code fromKey} or {@code toKey} lies
     *                                  outside the bounds of the range
     */
    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)
        throws NullPointerException, IllegalArgumentException{

        if(fromKey == null || toKey == null)
            throw new NullPointerException();

        if(isThereAnyKey(fromKey) == false || isThereAnyKey(toKey) == false)
            throw new IllegalArgumentException();

        if(fromKey.compareTo(toKey) > 0){
            throw new IllegalArgumentException();
        }

        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();
        NavigableMap<K,V> navMap = new TreeMap<K, V>();
        int a = 0;
        boolean flag = false;
        while(true) {
            if (tempList.get(a).key.equals(fromKey)) {
                if (fromInclusive == true) {
                    navMap.put(tempList.get(a).key, tempList.get(a).value);
                }
                a++;
                while(true){
                    if(toKey.equals(tempList.get(a).key)){
                        if(toInclusive == true)
                            navMap.put(tempList.get(a).key, tempList.get(a).value);
                        flag = true;
                        break;
                    }
                    else{
                        navMap.put(tempList.get(a).key, tempList.get(a).value);
                    }
                    a++;
                }
            }

            if(flag == true)
                break;

            a++;
        }

        return navMap;

    }

    /**
     * Returns a view of the portion of this map whose keys are less than (or
     * equal to, if {@code inclusive} is true) {@code toKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param toKey     high endpoint of the keys in the returned map
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are less than
     * (or equal to, if {@code inclusive} is true) {@code toKey}
     * @throws ClassCastException       if {@code toKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code toKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code toKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code toKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code toKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive)
            throws NullPointerException, IllegalArgumentException {

        if(toKey == null)
            throw new NullPointerException();

        if(isThereAnyKey(toKey) == false)
            throw new IllegalArgumentException();


        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();
        NavigableMap<K,V> navMap = new TreeMap<K, V>();
        int a = 0;

        while(true) {
            if(toKey.equals(tempList.get(a).key)){
                if(inclusive == true)
                    navMap.put(tempList.get(a).key, tempList.get(a).value);
                break;
            }
            else{
                navMap.put(tempList.get(a).key, tempList.get(a).value);
                a++;
            }

        }

        return navMap;
    }

    /**
     * Returns a view of the portion of this map whose keys are greater than (or
     * equal to, if {@code inclusive} is true) {@code fromKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     * <p>
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param fromKey   low endpoint of the keys in the returned map
     * @param inclusive {@code true} if the low endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this map whose keys are greater than
     * (or equal to, if {@code inclusive} is true) {@code fromKey}
     * @throws ClassCastException       if {@code fromKey} is not compatible
     *                                  with this map's comparator (or, if the map has no comparator,
     *                                  if {@code fromKey} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromKey} cannot be compared to keys
     *                                  currently in the map.
     * @throws NullPointerException     if {@code fromKey} is null
     *                                  and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a
     *                                  restricted range, and {@code fromKey} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive)throws NullPointerException,IllegalArgumentException {

        if(fromKey == null)
            throw new NullPointerException();

        if(isThereAnyKey(fromKey) == false)
            throw new IllegalArgumentException();


        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();
        NavigableMap<K,V> navMap = new TreeMap<K, V>();
        int a = 0;
        boolean flag = false;
        while(true) {
            if (fromKey.equals(tempList.get(a).key)) {
                if (inclusive == true)
                    navMap.put(tempList.get(a).key, tempList.get(a).value);
                a++;
                while (true) {
                    if (a == tempList.size()) {
                        flag = true;
                        break;
                    }
                    navMap.put(tempList.get(a).key, tempList.get(a).value);
                    a++;
                }
            }
            if (flag)
                break;

            a++;
        }

        return navMap;
    }

    /**
     * Returns the comparator used to order the keys in this map, or
     * {@code null} if this map uses the {@linkplain Comparable
     * natural ordering} of its keys.
     *
     * @return the comparator used to order the keys in this map,
     * or {@code null} if this map uses the natural ordering
     * of its keys
     */
    @Override
    public Comparator<? super K> comparator() {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code subMap(fromKey, true, toKey, false)}.
     *
     * @param fromKey
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) throws NullPointerException, IllegalArgumentException{

        if(fromKey == null || toKey == null)
            throw new NullPointerException();

        if(isThereAnyKey(fromKey) == false || isThereAnyKey(toKey) == false)
            throw new IllegalArgumentException();

        if(fromKey.compareTo(toKey) > 0){
            throw new IllegalArgumentException();
        }

        return subMap(fromKey,true,toKey,true);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code headMap(toKey, false)}.
     *
     * @param toKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> headMap(K toKey) throws NullPointerException,IllegalArgumentException{
        if(toKey == null)
            throw new NullPointerException();

        if(isThereAnyKey(toKey) == false)
            throw new IllegalArgumentException();

        return headMap(toKey,true);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Equivalent to {@code tailMap(fromKey, true)}.
     *
     * @param fromKey
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedMap<K, V> tailMap(K fromKey)throws NullPointerException,IllegalArgumentException {

        if(fromKey == null)
            throw new NullPointerException();

        if(isThereAnyKey(fromKey) == false)
            throw new IllegalArgumentException();

        return tailMap(fromKey,true);
    }

    /**
     * Returns the first (lowest) key currently in this map.
     *
     * @return the first (lowest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K firstKey()throws NoSuchElementException {
        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();
        if(tempList.size() == 0){
            throw new NoSuchElementException();
        }

        return tempList.get(0).key;
    }

    /**
     * Returns the last (highest) key currently in this map.
     *
     * @return the last (highest) key currently in this map
     * @throws NoSuchElementException if this map is empty
     */
    @Override
    public K lastKey() {
        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();
        if(tempList.size() == 0){
            throw new NoSuchElementException();
        }

        return tempList.get(tempList.size()-1).key;
    }

    @Override
    public String toString(){
        String str = "";
        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();
        str += "{";
        for(Entries<K,V> each : tempList){
            str += each.key + "=" + each.value + ", ";
        }
        str = str.substring(0,str.length()-2);
        str += "}";

        return str;

    }

    /**
     * Checks whether the given key is in the tree.<br>
     * @param paramKey the key that will be checked.<br>
     * @return true if the key is in, false if the key is not in.<br>
     */
    private boolean isThereAnyKey(K paramKey){

        boolean flag = false;
        myBst.traverseIterator();
        List<Entries<K,V>> tempList = myBst.returnTree();

        for(Entries<K,V> each : tempList){
            if(each.key.equals(paramKey))
                flag = true;
        }

        return flag;
    }
}