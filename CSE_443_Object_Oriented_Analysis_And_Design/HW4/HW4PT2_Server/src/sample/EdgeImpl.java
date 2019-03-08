package sample;

/**
 * @author Erdem Duman<br>
 * This class implements Edge interface.<br>
 * @param <T> type of graph<br>
 */
public class EdgeImpl<T> implements Edge<T> {
    private T source;
    private T dest;
    private int weight;

    /**
     * Constructor that generates an edge.<br>
     * @param source source of edge<br>
     * @param dest dest of edge<br>
     * @param weight weight of edge<br>
     */
    public EdgeImpl(T source, T dest, int weight){
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Constructor that generates an edge with fixed weight, which is one.<br>
     * @param source source of edge<br>
     * @param dest dest of edge<br>
     */
    public EdgeImpl(T source, T dest){
        this.source = source;
        this.dest = dest;
        this.weight = 1;
    }

    /**
     * This method returns source of edge<br>
     * @return source of edge<br>
     */
    @Override
    public T getSource() {
        return source;
    }

    /**
     * This method returns dest of edge<br>
     * @return dest of edge<br>
     */
    @Override
    public T getDest() {
        return dest;
    }

    /**
     * This method returns weight of edge<br>
     * @return weight of edge<br>
     */
    @Override
    public int getWeight(){
        return weight;
    }

    /**
     * This method checks whether given edge is equal to this object.<br>
     * @param e given edge<br>
     * @return true whether they are equal. False otherwise.<br>
     */
    @Override
    public boolean isEqual(Edge e){
        if(this.getSource() == e.getSource() && this.getDest() == e.getDest())
            return true;

        return false;
    }

    /**
     * toString method<br>
     *
     * @return information of edge.<br>
     */
    @Override
    public String toString(){
        return "(" + this.source + "," + this.dest + ")" + " [" + this.weight + "]";
    }


}
