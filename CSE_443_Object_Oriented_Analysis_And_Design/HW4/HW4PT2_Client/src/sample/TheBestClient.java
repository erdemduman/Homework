package sample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Erdem Duman<br>
 * This class performs the client operations.<br>
 * @param <T> type of graph<br>
 */
public class TheBestClient<T> {

    private Graph<T> graph;
    Registry registry;
    RMInterface<T> rmi;
    String name;

    /**
     * Constructor<br>
     * @param graph client graph<br>
     * @param name client name<br>
     */
    public TheBestClient(Graph<T> graph, String name){
        this.graph = graph;
        this.name = name;
    }

    /**
     * This method helps client to connect server.<br>
     */
    public void connect(){
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            rmi = (RMInterface<T>) registry.lookup("server");
            System.out.println("Connected to Server");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method helps client to get incidence matrix.<br>
     * @return incidence matrix.<br>
     */
    public Integer[][] getIncMatrix(){
        Integer[][] incMatrix = null;
        try {
            incMatrix = rmi.incidenceMatrix(graph, name);

        } catch (Exception e) {
            System.out.println(e);
        }

        return incMatrix;
    }

    /**
     * This method helps client to get mst of graph.<br>
     * @return mst of graph.<br>
     */
    public Integer getMst(){
        Integer mst = null;
        try {
            mst = rmi.minimumSpanningTree(graph, name);

        } catch (Exception e) {
            System.out.println(e);
        }

        return mst;
    }



}