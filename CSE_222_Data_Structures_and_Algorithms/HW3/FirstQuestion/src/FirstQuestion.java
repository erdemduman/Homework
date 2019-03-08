/**
 * Main class of the first question.<br>
 * @author Hakki Erdem Duman<br>
 */
public class FirstQuestion {

    public static void main(String[] args){
        FileOperations<Integer> fileObj = new FileOperations<Integer>("numbers.txt");
        myStringBuilder<Integer>theList = new myStringBuilder<Integer>();
        fileObj.writeFoo();
        fileObj.readFoo(theList);

        long startTime = System.nanoTime();
        String first = theList.toString();
        long endTime = System.nanoTime();
        System.out.println("toString: " + (endTime - startTime) + " nanoseconds.");
        fileObj.reportToFile("result1.txt", first);

        startTime = System.nanoTime();
        String second = theList.toStringWithGet();
        endTime = System.nanoTime();
        System.out.println("toStringWithGet: " + (endTime - startTime) + " nanoseconds.");
        fileObj.reportToFile("result2.txt", second);

        startTime = System.nanoTime();
        String third = theList.toStringWithIterator();
        endTime = System.nanoTime();
        System.out.println("toStringWithIterator: " + (endTime - startTime) + " nanoseconds.");
        fileObj.reportToFile("result3.txt", third);
    }
}
