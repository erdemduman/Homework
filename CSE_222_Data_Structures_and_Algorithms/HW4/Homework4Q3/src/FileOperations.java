import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This class makes the file operations and tests the stacks.<br>
 * @author Hakki Erdem Duman <br>
 */
public class FileOperations {

    private PriorityQueueA<String> priorityQueueA = null;
    private PriorityQueueB<String> priorityQueueB = null;

    /**
     * This constructor creates 2 references from 2 classes.<br>
     * with string because string type can handle everything.<br>
     */
    public FileOperations() {

        priorityQueueA = new PriorityQueueA<String>();
        priorityQueueB = new PriorityQueueB<String>();
    }

    /**
     * This method calls a method to read and write. <br>
     */
    public void startProg(){

        readFromFile("test.csv","testResult_3.csv");
    }

    /**
     * This method reads the given file and stores the<br>
     * datas to stacks to write them in a test file.<br>
     * Also calculates the run time of all<br>
     * the stacks. <br>
     * @param filename the file that will be read.<br>
     * @param otherFile file to write results.<br>
     */
    private void readFromFile(String filename,String otherFile){

        BufferedReader readObj = null;
        FileWriter writeObj = null;
        final String COMMA = ",";
        final String NEWLINE = "\n";
        long durationA = 0;
        long durationB = 0;

        try{
            readObj = new BufferedReader(new FileReader(filename));
            writeObj = new FileWriter(otherFile,false);

            String aLine = "";

            while((aLine = readObj.readLine()) != null) {

                String[] tokens = aLine.split(COMMA);

                for (String str : tokens) {
                    priorityQueueA.insert(str);
                    priorityQueueB.insert(str);
                }

                long startTime = System.nanoTime();

                Integer mySize = priorityQueueA.size();

                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(priorityQueueA.deleteMin().toString());
                    writeObj.append(COMMA);
                }
                writeObj.append(priorityQueueA.deleteMin().toString());
                writeObj.append(NEWLINE);

                long endtime = System.nanoTime();

                durationA += endtime - startTime;

                startTime = System.nanoTime();

                mySize = priorityQueueB.size();

                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(priorityQueueB.deleteMin().toString());
                    writeObj.append(COMMA);
                }
                writeObj.append(priorityQueueB.deleteMin().toString());
                writeObj.append(NEWLINE);

                endtime = System.nanoTime();

                durationB += endtime - startTime;
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
                System.out.println("PriorityQueueA: " + durationA + " nanoseconds.");
                System.out.println("PriorityQueueB: " + durationB + " nanoseconds.");

            }
            catch(Exception e){
                System.out.println("File could not been closed.");
            }
        }
    }
}
