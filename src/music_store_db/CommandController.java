package music_store_db;

import java.util.Scanner;

public class CommandController {
	private Scanner input;
	
	public CommandController() {
		input = new Scanner(System.in);
	}
	
	public void run() {
		MusicStoreConnection msConnection = new MusicStoreConnection();
		String stmnt = new String();

		intro();
		
		boolean exit = true;
		String choice = input.next();
		do {
			switch (choice) {
			case "EXIT": {
				exit = false; 
				break;
			}
			case "SELECT": {
				break; 
			}
			case "Insert": {
				System.out.println(" --- INSERT YOUR DATA --- ");
				System.out.println("WHAT DO YOU WANT TO INSERT?");
				String insertChoice = input.next();
				msConnection.insertExecution(stmnt, insertChoice);
				break;
			}
			case "DELETE": {
				break;
			}
			default:
				throw new IllegalArgumentException("UNEXPECTED VALUE: " + choice);
			}
		} while (exit);
	}
	
	private void intro() {
		System.out.println(" --- ENTER COMMAND --- ");
		System.out.println(" 1) SELECT ");
		System.out.println(" 2) INSERT ");
		System.out.println(" 3) DELETE ");
		System.out.println(" 4) EXIT ");
	}
}
