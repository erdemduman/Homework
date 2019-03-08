package sample;

/**
 * @author Erdem Duman<br>
 * Main class<br>
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    /**
     * In this method, server is started.<br>
     * @param args main arguments<br>
     */
    public static void main(String[] args) {
        Registry registry;
        try {
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind("server", new TheBestServer<String>());
            System.out.println("Server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
