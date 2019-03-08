import java.io.*;
import java.util.*;

/**
 * This class is the child class of ProgMenu and implements the <br>
 * LibrarySystem interface. <br>
 * <br>
 * Class is used to implement the methods of the screen of the <br>
 * user. All the things that user can do are here.<br>
 * <br>
 * This class have 2 private fields that is named like "pswrd" and <br>
 * "username". These fields are stores the id and the password of user.<br>
 * @author 151044005 Hakki Erdem Duman <br>
 */

public class UserScreen extends ProgMenu implements LibrarySystem{

	private String pswrd;
	private String username;

	/**
	 * This method has been overridden from the LibrarySystem interface <br>
	 * and tells the things that can be done by staff. <br>
	 */
	@Override
	public void advancedHelp(){
		System.out.print("\n");
		System.out.println("----------------------------------------");
		System.out.println("As a normal user, you can borrow books");
		System.out.println("and can see the book list. Nothing more.");
		System.out.println("----------------------------------------");
	}

	/**
	 * This method has been overridden from the LibrarySystem interface <br>
	 * This method shows the login screen of the user. User's informations <br>
	 * are given him/her from the staff. <br>
	 * @param flag to avoid not to write the header of the menu again.<br>
	 *
	 */
	@Override
	public void loginScreen(int flag){
		int choice;
		Scanner inputFoo = new Scanner(System.in);
		System.out.print("\n");
		if(flag == 1){
			System.out.println("----------------------------- USER LOGIN -----------------------------");
			System.out.print("\n");
		}
		
		System.out.println("1) Enter id and password");
		System.out.println("2) Did you forget the password?");
		System.out.println("\n3) Back to the main menu");
		System.out.print("Your choice: ");
		choice = inputFoo.nextInt();
		inputFoo.nextLine();
		
		if(choice == 1){

			System.out.print("Id: ");
			username = inputFoo.nextLine();
			System.out.print("Password: ");
			pswrd = inputFoo.nextLine();
			
			if(isUserExist(username,pswrd)){
				advancedMenu(inputFoo);
			}
			else{
				System.out.print("\n");
				System.out.println("----------------------------");
				System.out.println("There is no user like this.");
				System.out.println("----------------------------");
				System.out.print("\n");
			}

			loginScreen(0);

		}

		else if(choice == 2){
			System.out.print("\n");
			System.out.println("-----------------------------------------");
			System.out.println("Learn it from the staff of the library.");
			System.out.println("-----------------------------------------");
			loginScreen(0);
		}

		else if(choice == 3){
			System.out.print("\n");
		}

		else{
			System.out.println("------------------------------");
			System.out.println("There is no option like this.");
			System.out.println("------------------------------");
			loginScreen(0);
		}
	}

	/**
	 * This method has been overridden from the LibrarySystem interface. <br>
	 * If user enters the username and password correctly, this menu appears. <br>
	 * User can do the things that is written on the screen by choosing <br>
	 * a number. <br>
	 * @param inputObj to read an input from user. <br>
	 *
	 */
	@Override
	public void advancedMenu(Scanner inputObj){
		System.out.print("\n");
		BufferedReader readObj = null;
		boolean flag = false;
		int choice;
		String realName = "";

		try{
			readObj = new BufferedReader(new FileReader("userList.csv"));
				
			String lineStr = "";
			while((lineStr = readObj.readLine()) != null){
				String[] tokens = lineStr.split(",");
				if(username.equals(tokens[0])){
					realName = tokens[2];
				}
			}
		}

		catch(Exception e){
			System.out.print("\n");
			System.out.println(e.getMessage());
		}

		finally{
			try{
				readObj.close();
			}
			catch(Exception e){
				System.out.print("\n");
				System.out.println(e.getMessage());
			}
		}

		System.out.println("WELCOME " + realName + "!");
		System.out.print("\n");
		System.out.println("What do you wanna do?");
		System.out.print("\n");
		System.out.println("1) Borrow a book");
		System.out.println("2) Give a book back");
		System.out.println("3) My books");
		System.out.print("\n");
		System.out.println("4) Back to the user menu");
		System.out.print("\n");
		System.out.print("Your choice: ");
		choice = inputObj.nextInt();

		if(choice == 1){
			removeBook("action.csv");
			advancedMenu(inputObj);
		}
		else if(choice == 2){
			addBook("action.csv");
			advancedMenu(inputObj);
		}
		else if(choice == 3){
			myBooks("action.csv");
			advancedMenu(inputObj);
		}
		else if(choice == 4){
			System.out.print("\n");
		}
		else{
			System.out.print("\n");
			System.out.println("-------------------------------");
			System.out.println("There is no option like this.");
			System.out.println("-------------------------------");
			System.out.println("\n");
			advancedMenu(inputObj);
		}


	}

