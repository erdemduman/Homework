import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class have a method to read strings from<br>
 * the given file.<br>
 * @author Hakki Erdem Duman.<br>
 *
 */
public class FileOperations{

    /** Variable to get first line*/
    private int lineCounter;
    /** first line of file*/
    private String nameOfRoot;

    /**
     * This constructor initializes the data fields.<br>
     */
    public FileOperations(){
        lineCounter = 0;
        nameOfRoot = "";
    }

    /**
     *This method returns the root name.<br>
     * @return root name.<br>
     */
    public String getRoot(){
        return nameOfRoot;
    }

    /**
     * This method reads Strings from the given file and<br>
     * returns an ArrayList which is filled with these Strings.<br>
     * @param filename file that will be read.<br>
     * @throws MyException if there are no 3 elements in a line.<br>
     * @return ArrayList.<br>
     */
    public List<String> readFile(String filename) throws MyException{

        BufferedReader readObj = null;
        List<String> storage = null;

        try{
            String line = "";
            storage = new ArrayList<String>();
            readObj = new BufferedReader(new FileReader(filename));
            while((line = readObj.readLine())!= null){
                if(lineCounter == 0){
                    nameOfRoot = line;
                }
                else{
                    String[] tokens = line.split(",");
                    if(tokens.length%3 != 0){
                        throw new MyException("There is a trouble with reading file.");
                    }
                    for(String each : tokens){
                        each = each.trim();
                        storage.add(each);
                    }
                }

                lineCounter++;
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