import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class have a method to read integers from<br>
 * the given file.<br>
 * @author Hakki Erdem Duman.<br>
 *
 */
public class FileOperations{

    /**
     * This method reads integers from the given file and<br>
     * returns an ArrayList which is filled with these integers.<br>
     * @param filename file that will be read.<br>
     * @return ArrayList.<br>
     */
    public List<Integer> readFile(String filename){

        BufferedReader readObj = null;
        List<Integer> storage = null;

        try{
            String line = "";
            storage = new ArrayList<Integer>();
            readObj = new BufferedReader(new FileReader(filename));

            while((line = readObj.readLine())!= null){
                String[] tokens = line.split(" ");
                for(String each : tokens){
                    storage.add(Integer.parseInt(each));
                }
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
