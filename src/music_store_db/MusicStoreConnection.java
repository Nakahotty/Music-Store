package music_store_db;

import java.sql.*;
import java.util.Scanner;

public class MusicStoreConnection {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public void openConnection() {
		// 1) load IBM DB2 JDBC driver
		try {
			DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
		} catch(Exception cnfex) {
			System.out.println("Problem is loading or geistreing IBM DB2 JDBC driver");
			cnfex.printStackTrace();
		}
		
		// 2) opening DB connection
		try {
			connection = DriverManager.getConnection("jdbc:db2://62.44.108.24:50000/SAMPLE", "db2admin", "db2admin");
			statement = connection.createStatement();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if (connection != null) {
				resultSet.close();
				statement.close();
				
				connection.close();
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	
	
	// SELECT, INSERT, DELETE
	public void select(String stmnt, int column) {
		try {
			resultSet = statement.executeQuery(stmnt);
			String result = "";
			
			while (resultSet.next()) {
				for (int i = 1; i <= column; i++) {
					result += resultSet.getString(i);
					if (i == column)
						result += " \n";
					else 
						result += ", ";
				}
			}
			
			System.out.println("Executing query: " + stmnt + "\n");
	        System.out.println("Result output \n");
	        System.out.println("---------------------------------- \n");
	        System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String stmtnt) {
		try {
			statement.executeUpdate(stmtnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Successfully inserted!");
	}
	
	public void delete(String stmtnt) {
		try {
			statement.executeUpdate(stmtnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Successfully deleted!");
	}
	
	public void selectExecution(String stmnt) {
		openConnection();
		stmnt = "SELECT TYPE FROM FN71937.ITEMSINSTRUMENTS";
		this.select(stmnt, 1); 
	}
	
	public void insertExecution(String stmnt, String insertChoice) {
		openConnection();
		insertInto(stmnt, insertChoice); 
	}
	
	private void insertInto(String stmnt, String choice) {
		choice.toUpperCase();
		if (choice.equals("INSTRUMENTS")) {
			Scanner inputScanner = new Scanner(System.in);
			
			String model; String category; int price; int available; 
			int year; String color; String type; int length;
			
			System.out.println("Enter model: ");
			model = inputScanner.next();
			
			System.out.println("Enter category: ");
			category = inputScanner.next();
			
			System.out.println("Enter price: ");
			price = inputScanner.nextInt();
			
			System.out.println("Enter availability: ");
			available = inputScanner.nextInt();
			
			
			stmnt = " INSERT INTO FN71937.ITEMS(MODEL, CATEGORY, PRICE, AVAILABLE)"
			     	  + " VALUES ('" + model + "','" + category + "','" + price + "','" + available + "')";
			
			this.insert(stmnt); 
			
			System.out.println("Enter INSTRUMENT year: ");
			year = inputScanner.nextInt();
			
			System.out.println("Enter INSTRUMENT color: ");
			color = inputScanner.next();
			
			System.out.println("Enter INSTRUMENT type: ");
			type = inputScanner.next();
			
			System.out.println("Enter INSTRUMENT length: ");
			length = inputScanner.nextInt();
			
			stmnt = "INSERT INTO FN71937.ITEMSINSTRUMENTS(MODEL, YEAR, TYPE, COLOR, LENGTH)"
			     	  + " VALUES ('" + model + "','" + year + "','" + type + "','" + color + "','" + length + "')";
			System.out.println(stmnt);
			
			this.insert(stmnt);
		} else if (choice == "CDVYNILS") {
			
		} else if (choice == "MICROPHONES") {
			
		} else if (choice == "HEADSETS") {
			
		} else if (choice == "CONTROLLERS") {
			
		} else if (choice == "RECORD PLAYERS") {
			
		} else if (choice == "CLIENTS") {
			
		} else if (choice == "STAFF") {
			
		} else if (choice == "STORES") {
			
		} else if (choice == "BUYS") {
			
		} else if (choice == "GOES TO") {
			
		} else if (choice == "ARE AVAILABLE") {
			
		} else {
			System.out.println("WRONG TABLE NAME!");
			return;
		}
	}
}