	/**
	 * This method has been overridden from the LibrarySystem interface. <br>
	 * Actually, this method is the response of the removeBook <br>
	 * method of the StaffScreen class. User can give the book <br>
	 * ,that he/she borrows from the library, back by this method.<br>
	 * This is the removing book from the database. <br>
	 * @param fileName name of file that will be manupilated. <br>
	 */
	@Override 
	public void addBook(String fileName){

		Scanner inputFoo = new Scanner(System.in);
		List<String> backupList = new ArrayList<String>();
		FileWriter fileRef = null;
		String bookId;
		final String COMMA = ",";
		final String NEWLINE = "\n";
		int lineFlag = -1;

		System.out.print("\n");

		if(isFileValid(fileName) == false || isFileEmpty(fileName) == true){
			System.out.print("\n");
			System.out.println("You haven't got any book.");
		}

		else{

			BufferedReader readObj = null;
			

			try{
				readObj = new BufferedReader(new FileReader(fileName));
					
				String lineStr = "";
				while((lineStr = readObj.readLine()) != null){
					backupList.add(lineStr);
				}
			}

			catch(Exception e){
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
		

			try{
				
				fileRef = new FileWriter(fileName,false);
				System.out.println("GIVE A BOOK BACK");
				System.out.println("\n");
				System.out.print("Enter book id: ");
				bookId = inputFoo.nextLine();


				for(int a = 0; a<backupList.size(); a++){
					String[] tokens = backupList.get(a).split(",");
					if(username.equals(tokens[0]) && bookId.equals(tokens[1])){
						lineFlag = a;
					}
				}

				if(lineFlag == -1){
					for(String str : backupList){
						fileRef.append(str);
						fileRef.append("\n");
					}


					System.out.print("\n");
					System.out.println("--------------------------------------------");
					System.out.println("There is no book that has an id like this.");
					System.out.println("--------------------------------------------");
					System.out.print("\n");
				}

				else{
					backupList.remove(lineFlag);
					for(String str : backupList){
						fileRef.append(str);
						fileRef.append("\n");
					}
				}
			}

			catch(Exception e){
				System.out.print("\n");
				System.out.println("The file could not been opened.");
				System.out.print("\n");
			}
			finally{
				try{
					fileRef.flush();
					fileRef.close();
				}
				catch(Exception e){
				System.out.print("\n");
				System.out.println("The file could not been closed.");
				System.out.print("\n");
				}
			}
		}
	}

	/**
	 * This method has been overridden from the LibrarySystem interface. <br>
	 * As above, this method is the opposite meaning of the removeBook method<br>
	 * of the StaffScreen class. If user wants to borrow any book from the library <br>
	 * this method appears. <br>
	 * @param fileName name of file that will be manupilated. <br>
	 */
	@Override
	public void removeBook(String fileName){
		System.out.println("-----------------------------");
		BufferedReader readObj = null;
		BufferedReader readObj2 = null;
		FileWriter fileRef = null;
		int flag = 0;
		Scanner inputFoo = new Scanner(System.in);
		List<String> backupList = new ArrayList<String>();
		
		String bookId;
		final String COMMA = ",";
		final String NEWLINE = "\n";

		if(isFileValid("bookList.csv") == false || isFileEmpty("bookList.csv") == true){
			System.out.print("\n");
			System.out.println("-----------------------------");
			System.out.println("There is no book to borrow.");
			System.out.println("-----------------------------");
			System.out.print("\n");
		}

		else{

			System.out.println("BORROW A BOOK");
			System.out.println("\n");
			System.out.print("Enter book id: ");
			bookId = inputFoo.nextLine();

			try{
				readObj = new BufferedReader(new FileReader("bookList.csv"));
				String lineStr = "";
				while((lineStr = readObj.readLine()) != null){
					String[] eachWord = lineStr.split(",");
					if(bookId.equals(eachWord[0])){
						flag = 1;
					}

				}
			}

			catch(Exception e){
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

			if(flag == 0){
				System.out.print("\n");
				System.out.println("--------------------------------------");
				System.out.println("There is no book like this in system.");
				System.out.println("--------------------------------------");
				System.out.print("\n");
			}

			else{
				flag = 0;

				if(isFileValid(fileName) == true && isFileEmpty(fileName) == false){
					try{
						readObj2 = new BufferedReader(new FileReader(fileName));
						String lineStr = "";
						while((lineStr = readObj2.readLine()) != null){
							String[] eachWord = lineStr.split(",");
							if(bookId.equals(eachWord[1])){
								flag = 1;
							}

						}
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
					finally{
						try{
							readObj2.close();	
						}
						catch(Exception e){
							System.out.println(e.getMessage());
						}
						
					}

				}

				if(flag == 1){
					System.out.print("\n");
					System.out.println("------------------------------------------------");
					System.out.println("This book is not avaliable to borrow right now.");
					System.out.println("------------------------------------------------");
					System.out.print("\n");
				}

				else{
					flag = 0;
					if(isFileValid(fileName) == true && isFileEmpty(fileName) == false){
						flag = 1;
						backup(fileName, backupList);
					}

					try{
						fileRef = new FileWriter(fileName,false);
						if(flag == 1){
							for(String str : backupList){
								fileRef.append(str);
							}
						}

						fileRef.append(username);
						fileRef.append(COMMA);
						fileRef.append(bookId);
						
					}

					catch(Exception e){
						System.out.print("\n");
						System.out.println("The file could not been opened.");
						System.out.print("\n");
					}
					finally{
						try{
							fileRef.flush();
							fileRef.close();
						}
						catch(Exception e){
							System.out.print("\n");
							System.out.println("The file could not been closed.");
							System.out.print("\n");
						}
					}
				}

			}
		
		}
	}

	/**
	 * This method checks whether there is a user in library system<br>
	 * with this username and password. <br>
	 * @param usr username.<br>
	 * @param passwrd password.<br>
	 * @return returns true if there is a user like this. <br>
	 * Returns false if not. <br>
	 */
	public boolean isUserExist(String usr, String passwrd){
		BufferedReader readObj = null;
		boolean flag = false;

		try{
			readObj = new BufferedReader(new FileReader("userList.csv"));
				
			String lineStr = "";
			while((lineStr = readObj.readLine()) != null){
				String[] tokens = lineStr.split(",");
				if(usr.equals(tokens[0]) && passwrd.equals(tokens[1])){
					flag = true;
				}
			}
		}

		catch(Exception e){
			System.out.print("\n");
			System.out.println(e.getMessage());
			
		}

		finally{
			try{
				readObj.close();
			}
			catch(Exception e){
				System.out.print("\n");
				System.out.println(e.getMessage());
			}
		}

		return flag;

	}

	/**
	 * This is the method that allows the user to see <br>
	 * what books he/she got. <br>
	 * Method prints the book on the screen. <br>
	 * @param fileName name of file that will be manupilated.
	 */
	public void myBooks(String fileName){
		BufferedReader fileRef = null;
		BufferedReader fileRef2 = null;
		List<String> myBooksList = new ArrayList<String>(); 
		int flag = 0;

		try{
			fileRef = new BufferedReader(new FileReader(fileName));
			if(isFileEmpty(fileName) == true){
				System.out.print("\n");
				System.out.println("---------------------");
				System.out.println("You have no book.");
				System.out.println("---------------------");
				System.out.print("\n");
			}

			else{

				String lineStr = "";
				while((lineStr = fileRef.readLine()) != null){
					String[] tokens = lineStr.split(",");
					if(username.equals(tokens[0])){
						flag = 1;
						myBooksList.add(tokens[1]);
					}
				}

				if(flag == 0){
					System.out.print("\n");
					System.out.println("---------------------");
					System.out.println("You have no book.");
					System.out.println("---------------------");
					System.out.print("\n");
				}

				else{
					flag = 0;
					try{
						fileRef2 = new BufferedReader(new FileReader("bookList.csv"));
						String lineStr2 = "";
						while((lineStr2 = fileRef2.readLine()) != null){
							String[] tokens = lineStr2.split(",");
							for(int a = 0; a < myBooksList.size(); a++){
								if(tokens[0].equals(myBooksList.get(a))){
									flag = 1;
									System.out.print("\n");
									System.out.println("Id: " + myBooksList.get(a) + " Name: " + tokens[1]);
								}
							}
						}
						if(flag == 0){
							System.out.print("\n");
							System.out.println("------------------------------------------------------------------");
							System.out.println("You have a deleted book. Please give it back as soon as possible.");
							System.out.println("------------------------------------------------------------------");
							System.out.print("\n");
						}
					}
					
					catch(Exception e){
						System.out.print("\n");
						System.out.println("------------------------------------------------------------------");
						System.out.println("You have a deleted book. Please give it back as soon as possible.");
						System.out.println("------------------------------------------------------------------");
						System.out.print("\n");
					}
					finally{
						try{
							fileRef.close();
						}
						catch(Exception e){
							System.out.println(e.getMessage());
						}
					}
				}
			}
		}
		
		catch(Exception e){
			System.out.print("\n");
			System.out.println("--------------------------------------------");
			System.out.println("You have no book.");
			System.out.println("--------------------------------------------");
			System.out.print("\n");
		}
		finally{
			try{
				fileRef.close();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
	
}