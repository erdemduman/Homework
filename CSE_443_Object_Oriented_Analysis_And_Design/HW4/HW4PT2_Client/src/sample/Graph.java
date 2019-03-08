package sample;

import java.io.Serializable;
import java.util.List;

/**
 * @author Erdem Duman<br>
 * This interface is implemented by GraphImpl class.<br>
 * @param <T> type of graph<br>
 */
public interface Graph<T> extends Serializable {
    int getNumV();

    void insert(Edge<T> edge);

    boolean isEdge(T source, T dest);

    List<T> getAllSources();

    List<T> getSourceAndDest();

    List<T> getAllDest();

    void delete(Edge<T> edge);

    Edge getEdge(T source, T dest);

    List<Edge<T>> getAllEdges();

    List<Edge<T>> getAllEdgesWithSortedWeight();
}
