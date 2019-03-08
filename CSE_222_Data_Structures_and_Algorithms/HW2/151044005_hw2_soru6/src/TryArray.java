import java.io.*;
import java.util.*;

/**
 * This is the class that uses classical java array to store datas and<br>
 * implements LibraryInterface.<br>
 * @author 151044005 Hakki Erdem Duman.<br>
 */
public class TryArray implements LibraryInterface {
    /**
     * This method is overridden from LibraryInterface.<br>
     * Adds 2000 books to the file by using classical java array.<br>
     * @param fileName name of file that we want to manipulate.<br>
     */
    @Override
    public void addBook(String fileName) {
        int index = 0;
        int size = 12000;
        String bookId = "23";
        String bookName = "Return of The King";
        String bookWriter = "J.R.R Tolkien";
        String[] arr = new String[12000];
        FileWriter fileRef = null;
        final String COMMA = ",";
        final String NEWLINE = "\n";

        try {
            fileRef = new FileWriter(fileName, false);

            long start = System.nanoTime();

            while (index < size){

                arr[index] = bookId;
                index++;
                arr[index] = COMMA;
                index++;
                arr[index] = bookName;
                index++;
                arr[index] = COMMA;
                index++;
                arr[index] = bookWriter;
                index++;
                arr[index] = NEWLINE;
                index++;


            }

            long end = System.nanoTime();
            long theTime = (end - start);
            System.out.println("\nAdded in " + theTime + " nanoseconds.");

            for (int i = 0; i < size; i++) {
                fileRef.append(arr[i]);
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
