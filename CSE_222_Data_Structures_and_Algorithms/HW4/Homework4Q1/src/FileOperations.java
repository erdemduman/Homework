import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This class makes the file operations and tests the stacks.<br>
 * @author Hakki Erdem Duman <br>
 */

public class FileOperations {

    private StackInterface<String> stackA = null;
    private StackInterface<String> stackB = null;
    private StackInterface<String> stackC = null;
    private StackInterface<String> stackD = null;

    /**
     * This constructor creates 4 references from 4 classes.<br>
     * with string because string type can handle everything.<br>
     */
    public FileOperations(){

        stackA = new StackA<String>();
        stackB = new StackB<String>();
        stackC = new StackC<String>();
        stackD = new StackD<String>();
    }

    /**
     * This method calls a method to read and write. <br>
     */
    public void startProg(){

        readFromFile("test.csv","testResult_1.csv");
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
        long durationC = 0;
        long durationD = 0;

        try{
            readObj = new BufferedReader(new FileReader(filename));
            writeObj = new FileWriter(otherFile,false);

            String aLine = "";

            while((aLine = readObj.readLine()) != null) {

                String[] tokens = aLine.split(COMMA);

                for (String str : tokens) {
                    stackA.push(str);
                    stackB.push(str);
                    stackC.push(str);
                    stackD.push(str);
                }

                long startTime = System.nanoTime();

                Integer mySize = stackA.size();

                writeObj.append("Size:" + mySize.toString());
                writeObj.append(COMMA);
                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(stackA.pop().toString());
                    writeObj.append(COMMA);
                }
                writeObj.append(stackA.pop().toString());
                writeObj.append(NEWLINE);

                long endtime = System.nanoTime();

                durationA += endtime - startTime;

                startTime = System.nanoTime();

                mySize = stackB.size();

                writeObj.append("Size:" + mySize.toString());
                writeObj.append(COMMA);
                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(stackB.pop().toString());
                    writeObj.append(COMMA);
                }
                writeObj.append(stackB.pop().toString());
                writeObj.append(NEWLINE);

                endtime = System.nanoTime();

                durationB += endtime - startTime;

                startTime = System.nanoTime();

                mySize = stackC.size();

                writeObj.append("Size:" + mySize.toString());
                writeObj.append(COMMA);
                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(stackC.pop().toString());
                    writeObj.append(COMMA);
                }
                writeObj.append(stackC.pop().toString());
                writeObj.append(NEWLINE);

                endtime = System.nanoTime();

                durationC += endtime - startTime;

                startTime = System.nanoTime();

                mySize = stackD.size();

                writeObj.append("Size:" + mySize.toString());
                writeObj.append(COMMA);
                for(int a = 0; a < mySize-1; a++){
                    writeObj.append(stackD.pop().toString());
                    writeObj.append(COMMA);
                }
                writeObj.append(stackD.pop().toString());
                writeObj.append(NEWLINE);

                endtime = System.nanoTime();

                durationD += endtime - startTime;
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
                System.out.println("StackA: " + durationA + " nanoseconds.");
                System.out.println("StackB: " + durationB + " nanoseconds.");
                System.out.println("StackC: " + durationC + " nanoseconds.");
                System.out.println("StackD: " + durationD + " nanoseconds.");
            }
            catch(Exception e){
                System.out.println("File could not been closed.");
            }
        }
    }
}
