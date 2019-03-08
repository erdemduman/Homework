import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Queue;
import java.util.LinkedList;

/**
 * This class makes the file operations and tests the stacks.<br>
 * @author Hakki Erdem Duman <br>
 */
public class FileOperations {

    private myQueue<String> iterationTry = null;
    private myQueue<String> recursionTry = null;
    private Queue<String> queueObj = null;

    /**
     * This constructor creates 2 references from a classes.<br>
     * with string because string type can handle everything.<br>
     */
    public FileOperations() {
    }

    /**
     * This method calls a method to read and write. <br>
     */
    public void startProg(){

        readFromFile("test.csv","testResult_2.csv");
    }

    /**
     * This method reads the given file and stores the<br>
     * datas to queues to write them in a test file.<br>
     *
     *
     * @param filename the file that will be read.<br>
     * @param otherFile file to write results.<br>
     */
    private void readFromFile(String filename,String otherFile){

        BufferedReader readObj = null;
        FileWriter writeObj = null;
        final String COMMA = ",";
        final String NEWLINE = "\n";

        try{
            readObj = new BufferedReader(new FileReader(filename));
            writeObj = new FileWriter(otherFile,false);

            String aLine = "";

            while((aLine = readObj.readLine()) != null) {

                iterationTry = new myQueue<String>();
                recursionTry = new myQueue<String>();
                queueObj = new LinkedList<String>();

                String[] tokens = aLine.split(COMMA);

                for (String str : tokens) {
                    iterationTry.addLast(str);
                    queueObj.add(str);
                }

                iterationTry.reverse();
                String elements = iterationTry.toString();
                Integer mySize = iterationTry.size;

                String[] elementTokens = elements.split(COMMA);

                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(elementTokens[a]);
                    writeObj.append(COMMA);
                }
                writeObj.append(elementTokens[mySize-1]);
                writeObj.append(NEWLINE);

                recursionTry.reverseQueue(queueObj);
                String[] tempArr = new String[queueObj.size()];

                int counter = 0;

                while(!queueObj.isEmpty()){
                    tempArr[counter] = queueObj.poll();
                    counter++;
                }

                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(tempArr[a]);
                    writeObj.append(COMMA);
                }
                writeObj.append(tempArr[mySize-1]);
                writeObj.append(NEWLINE);
            }

        }
        catch(Exception e){
            System.out.println("File does not exist.");
        }
        finally{
            try{
                writeObj.flush();
                writeObj.close();
                readObj.close();

            }
            catch(Exception e){
                System.out.println("File could not been closed.");
            }
        }
    }
}
