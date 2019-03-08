import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This class makes all of the file operations<br>
 * like reading from a file and writing to a file.<br>
 * @author Hakki Erdem Duman<br>
 */
public class FileOperations<E> {

    private String filename;

    /**
     * This constructor gets a filename to manipulate. <br>
     * @param fileName filename.<br>
     */
    public FileOperations(String fileName){
        filename = fileName;
    }

    /**
     * This method writes to a file. Uses the data field of<br>
     * filename to open a file. <br>
     */
    public void writeFoo(){
        FileWriter writeObj = null;
        try {
            writeObj = new FileWriter(filename, false);

            for (int a = 0; a < 10000; a++) {
                writeObj.append(new Integer(a).toString());
                writeObj.append("\n");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                writeObj.flush();
                writeObj.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * This method reads a file and stores the datas to<br>
     * a linked list.<br>
     * @param testList linked list that will be manupilated.<br>
     */
    public void readFoo(myStringBuilder<E> testList){

        BufferedReader readObj = null;
        String line = "";
        try{
            readObj = new BufferedReader(new FileReader(filename));
            while((line = readObj.readLine()) != null){
                Integer tempInt = Integer.parseInt(line);
                testList.append((E) tempInt);
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                readObj.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * This function also writes a file, but that file is special<br>
     * for toString methods, so this method has been imlemented.<br>
     * @param fileName filename<br>
     * @param report information of the linked list.<br>
     */
    public void reportToFile(String fileName, String report){
        FileWriter writeObj = null;
        try {
            writeObj = new FileWriter(fileName, false);
            writeObj.append(report);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                writeObj.flush();
                writeObj.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
