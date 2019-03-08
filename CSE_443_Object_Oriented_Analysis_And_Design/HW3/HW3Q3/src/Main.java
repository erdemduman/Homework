import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author Erdem Duman<br>
 * This is the main class of program.<br>
 */
public class Main {

    /**
     * This is the main method of the program. In this method,<br>
     * a file that involves a 2D array is opened and this array<br>
     * is sent to ContainerConcrete class to be iterated.<br>
     * @param args main arguments<br>
     */
    public static void main(String[] args) {
        Object[][] arr;
        String file = "array.txt";
        int x = 0;
        int y = 0;
        BufferedReader br = null;
        Container container;
        Scanner scanner = null;
        try{
            scanner = new Scanner(System.in);
            br =  new BufferedReader(new FileReader(file));
            String[] token;
            String line;
            while ((line = br.readLine()) != null) {
                token = line.split(",");
                y = token.length;
                x++;
            }
            arr = new Object[x][y];
            br.close();
            br = new BufferedReader(new FileReader(file));
            int x_count = 0;
            while ((line = br.readLine()) != null) {
                token = line.split(",");
                for(int i = 0; i < y; i++){
                    arr[x_count][i] = Integer.parseInt(token[i]);
                }
                x_count++;
            }

            container = new ContainerConcrete(arr);
            Iterator it = container.getIterator();
            while (it.hasNext()){
                System.out.print(it.next() + " ");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                System.out.println("<Press Enter to Finish>");
                scanner.nextLine();
                br.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
