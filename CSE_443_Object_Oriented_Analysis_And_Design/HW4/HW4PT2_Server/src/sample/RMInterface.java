package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Erdem Duman<br>
 * This interface is implemented by server.<br>
 * @param <T> type of graph<br>
 */
public interface RMInterface<T> extends Remote {
    Integer[][] incidenceMatrix(Graph<T> graph, String name) throws RemoteException;
    Integer minimumSpanningTree(Graph<T> graph, String name) throws RemoteException;

}