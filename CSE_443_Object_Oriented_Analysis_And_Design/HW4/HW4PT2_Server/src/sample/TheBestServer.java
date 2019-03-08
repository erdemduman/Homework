package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Erdem Duman<br>
 * Server of the program.<br>
 * @param <T> Type of graph elements<br>
 */
public class TheBestServer<T> extends UnicastRemoteObject implements RMInterface<T> {

    /**
     * Constructor that matches super.<br>
     * @throws RemoteException exception<br>
     */
    public TheBestServer() throws RemoteException {
        super();
    }

    /**
     * This method sends incidence matrix to the client.<br>
     * @param graph given graph<br>
     * @param name name of client<br>
     * @return incidence matrix<br>
     * @throws RemoteException exception<br>
     */
    @Override
    public Integer[][] incidenceMatrix(Graph<T> graph, String name) throws RemoteException{
        GraphOperations<T> go = new GraphOperations<>(graph);
        System.out.println(name + " is connected.");
        System.out.println("Incidence Matrix is being calculated...");
        return go.incidenceMatrix();
    }

    /**
     * This method sends mst to the client.<br>
     * @param graph given graph<br>
     * @param name name of client<br>
     * @return mst of graph<br>
     * @throws RemoteException exception<br>
     */
    @Override
    public Integer minimumSpanningTree(Graph<T> graph, String name) throws RemoteException{
        GraphOperations<T> go = new GraphOperations<>(graph);
        System.out.println(name + " is connected.");
        System.out.println("Minimum Spanning Tree is being calculated...");
        return go.minimumSpanningTree();
    }

}
