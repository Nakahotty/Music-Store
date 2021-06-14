package music_store_db;

import java.util.Scanner;

public class CommandController {
	private Scanner input; 
	
	CommandController() {
		input = new Scanner(System.in);
	}
	
	public void run() {
		MusicStoreController msController = new MusicStoreController();
		String stmnt = new String();

		intro();
		
		String choice = input.next();
		
		if (choice.equalsIgnoreCase("EXIT")) {
			System.out.println("Exiting from the application...");
			input.close();
		} else if (choice.equalsIgnoreCase("SELECT")) {
			System.out.println(" --- SELECT ----");
			System.out.println(" WHAT DO YOU WANT TO SELECT?");
			
			String selectChoice = input.next();
			msController.selectExecution(stmnt, selectChoice);
		} else if (choice.equalsIgnoreCase("INSERT")) {
			System.out.println(" --- INSERT YOUR DATA --- ");
			System.out.println(" WHAT DO YOU WANT TO INSERT?");
			
			String insertChoice = input.next();
			msController.insertInto(stmnt, insertChoice);
		} else if (choice.equalsIgnoreCase("DELETE")) {
			System.out.println(" --- DELETE ITEM ---");
			System.out.println(" WHICH ITEM DO YOU WANT TO DELETE? (MODEL)");
			
			String deleteChoice = input.next();
			msController.deleteExecution(stmnt, deleteChoice);
		} else if (choice.equalsIgnoreCase("VIEW")) {
			System.out.println(" --- VIEW ---");
			System.out.println(" WHAT DO YOU WANT TO VIEW?");
			
			System.out.println(" AVAILABLE ITEMS");
			System.out.println(" EXPENSIVE ITEMS");
			
			String viewChoice = input.next();
			msController.viewExecution(stmnt, viewChoice);
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
		System.out.println(" 4) VIEW ");
		System.out.println(" 5) EXIT ");
	}
}
