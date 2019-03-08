import java.io.*;
import java.util.*;

/**
 * This class is the child class of ProgMenu and implements the <br>
 * LibrarySystem interface. <br>
 * <br>
 * Class is used to implement the methods of the screen of the <br>
 * staff. All the things that staff can do are here.<br>
 * @author 151044005 Hakki Erdem Duman <br>
 */
public class StaffScreen extends ProgMenu implements LibrarySystem{

	/**
	 * This method has been overridden from the LibrarySystem interface <br>
	 * and tells the things that can be done by staff. <br>
	 */
	@Override
	public void advancedHelp(){
		System.out.print("\n");
		System.out.println("------------------------------------------------");
		System.out.println("As a staff, you can add/remove books and users.");
		System.out.println("Also can see the book list, of course.");
		System.out.println("------------------------------------------------");
	}

	/**
	 * This method has been overridden from the LibrarySystem interface <br>
	 * This method shows the login screen of the staff. Staff also can see <br>
	 * a hint to find the password. Since the staff does not need any username,<br>
	 * only the information of the password is needed. <br>
	 * @param flag to avoid not to write the header of the menu again.<br>
	 */
	@Override
	public void loginScreen(int flag){
		int choice;
		String pswrd;
		Scanner inputFoo = new Scanner(System.in);
		System.out.print("\n");
		if(flag == 1){
			System.out.println("----------------------------- STAFF LOGIN -----------------------------");
			System.out.print("\n");
		}
		
		System.out.println("1) Enter password");
		System.out.println("2) Did you forget the password?");
		System.out.println("\n3) Back to the main menu");
		System.out.print("Your choice: ");
		choice = inputFoo.nextInt();
		inputFoo.nextLine();
		
		if(choice == 1){
			System.out.print("Password: ");
			pswrd = inputFoo.nextLine();
			if(pswrd.equals("iamtheboss")){
				advancedMenu(inputFoo);
				loginScreen(1);
			}
			else{
				System.out.print("\n");
				System.out.println("-----------------");
				System.out.println("Wrong Password.");
				System.out.println("-----------------");
				loginScreen(0);
			}
		}

		else if(choice == 2){
			System.out.print("\n");
			System.out.println("--------------------------------");
			System.out.println("Password is in the report file.");
			System.out.println("--------------------------------");
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
	 * If staff enters the password correctly, this menu appears. <br>
	 * Staff can do the things that is written on the screen by choosing <br>
	 * a number. <br>
	 * @param inputObj to read an input from user. <br>
	 */
	@Override
	public void advancedMenu(Scanner inputObj){
		int choice;
		System.out.print("\n");
		System.out.println("WELCOME, BOSS!");
		System.out.print("\n");
		System.out.println("What do you wanna do?");
		System.out.print("\n");
		System.out.println("1) Add Book");
		System.out.println("2) Remove Book");
		System.out.println("3) Add User");
		System.out.println("4) Remove User");
		System.out.println("\n5) Back to the staff menu");
		System.out.println("\n");
		System.out.print("Your choice: ");
		choice = inputObj.nextInt();

		if(choice == 1){
			System.out.print("\n");
			addBook("bookList.csv");
			advancedMenu(inputObj);
		}

		else if(choice == 2){
			System.out.print("\n");
			removeBook("bookList.csv");
			advancedMenu(inputObj);
		}

		else if(choice == 3){
			System.out.print("\n");
			addUser("userList.csv");
			advancedMenu(inputObj);
		}

		else if(choice == 4){
			System.out.print("\n");
			removeUser("userList.csv");
			advancedMenu(inputObj);
		}
		else if(choice == 5){
			System.out.print("\n");
		}
		else{
			System.out.print("\n");
			System.out.println("-------------------------------");
			System.out.println("There is no option like this.");
			System.out.println("-------------------------------");
			advancedMenu(inputObj);
		}

	}

	/**
	 * This method has been overridden from the LibrarySystem interface <br>
	 * If staff choose the first option, this menu appears. Staff enters 3 <br>
	 * information about the book and adds it into the database. <br>
	 * @param fileName the name of the file that will be manupilated.
	 */
	@Override
	public void addBook(String fileName){

		int flag = 0;
		Scanner inputFoo = new Scanner(System.in);
		List<String> backupList = new ArrayList<String>();
		FileWriter fileRef = null;
		String bookId, bookName, bookWriter;
		final String COMMA = ",";
		final String NEWLINE = "\n";

		System.out.print("\n");

		if(isFileValid(fileName) == true && isFileEmpty(fileName) == false){
			flag = 1;
			backup(fileName,backupList);
		}

		try{
			fileRef = new FileWriter(fileName,false);
			if(flag == 1){
				for(String str : backupList){
					fileRef.append(str);
				}
			}
			System.out.println("ADD A BOOK");
			System.out.println("\n");
			System.out.print("Enter book id: ");
			bookId = inputFoo.nextLine();
			System.out.print("Enter book name: ");
			bookName = inputFoo.nextLine();
			System.out.print("Enter book writer: ");
			bookWriter = inputFoo.nextLine();

			fileRef.append(bookId);
			fileRef.append(COMMA);
			fileRef.append(bookName);
			fileRef.append(COMMA);
			fileRef.append(bookWriter);
			
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

	/**
	 * This method has been overridden from the LibrarySystem interface <br>
	 * If staff choose the second option, this menu appears. Staff enters the <br>
	 * id of the book that he/she wants to remove. <br>
	 * @param fileName the name of the file that will be manupilated. <br>
	 */
	@Override
	public void removeBook(String fileName){

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
			System.out.println("There is no book in system to remove.");
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
				System.out.println("REMOVE A USER");
				System.out.println("\n");
				System.out.print("Enter user id: ");
				bookId = inputFoo.nextLine();

				for(int a = 0; a<backupList.size(); a++){
					String controlStr = "";
					for(int b = 0; backupList.get(a).charAt(b) != ','; b++){
						controlStr += backupList.get(a).charAt(b);
					}
					if(controlStr.equals(bookId)){
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
	 * If staff choose the third option, this menu appears. Staff enters 3<br>
	 * informations about the user (id, password, name) and adds the user to the <br>
	 * database.<br>
	 * @param fileName name of the file that will be manupilated.
	 */
	public void addUser(String fileName){

		int flag = 0;
		Scanner inputFoo = new Scanner(System.in);
		List<String> backupList = new ArrayList<String>();
		FileWriter fileRef = null;
		String stuId, stuPass, stuName;
		final String COMMA = ",";
		final String NEWLINE = "\n";

		System.out.print("\n");

		if(isFileValid(fileName) == true && isFileEmpty(fileName) == false){
			flag = 1;
			backup(fileName,backupList);
		}

		try{
			fileRef = new FileWriter(fileName,false);
			if(flag == 1){
				for(String str : backupList){
					fileRef.append(str);
				}
			}
			System.out.println("ADD A USER");
			System.out.println("\n");
			System.out.print("Enter user id: ");
			stuId = inputFoo.nextLine();
			System.out.print("Enter user password: ");
			stuPass = inputFoo.nextLine();
			System.out.print("Enter user name: ");
			stuName = inputFoo.nextLine();

			fileRef.append(stuId);
			fileRef.append(COMMA);
			fileRef.append(stuPass);
			fileRef.append(COMMA);
			fileRef.append(stuName);
			
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

	/**
	 * If staff choose the fourth option, this menu appears. Staff enters <br>
	 * the id the user that he/she wants to remove.<br>
	 * @param fileName name of the file that will be manupilated. <br>
	 */
	public void removeUser(String fileName){

		Scanner inputFoo = new Scanner(System.in);
		List<String> backupList = new ArrayList<String>();
		FileWriter fileRef = null;
		String stuId;
		final String COMMA = ",";
		final String NEWLINE = "\n";
		int lineFlag = -1;

		System.out.print("\n");

		if(isFileValid(fileName) == false || isFileEmpty(fileName) == true){
			System.out.print("\n");
			System.out.println("There is no user in system to remove.");
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
				System.out.println("REMOVE A USER");
				System.out.println("\n");
				System.out.print("Enter book id: ");
				stuId = inputFoo.nextLine();

				for(int a = 0; a<backupList.size(); a++){
					String controlStr = "";
					for(int b = 0; backupList.get(a).charAt(b) != ','; b++){
						controlStr += backupList.get(a).charAt(b);
					}
					if(controlStr.equals(stuId)){
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

}