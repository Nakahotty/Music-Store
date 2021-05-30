package music_store_db;

import java.util.Scanner;

public class CommandController {
	private Scanner input; 
	
	CommandController() {
		input = new Scanner(System.in);
	}
	
	public void run() {
		MusicStoreConnection msConnection = new MusicStoreConnection();
		String stmnt = new String();

		intro();
		
		String choice = input.next();
		
		if (choice.equalsIgnoreCase("EXIT")) {
			System.out.println("Exiting from the application...");
			input.close();
		} else if (choice.equalsIgnoreCase("SELECT")) {
			
			
		} else if (choice.equalsIgnoreCase("INSERT")) {
			System.out.println(" --- INSERT YOUR DATA --- ");
			System.out.println("WHAT DO YOU WANT TO INSERT?");
			
			String insertChoice = input.next();
			msConnection.insertInto(stmnt, insertChoice);
		} else if (choice.equalsIgnoreCase("DELETE")) {
			
		} else {
			throw new IllegalArgumentException("UNEXPECTED VALUE: " + choice);
		}
		
		
		System.out.println("Thank you for using the Music Store application!");
	}
	
	private void intro() {
		System.out.println(" --- ENTER COMMAND --- ");
		System.out.println(" 1) SELECT ");
		System.out.println(" 2) INSERT ");
		System.out.println(" 3) DELETE ");
		System.out.println(" 4) EXIT ");
	}
}
