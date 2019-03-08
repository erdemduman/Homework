import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class have a method to read characters and their<br>
 * weights from the given file.<br>
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
    public HuffmanTree.HuffData[] readFile(String filename){

        HuffmanTree obj = new HuffmanTree();

        BufferedReader readObj = null;
        List<HuffmanTree.HuffData> storage = null;
        HuffmanTree.HuffData[] storageArr = null;


        try{
            String line = "";
            storage = new ArrayList<HuffmanTree.HuffData>();
            readObj = new BufferedReader(new FileReader(filename));

            while((line = readObj.readLine())!= null){
                String[] tokens = line.split(" ");
                Character data = tokens[0].charAt(0);
                double value = Double.parseDouble(tokens[1]);
                storage.add(obj.createInner(value,data));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                readObj.close();
                storageArr = new HuffmanTree.HuffData[storage.size()];
                for(int a = 0; a < storage.size(); a++){
                    storageArr[a] = storage.get(a);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return storageArr;
    }
}