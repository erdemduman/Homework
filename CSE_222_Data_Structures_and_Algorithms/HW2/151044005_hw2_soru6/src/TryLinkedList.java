import java.io.*;
import java.util.*;
/**
 * This is the class that uses classical LinkedList to store datas and<br>
 * implements LibraryInterface.<br>
 * @author 151044005 Hakki Erdem Duman.<br>
 */
public class TryLinkedList implements LibraryInterface {
    /**
     * This method is overridden from LibraryInterface.<br>
     * Adds 2000 books to the file by using LinkedList.<br>
     * @param fileName name of file that we want to manipulate.<br>
     */
    @Override
    public void addBook(String fileName) {
        int index = 0;
        int size = 12000;
        String bookId = "23";
        String bookName = "Return of The King";
        String bookWriter = "J.R.R Tolkien";
        List<String> backupList =new LinkedList<String>();

        FileWriter fileRef = null;
        final String COMMA = ",";
        final String NEWLINE = "\n";

        try {
            fileRef = new FileWriter(fileName, false);

            long start = System.nanoTime();

            while (index < size) {

                backupList.add(bookId);
                index++;
                backupList.add(COMMA);
                index++;
                backupList.add(bookName);
                index++;
                backupList.add(COMMA);
                index++;
                backupList.add(bookWriter);
                index++;
                backupList.add(NEWLINE);
                index++;


            }

            long end = System.nanoTime();
            long theTime = (end - start);
            System.out.println("\nAdded in " + theTime + " nanoseconds.");

            Iterator<String> it = backupList.iterator();

            for (int i = 0; i < size; i++) {
                fileRef.append(it.next());
            }





        }

        catch (Exception e) {
            System.out.print("\n");
            System.out.println("The file could not been opened.");
            System.out.print("\n");
        } finally {
            try {
                fileRef.flush();
                fileRef.close();
            } catch (Exception e) {
                System.out.print("\n");
                System.out.println("The file could not been closed.");
                System.out.print("\n");
            }
        }
    }
}
