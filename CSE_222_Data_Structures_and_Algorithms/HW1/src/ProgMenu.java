import java.io.*;
import java.util.*;

/**
 * This class is the father class of StaffScreen and UserScreen <br>
 * classes. Involves the mainMenu method and the methods that <br>
 * will be used by 2 child classes. <br>
 * @author 151044005 Hakki Erdem Duman <br>
 */

public class ProgMenu{
	/**
	 * This method is the main menu method. This is called by <br>
	 * main method of the java file to create an interface for user <br>
	 * to use program easily. User can do the operations by entering <br>
	 * numbers. <br>
	 */
	public void mainMenu(){

		boolean loopBreaker = false;
		Scanner userInput = new Scanner(System.in);
		int choice;

		while(!loopBreaker){

			System.out.println("\n------------------- WELCOME TO THE MACMILLAN'S LIBRARY -------------------\n");
			System.out.println("------------------------  What can we do for you? ------------------------\n");
			System.out.println("1) User Login");
			System.out.println("2) Staff Login");
			System.out.println("3) Book List");
			System.out.println("4) Help");
			System.out.println("5) About");
			System.out.println("6) Exit");
			System.out.print("\nYour choice: ");

			choice = userInput.nextInt();

			if(choice == 1){
				System.out.print("\n");
				LibrarySystem obj = new UserScreen();
				obj.loginScreen(1);
			}

			else if(choice == 2){
				System.out.print("\n");
				LibrarySystem obj = new StaffScreen();
				obj.loginScreen(1);
			}

			else if(choice == 3){
				System.out.print("\n");
				bookList("bookList.csv");
				System.out.print("\n");
			}


			else if(choice == 4){
				System.out.print("\n");
				help(userInput,1);
			}


			else if(choice == 5){
				System.out.print("\n");
				about();
			}

			else if(choice == 6){
				System.out.print("\n");
				System.out.println("Goodbye!");
				loopBreaker = true;
			}

			else{
				System.out.print("\n");
				System.out.println("---------------------------------");
				System.out.println("There is no option like this.");
				System.out.println("---------------------------------");
			}


		}

	}

	/**
	 * This method runs the "about" menu. <br>
	 */
	public void about(){
		System.out.println("------------------------------");
		System.out.println("The best library in the world.");
		System.out.println("------------------------------");
	}

	/**
	 * This method runs the help menu. Also in this method, <br>
	 * there is an example of polymorphism. Help method of the <br>
	 * user or staff is called according to user's choice. <br>
	 * @param fooInput to read input from user.<br>
	 * @param flag to avoid not to write the header of the menu again.<br>
	 */
	public void help(Scanner fooInput, int flag){
		int choice;

		System.out.print("\n");
		if(flag == 1){
			System.out.println("------------------------------------ HELP ------------------------------------\n");
		}

		System.out.println("1) What can I do as a user?");
		System.out.println("2) What can I do as a staff?");
		System.out.println("\n3) Back to the main menu");
		System.out.print("\nYour choice: ");
		choice = fooInput.nextInt();


		if(choice == 1){
			LibrarySystem obj = new UserScreen();
			obj.advancedHelp();
			help(fooInput,0);
		}

		else if(choice == 2){
			LibrarySystem obj = new StaffScreen();
			obj.advancedHelp();
			help(fooInput,0);
		}

		else if(choice == 3){
			System.out.print("\n");
		}

		else{
			System.out.print("\n");
			System.out.println("---------------------------------");
			System.out.println("There is no option like that.");
			System.out.println("---------------------------------");
			help(fooInput,0);
		}

	}

	/**
	 * This method stores the datas of the file that we send<br>
	 * as a parameter.<br>
	 * @param fileName the file name that we want to backup.<br>
	 * @param myList the list that we want to store the datas.<br>
	 */
	public static void backup(String fileName, List<String> myList){

		BufferedReader readObj = null;
			

		try{
			readObj = new BufferedReader(new FileReader(fileName));
				
			String lineStr = "";
			while((lineStr = readObj.readLine()) != null){
				String[] eachWord = lineStr.split(",");
				for(String str : eachWord){
					myList.add(str);
					myList.add(",");
				}
				int last = myList.size()-1;
				myList.remove(last);
				myList.add("\n");
			}
		}

		catch(Exception e){
			System.out.print("\n");
			System.out.println("--------------------------------------------");
			System.out.println("There is no book in our library right now.");
			System.out.println("Sorry for this situation, come again!");
			System.out.println("--------------------------------------------");
			System.out.print("\n");
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
	 * This method checks whether there is a file like we send as a parameter.<br>
	 * @param fileName file name that we want to check. <br>
	 * @return returns true if there is a file that is named like this. <br>
	 * Returns false if not.
	 */
	public boolean isFileValid(String fileName){
		BufferedReader obj = null;
		try{
			obj = new BufferedReader(new FileReader(fileName));
		}
		catch(Exception e){
			return false;
		}

		try{
			obj.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return true;
	}

	/**
	 * This method checks whether the file that we send <br>
	 * as a parameter is empty or not.
	 * @param fileName file name that we want to check.<br>
	 * @return returns true if the file is empty. <br>
	 * Returns false if not.
	 */

	public boolean isFileEmpty(String fileName){
		BufferedReader fileRef = null;

		try{
			fileRef = new BufferedReader(new FileReader(fileName));
			if(fileRef.readLine() == null){
				try{
					fileRef.close();
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
				finally{
					return true;	
				}
				
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

		return false;

	}

	/**
	 * This method prints the book list on the screen.<br>
	 * @param fileName file name that involves the book list.<br>
	 */
	public void bookList(String fileName){

		BufferedReader fileRef = null;

		try{
			fileRef = new BufferedReader(new FileReader(fileName));
			if(isFileEmpty(fileName) == true){
				System.out.print("\n");
				System.out.println("---------------------");
				System.out.println("Database is empty.");
				System.out.println("---------------------");
				System.out.print("\n");
			}

			else{
				System.out.print("\n");
				System.out.println("Book ID - Book Name - Writer");
				System.out.print("\n");
				String lineStr = "";
				while((lineStr = fileRef.readLine()) != null){
					String[] tokens = lineStr.split(",");
					System.out.println(tokens[0] + "            " + tokens[1] + "            " + tokens[2]);
					

				}
				
				
			}

		}
		
		catch(Exception e){
			System.out.print("\n");
			System.out.println("--------------------------------------------");
			System.out.println("There is no book in our library right now.");
			System.out.println("Sorry for this situation, come again!");
			System.out.println("--------------------------------------------");
			System.out.print("\n");
		}
	}

}