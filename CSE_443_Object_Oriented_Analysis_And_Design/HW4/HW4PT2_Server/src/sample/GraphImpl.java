package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erdem Duman<br>
 * This class implements Graph interface and graph methods.<br>
 * @param <T> type of graph<br>
 */
public class GraphImpl<T> implements Graph<T> {

    private List<Edge<T>> edgeList;

    /**
     * Constructor<br>
     */
    public GraphImpl(){
        edgeList = new ArrayList<>();
    }

    /**
     * This method returns number of edges<br>
     * @return number of edges<br>
     */
    @Override
    public int getNumV() {
        return edgeList.size();
    }

    /**
     * This method inserts the given edge into the edge list.<br>
     * @param edge given edge<br>
     */
    @Override
    public void insert(Edge<T> edge) {
        boolean flag = true;

        for(int i = 0; i < edgeList.size(); i++){
            if(edgeList.get(i).isEqual(edge))
                flag = false;
        }

        if(flag)
            edgeList.add(edge);

    }

    /**
     * This method deletes the given edge from the edge list.<br>
     * @param edge given edge<br>
     */
    @Override
    public void delete(Edge<T> edge) {
        boolean flag = true;

        for(int i = 0; i < edgeList.size(); i++){
            if(edgeList.get(i).isEqual(edge))
                edgeList.remove(edgeList.get(i));
        }

    }

    /**
     * This method checks whether there is a given edge in <br>
     * edge list<br>
     * @param source source of edge<br>
     * @param dest destination of edge<br>
     * @return true whether there is an edge like source-dest. False otherwise<br>
     */
    @Override
    public boolean isEdge(T source, T dest) {
        for(int i = 0; i < edgeList.size(); i++) {
            if(edgeList.get(i).getSource().equals(source) && edgeList.get(i).getDest().equals(dest))
                return true;
        }

        return false;
    }

    /**
     * This method returns all sources of edge list<br>
     * @return sources<br>
     */
    @Override
    public List<T> getAllSources() {
        List<T> sources = new ArrayList<>();

        for(int i = 0; i < edgeList.size(); i++){

            if(i == 0)
                sources.add(edgeList.get(i).getSource());

            else{
                if(!(sources.contains(edgeList.get(i).getSource()))){
                    sources.add(edgeList.get(i).getSource());
                }
            }

        }

        return sources;
    }

    /**
     * This method returns all sources and destinations of edge list<br>
     * @return sources and dest<br>
     */
    @Override
    public List<T> getSourceAndDest() {
        List<T> sou = getAllSources();
        List<T> dest = getAllDest();
        List<T> souAndDest = sou;

        for(int i = 0; i < dest.size(); i++){
            if(!(souAndDest.contains(dest.get(i)))){
                souAndDest.add(dest.get(i));
            }
        }

        return souAndDest;

    }

    /**
     * This method returns all destinations of edge list<br>
     * @return dest<br>
     */
    @Override
    public List<T> getAllDest() {
        List<T> dest = new ArrayList<>();

        for(int i = 0; i < edgeList.size(); i++){

            if(i == 0)
                dest.add(edgeList.get(i).getDest());

            else{
                if(!(dest.contains(edgeList.get(i).getDest()))){
                    dest.add(edgeList.get(i).getDest());
                }
            }

        }

        return dest;
    }

    /**
     * This method returns the desired edge.<br>
     * @param source source of edge<br>
     * @param dest destination of edge<br>
     * @return desired edge<br>
     */
    @Override
    public Edge getEdge(T source, T dest) {

        Edge<T> temp = new EdgeImpl<T>(source, dest);

        for(int i = 0; i < edgeList.size(); i++){
            if(edgeList.get(i).isEqual(temp))
                return edgeList.get(i);
        }

        return null;
    }

    /**
     * This method returns all edges.<br>
     * @return the edge list<br>
     */
    @Override
    public List<Edge<T>> getAllEdges(){
        return edgeList;
    }

    /**
     * This method returns edge list after sorted it<br>
     * with bubble sort according to edges' weights.<br>
     * @return sorted edge list<br>
     */
    @Override
    public List<Edge<T>> getAllEdgesWithSortedWeight() {

        List<Edge<T>> sortedEdgeList = this.edgeList;

        for(int i = 0; i < sortedEdgeList.size() - 1; i++){
            for(int j = 0; j < sortedEdgeList.size()-i-1; j++){
                if(sortedEdgeList.get(j).getWeight() > sortedEdgeList.get(j+1).getWeight()){
                    Edge<T> tmp = sortedEdgeList.get(j);
                    sortedEdgeList.set(j, sortedEdgeList.get(j+1));
                    sortedEdgeList.set(j+1, tmp);
                }
            }
        }

        return sortedEdgeList;
    }
}
