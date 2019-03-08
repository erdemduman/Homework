import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class have a method to read integers from<br>
 * the given file.<br>
 * @author Erdem Duman.<br>
 *
 */
public class FileOperations{

    public FileOperations(){
        System.out.println("File reader is ready to read.");
    }

    public ArrayList<ArrayList<Double>> readFile(String filename){

        System.out.println("File is being read...");
        int counter = 0;
        BufferedReader readObj = null;
        ArrayList<ArrayList<Double>> storage = null;

        try{
            String line = "";
            storage = new ArrayList<ArrayList<Double>>();
            readObj = new BufferedReader(new FileReader(filename));
            while((line = readObj.readLine())!= null){

                String[] tokens = line.split(",");
                for(String each : tokens){
                    storage.add(new ArrayList<Double>());
                    storage.get(counter).add(Double.parseDouble(each));
                }
                counter++;
            }
        }

        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                readObj.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return storage;
    }
}