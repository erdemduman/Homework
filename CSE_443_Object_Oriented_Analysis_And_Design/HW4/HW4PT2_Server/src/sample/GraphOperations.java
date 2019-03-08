package sample;

import java.util.List;

/**
 * @author Erdem Duman<br>
 * This class has the methods that does graph operations<br>
 * that are done by server.<br>
 * @param <T> type of graph<br>
 */
public class GraphOperations<T> {
    Graph<T> graph;

    /**
     * Constructor<br>
     * @param graph the given graph<br>
     */
    public GraphOperations(Graph<T> graph){
        this.graph = graph;
    }

    /**
     * This method calculates and returns incidence matrix.<br>
     * @return incidence matrix of given graph<br>
     */
    public Integer[][] incidenceMatrix(){
        Integer [][] incMatrix = null;

        List<T> souAndDest = graph.getSourceAndDest();
        incMatrix = new Integer[souAndDest.size()][souAndDest.size()];

        for(int i = 0; i < souAndDest.size(); i++){
            for(int j = 0; j < souAndDest.size(); j++){
                if(graph.isEdge(souAndDest.get(i), souAndDest.get(j)))
                    incMatrix[i][j] = 1;
                else if(graph.isEdge(souAndDest.get(j), souAndDest.get(i)))
                    incMatrix[i][j] = -1;
                else
                    incMatrix[i][j] = 0;
            }
        }

        return incMatrix;
    }

    /**
     * This method calculates and returns mst of graph.<br>
     * @return mst of given graph<br>
     */
    public Integer minimumSpanningTree(){
        Integer result = 0;
        List<T> souAndDest = graph.getSourceAndDest();
        List<Edge<T>> edgeList = graph.getAllEdgesWithSortedWeight();
        boolean[] isVisited = new boolean[souAndDest.size()];

        for(int i = 0; i < souAndDest.size(); i++){
            isVisited[i] = false;
        }

        for(int i = 0; i < edgeList.size(); i++){
            Edge<T> tmpEdge = edgeList.get(i);
            if(isVisited[souAndDest.indexOf(tmpEdge.getSource())] == false || isVisited[souAndDest.indexOf(tmpEdge.getDest())] == false){
                isVisited[souAndDest.indexOf(tmpEdge.getSource())] = true;
                isVisited[souAndDest.indexOf(tmpEdge.getDest())] = true;
                result += tmpEdge.getWeight();
            }
        }

        return result;

    }
}
