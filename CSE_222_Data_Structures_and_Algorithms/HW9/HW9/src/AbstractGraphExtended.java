import java.io.FileWriter;
import java.util.Iterator;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Created by Hakki Erdem Duman on 16/05/17.
 */
public abstract class AbstractGraphExtended extends AbstractGraph {

    public AbstractGraphExtended(int numV, boolean directed) {
        super(numV, directed);
    }

    @Override
    public void insert(Edge edge) {

    }

    @Override
    public boolean isEdge(int source, int dest) {
        return false;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        return null;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return null;
    }

    /**
     * This method selects a random number between 0 and edgeLimit then adds that much edges to calling<br>
     * graph. The source and destination vertices of edges are random as well.<br>
     * The inserted edges are
     * directed or undirected according to calling graph.<br>
     * @param edgeLimit upper bound of edge number that will be added.<br>
     * @return added edge number.<br>
     */
    public int addRandomEdgesToGraph(int edgeLimit) {

        Random random = new Random();
        int edgeNumber = random.nextInt(edgeLimit);
        int edgeTemp = edgeNumber;

        for (int a = 0; a < edgeTemp; a++) {

            int randomSource = random.nextInt(getNumV());
            int randomDest = random.nextInt(getNumV());

            if (isEdge(randomSource, randomDest) || randomSource == randomDest)
                edgeTemp++;

            else
                insert(new Edge(randomSource, randomDest));
        }

        return edgeNumber;
    }

    /**
     * This method prints the number of undirected graphs.<br>
     * @return an empty graph array.<br>
     */
    public Graph [] getConnectedComponentUndirectedGraph(){

        Graph[] graphArr = null;

        if(isDirected()){
            System.out.println("Graph must be undirected.");
            return null;
        }

        else{
            int [] parentArr = breadthFirstSearch();
            int sourceNumber = 0;
            for(int a = 0; a < parentArr.length; a++) {
                if (parentArr[a] == -1)
                    sourceNumber++;
            }

            graphArr = new Graph[sourceNumber];

            System.out.print("There are " + sourceNumber + " unconnected graphs.\n");
        }

        return graphArr;
    }

