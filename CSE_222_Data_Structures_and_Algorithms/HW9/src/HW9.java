import java.io.File;
import java.util.Scanner;

/**
 * Created by Hakki Erdem Duman on 16/05/17.
 */
public class HW9 {
    public static void main(String[] args){

        AbstractGraphExtended graphList = null;
        AbstractGraphExtended graphMatrix = null;
        File file = null;
        Scanner input = null;

        try{
            file = new File("inputGraph_1.txt");
            input = new Scanner(file);
            graphList = (AbstractGraphExtended) AbstractGraph.createGraph(input, false, "List");
            input = new Scanner(file);
            graphMatrix = (AbstractGraphExtended) AbstractGraph.createGraph(input, false, "Matrix");

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("For graphList and inputGraph_1.txt: \n");

        int[] arr = graphList.breadthFirstSearch(0);
        System.out.print("Starting point is 0: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphList.breadthFirstSearch(2);
        System.out.print("\nStarting point is 2: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphList.breadthFirstSearch(5);
        System.out.print("\nStarting point is 5: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }

        if(graphList.isBipartiteUndirectedGraph()){
            System.out.println("\nIt is bipartite.");
        }
        else{
            System.out.println("\nIt is not bipartite.");
        }

        graphList.getConnectedComponentUndirectedGraph();

        int num = graphList.addRandomEdgesToGraph(5);
        System.out.println(num + " Random edges are added.");
        System.out.println("They have been written to: outputGraph_1_list.txt");
        graphList.writeGraphToFile("outputGraph_1_list.txt");






        System.out.println("\nFor graphMatrix and inputGraph_1.txt: ");

        arr = graphMatrix.breadthFirstSearch(0);
        System.out.print("\nStarting point is 0: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphMatrix.breadthFirstSearch(2);
        System.out.print("\nStarting point is 2: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphMatrix.breadthFirstSearch(5);
        System.out.print("\nStarting point is 5: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }

        if(graphMatrix.isBipartiteUndirectedGraph()){
            System.out.println("\nIt is bipartite.");
        }
        else{
            System.out.println("\nIt is not bipartite.");
        }

        graphMatrix.getConnectedComponentUndirectedGraph();

        num = graphMatrix.addRandomEdgesToGraph(5);
        System.out.println(num + " Random edges are added.");
        System.out.println("They have been written to: outputGraph_1_matrix.txt");
        graphMatrix.writeGraphToFile("outputGraph_1_matrix.txt");






        try{
            file = new File("inputGraph_2.txt");
            input = new Scanner(file);
            graphList = (AbstractGraphExtended) AbstractGraph.createGraph(input, false, "List");
            input = new Scanner(file);
            graphMatrix = (AbstractGraphExtended) AbstractGraph.createGraph(input, false, "Matrix");

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nFor graphList and inputGraph_2.txt: \n");

        arr = graphList.breadthFirstSearch(0);
        System.out.print("Starting point is 0: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphList.breadthFirstSearch(2);
        System.out.print("\nStarting point is 2: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphList.breadthFirstSearch(5);
        System.out.print("\nStarting point is 5: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }

        if(graphList.isBipartiteUndirectedGraph()){
            System.out.println("\nIt is bipartite.");
        }
        else{
            System.out.println("\nIt is not bipartite.");
        }

        graphList.getConnectedComponentUndirectedGraph();

        num = graphList.addRandomEdgesToGraph(5);
        System.out.println(num + " Random edges are added.");
        System.out.println("They have been written to: outputGraph_2_list.txt");
        graphList.writeGraphToFile("outputGraph_2_list.txt");


        System.out.println("\nFor graphMatrix and inputGraph_2.txt: ");

        arr = graphMatrix.breadthFirstSearch(0);
        System.out.print("\nStarting point is 0: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphMatrix.breadthFirstSearch(2);
        System.out.print("\nStarting point is 2: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }
        arr = graphMatrix.breadthFirstSearch(5);
        System.out.print("\nStarting point is 5: ");
        for(int a: arr){
            System.out.print(a + "   ");
        }

        if(graphMatrix.isBipartiteUndirectedGraph()){
            System.out.println("\nIt is bipartite.");
        }
        else{
            System.out.println("\nIt is not bipartite.");
        }

        graphMatrix.getConnectedComponentUndirectedGraph();

        num = graphMatrix.addRandomEdgesToGraph(5);
        System.out.println(num + " Random edges are added.");
        System.out.println("They have been written to: outputGraph_2_matrix.txt");
        graphMatrix.writeGraphToFile("outputGraph_2_matrix.txt");
    }
}
