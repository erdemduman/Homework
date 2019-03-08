package sample;

import java.io.Serializable;

/**
 * @author Erdem Duman<br>
 * This interface is implemented by EdgeImpl class.<br>
 * @param <T> type of graph.<br>
 */
public interface Edge<T> extends Serializable {
    T getSource();
    T getDest();
    int getWeight();
    boolean isEqual(Edge<T> e);
}
