import java.io.*;
import java.util.*;

/**
 * This interface keeps the common methods of StaffScreen<br>
 * and UserScreen to be overridden by them.
 */
public interface LibrarySystem{

	public void advancedHelp();
	public void loginScreen(int flag);
	public void advancedMenu(Scanner inputObj);
	public void addBook(String fileName);
	public void removeBook(String fileName);
}