    /**
     * This method returns true if calling graph instance is bipartite graph, false otherwise.<br>
     * @return true if calling graph instance is bipartite graph, false otherwise.<br>
     *
     */
    public boolean isBipartiteUndirectedGraph() {

        if(isDirected()){
            System.out.println("Graph must be undirected.");
        }

        else{
            int [] graphArr = breadthFirstSearch();

            for(int a = 0; a < graphArr.length; a++){
                if(graphArr[a] == -1){
                    if(!isBipartiteUndirectedGraph(a)){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * This one is a helper method to find out whether the graph is bipartite or not.<br>
     * @param start starting vertex.<br>
     * @return true if calling graph instance is bipartite graph, false otherwise.<br>
     */
    public boolean isBipartiteUndirectedGraph (int start){

        int sign[] = new int[getNumV()];
        for (int a=0; a<getNumV(); ++a)
            sign[a] = -1;

        sign[start] = 1;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);

        while(!queue.isEmpty()){

            int currentEdge = queue.poll();

            for(int a = 0; a < getNumV(); a++){

                if(isEdge(currentEdge,a) && sign[a] == -1){
                    sign[a] = 1 - sign[currentEdge];
                    queue.add(a);
                }

                else if (isEdge(currentEdge,a) && sign[a]==sign[currentEdge])
                    return false;
            }
        }

        return true;
    }

    /**
     * This method finds whether a reversed version of the given edge exist in the<br>
     * given ArrayList.<br>
     * @param edge the edge that will be checked.<br>
     * @param arrList the given arrayList.<br>
     * @return false if the reversed version of the edge exist, otherwise it returns true.<br>
     */
    public boolean findArrayList(Edge edge, ArrayList<Edge> arrList){

        for(int a = 0; a < arrList.size(); a++){
            if(arrList.get(a).getSource() == edge.getDest() && arrList.get(a).getDest() == edge.getSource()){
                return false;
            }
        }
        return true;
    }

    /**
     * This method writes number of vertices,<br>
     * source and destination vertex of each edge in a file.<br>
     * @param fileName the file that will be manipulated.<br>
     */
    public void writeGraphToFile(String fileName) {

        FileWriter obj = null;
        ArrayList<Edge> allEdges = null;

        try{
            Integer num = getNumV();
            obj = new FileWriter(fileName,false);
            obj.write(num.toString() + "\n");
            allEdges = new ArrayList<Edge>();
            for (int sou = 0; sou < getNumV(); sou++) {

                Iterator<Edge> iter = edgeIterator(sou);

                while (iter.hasNext()) {
                    Edge currentEdge = iter.next();
                    allEdges.add(currentEdge);
                }
            }

            Edge temp;

            for(int a = 1; a < allEdges.size(); a++){
                for(int b = 0; b < allEdges.size()-a; b++){
                    if(allEdges.get(b).getSource() == allEdges.get(b+1).getSource()){
                        if(allEdges.get(b).getDest() > allEdges.get(b+1).getDest()){
                            temp = allEdges.get(b);
                            allEdges.set(b,allEdges.get(b+1));
                            allEdges.set(b+1,temp);
                        }
                    }
                }
            }

            if(isDirected()){
                for(Edge each: allEdges){
                    obj.write(each.getSource() + " " + each.getDest() + "\n");
                }
            }
            else{
                ArrayList<Edge> lastEdges = new ArrayList<Edge>();
                for(int a = 0; a < allEdges.size(); a++){
                    if(findArrayList(allEdges.get(a),lastEdges)){
                        lastEdges.add(allEdges.get(a));
                    }
                }

                for(Edge each: lastEdges){
                    obj.write(each.getSource() + " " + each.getDest() + "\n");
                }
            }

        }
        catch(Exception e){
            System.out.println("File could not been created.");
        }
        finally{
            try{
                obj.flush();
                obj.close();
            }
            catch(Exception e){
                System.out.println("File could not been closed.");
            }
        }
    }

    /**
     * This method performs a breadth first search on calling graph starting from vertex start and<br>
     * returns an
     * array which contains the predecessor of each vertex in the breadth-first search tree.<br>
     * @param start the starting vertex.<br>
     * @return parent array.<br>
     */
    public int[] breadthFirstSearch(int start){

        int[] parent = new int[getNumV()];
        boolean[]isVisited = new boolean[getNumV()];
        Queue<Integer> queue = new LinkedList();

        for(int a = 0; a < getNumV(); a++){
            parent[a] = -1;
            isVisited[a] = false;
        }

        isVisited[start] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int current = queue.remove();
            Iterator<Edge> iter = edgeIterator(current);

            while(iter.hasNext()){
                Edge currentEdge = iter.next();
                int neighbor = currentEdge.getDest();

                if(!isVisited[neighbor]){
                    isVisited[neighbor] = true;
                    queue.offer(neighbor);
                    parent[neighbor] = current;
                }
            }
        }
        return parent;
    }


    /**
     * This method returns a complete parent array even though there are unconnected graphs.<br>
     *
     * @return parent array.<br>
     */
    public int[] breadthFirstSearch() {

        int[] parent = new int[getNumV()];
        boolean[] isVisited = new boolean[getNumV()];

        for(int a = 0; a < getNumV(); a++){
            parent[a] = -2;
            isVisited[a] = false;
        }

        while(true){
            int check = searchControl(parent);
            if(check == -23){
                break;
            }
            else{
                breadthFirstSearch(check, parent, isVisited);
            }
        }
        return parent;
    }

    /**
     * This method is called as much as unconnected graphs in a graph to return a parent array.<br>
     * @param start starting vertex.<br>
     * @param parent parent array that will be manipulated. <br>
     * @param isVisited isVisited that will be manipulated. <br>
     */
    public void breadthFirstSearch(int start, int[] parent, boolean[] isVisited){

        Queue<Integer> queue = new LinkedList();

        parent[start] = -1;
        isVisited[start] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int current = queue.remove();
            Iterator<Edge> iter = edgeIterator(current);

            while(iter.hasNext()){
                Edge currentEdge = iter.next();
                int neighbor = currentEdge.getDest();

                if(!isVisited[neighbor]){
                    isVisited[neighbor] = true;
                    queue.offer(neighbor);
                    parent[neighbor] = current;
                }
            }
        }
    }

    /**
     * This method searches an array and returns the index of first "-2" that is found.<br>
     * @param arr the given array.<br>
     * @return the index. if there is no "-2" in array, returns -23.<br>
     */
    public int searchControl(int[] arr){

        for(int a = 0; a < arr.length; a++){
            if(arr[a] == -2)
                return a;
        }

        return -23;
    }
}