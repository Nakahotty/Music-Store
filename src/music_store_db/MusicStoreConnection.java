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
	
	public void insertInto(String stmnt, String choice) {
		openConnection();
		
		choice.toUpperCase();
		if (choice.equalsIgnoreCase("INSTRUMENTS")) {
			instrumentChoice(stmnt);
		} else if (choice.equalsIgnoreCase("CDVYNILS")) {
			cdVynilsChoice(stmnt);
		} else if (choice.equalsIgnoreCase("MICROPHONES")) {
			microphonesChoice(stmnt);
		} else if (choice.equalsIgnoreCase("HEADSETS") || choice.equalsIgnoreCase("HEADPHONES")) {
			headsetsChoice(stmnt);
		} else if (choice.equalsIgnoreCase("CONTROLLERS")) {
			controllersChoice(stmnt);
		} else if (choice.equalsIgnoreCase("RECORDPLAYERS")) {
			recordPlayersChoice(stmnt);
		} else if (choice.equalsIgnoreCase("CLIENTS")) {
			clientsChoice(stmnt);
		} else if (choice.equalsIgnoreCase("STAFF")) {
			staffChoice(stmnt);
		} else if (choice.equalsIgnoreCase("STORES")) {
			storesChoice(stmnt);
		} else if (choice.equalsIgnoreCase("BUYS")) {
			buysChoice(stmnt);
		} else if (choice.equalsIgnoreCase("GOES TO")) {
			goesToChoice(stmnt);
		} else if (choice.equalsIgnoreCase("AREAVAILABLE")) {
			areAvailableChoice(stmnt);
		} else if(choice.equalsIgnoreCase("LEAVE")) {
			return;
		} else {
			System.out.println("WRONG TABLE NAME!");
			return;
		}
	}
	
	private void instrumentChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String model; String category; int price; int available; 
		int year; String color; String type; int length;
		
		System.out.println("Enter model: ");
		model = inputScanner.nextLine();
		
		System.out.println("Enter category: ");
		category = inputScanner.nextLine();
		
		System.out.println("Enter price: ");
		price = inputScanner.nextInt();
		
		System.out.println("Enter availability: ");
		available = inputScanner.nextInt();
		
		
		stmnt = " INSERT INTO FN71937.ITEMS(MODEL, CATEGORY, PRICE, AVAILABLE)"
		     	  + " VALUES ('" + model + "','" + category + "','" + price + "','" + available + "')";
		
		this.insert(stmnt); 
		
		System.out.println("Enter INSTRUMENT year: ");
		year = inputScanner.nextInt();
		
		inputScanner.nextLine();
		
		System.out.println("Enter INSTRUMENT color: ");
		color = inputScanner.nextLine();
		
		System.out.println("Enter INSTRUMENT type: ");
		type = inputScanner.nextLine();
		
		System.out.println("Enter INSTRUMENT length: ");
		length = inputScanner.nextInt();
		
		stmnt = "INSERT INTO FN71937.ITEMSINSTRUMENTS(MODEL, YEAR, TYPE, COLOR, LENGTH)"
		     	  + " VALUES ('" + model + "','" + year + "','" + type + "','" + color + "','" + length + "')";
		System.out.println(stmnt);
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	

	private void cdVynilsChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String model; String category; int price; int available; 
		String artist; String album; String label; String genre;
		
		System.out.println("Enter model: ");
		model = inputScanner.nextLine();
		
		System.out.println("Enter category: ");
		category = inputScanner.nextLine();
		
		System.out.println("Enter price: ");
		price = inputScanner.nextInt();
		
		System.out.println("Enter availability: ");
		available = inputScanner.nextInt();
		
		
		stmnt = " INSERT INTO FN71937.ITEMS(MODEL, CATEGORY, PRICE, AVAILABLE)"
		     	  + " VALUES ('" + model + "','" + category + "','" + price + "','" + available + "')";
		
		this.insert(stmnt); 
		inputScanner.nextLine();
		
		System.out.println("Enter CDVYNILS artist: ");
		artist = inputScanner.nextLine();
		
		System.out.println("Enter CDVYNILS album: ");
		album = inputScanner.nextLine();
		
		System.out.println("Enter CDVYNILS label: ");
		label = inputScanner.nextLine();
		
		System.out.println("Enter CDVYNILS genre: ");
		genre = inputScanner.nextLine();
		
		stmnt = "INSERT INTO FN71937.ITEMSVINYLSCDS(MODEL, ARTIST, ALBUM, LABEL, GENRE)"
		     	  + " VALUES ('" + model + "','" + artist + "','" + album + "','" + label + "','" + genre + "')";
		System.out.println(stmnt);
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void microphonesChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String model; String category; int price; int available; 
		String type; int sensitivity; int freq; int size ; String brand;
		
		System.out.println("Enter model: ");
		model = inputScanner.nextLine();
		
		System.out.println("Enter category: ");
		category = inputScanner.nextLine();
		
		System.out.println("Enter price: ");
		price = inputScanner.nextInt();
		
		System.out.println("Enter availability: ");
		available = inputScanner.nextInt();
		
		
		stmnt = " INSERT INTO FN71937.ITEMS(MODEL, CATEGORY, PRICE, AVAILABLE)"
		     	  + " VALUES ('" + model + "','" + category + "','" + price + "','" + available + "')";
		
		this.insert(stmnt); 
		inputScanner.nextLine();
		
		System.out.println("Enter MICROPHONES type: ");
		type = inputScanner.nextLine();
		
		System.out.println("Enter MICROPHONES sensitivity: ");
		sensitivity = inputScanner.nextInt();
		
		System.out.println("Enter MICROPHONES frequency: ");
		freq = inputScanner.nextInt();
		
		System.out.println("Enter MICROPHONES size: ");
		size = inputScanner.nextInt();
		
		// STRING ���� INT �������!
		inputScanner.nextLine();
		
		System.out.println("Enter MICROPHONES brand: ");
		brand = inputScanner.nextLine();
		
		stmnt = "INSERT INTO FN71937.ITEMSMICROPHONES(MODEL, TYPE, SENSITIVITY, FREQ, SIZE, BRAND)"
		     	  + " VALUES ('" + model + "','" + type + "','" + sensitivity + "','" + freq+ "','" + size+ "','" + brand + "')";
		System.out.println(stmnt);
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void headsetsChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String model; String category; int price; int available; 
		String sources; int volume; String cabelType; String jack; int freq;
		
		System.out.println("Enter model: ");
		model = inputScanner.nextLine();
		
		System.out.println("Enter category: ");
		category = inputScanner.nextLine();
		
		System.out.println("Enter price: ");
		price = inputScanner.nextInt();
		
		System.out.println("Enter availability: ");
		available = inputScanner.nextInt();
		
		
		stmnt = " INSERT INTO FN71937.ITEMS(MODEL, CATEGORY, PRICE, AVAILABLE)"
		     	  + " VALUES ('" + model + "','" + category + "','" + price + "','" + available + "')";
		
		this.insert(stmnt); 
		inputScanner.nextLine();
		
		System.out.println("Enter HEADSETS sources: ");
		sources = inputScanner.nextLine();
		
		System.out.println("Enter HEADSETS volume: ");
		volume = inputScanner.nextInt();
		
		// STRING ���� INT �������!
		inputScanner.nextLine();
		
		System.out.println("Enter HEADSETS cabel type: ");
		cabelType = inputScanner.nextLine();
		
		System.out.println("Enter HEADSETS jack: ");
		jack = inputScanner.nextLine();
		
		System.out.println("Enter HEADSETS frequency: ");
		freq = inputScanner.nextInt();
		
		stmnt = "INSERT INTO FN71937.ITEMSHEADSETS(MODEL, SOURCES, VOLUME, CABEL_T, JACK, FREQ)"
		     	  + " VALUES ('" + model + "','" + sources + "','" + volume + "','" + cabelType + "','" + jack + "','" + freq + "')";
		System.out.println(stmnt);
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void controllersChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String model; String category; int price; int available; 
		String functions; String fformat; int width; int height; int freq;
		
		System.out.println("Enter model: ");
		model = inputScanner.nextLine();
		
		System.out.println("Enter category: ");
		category = inputScanner.nextLine();
		
		System.out.println("Enter price: ");
		price = inputScanner.nextInt();
		
		System.out.println("Enter availability: ");
		available = inputScanner.nextInt();
		
		
		stmnt = " INSERT INTO FN71937.ITEMS(MODEL, CATEGORY, PRICE, AVAILABLE)"
		     	  + " VALUES ('" + model + "','" + category + "','" + price + "','" + available + "')";
		
		this.insert(stmnt); 
		inputScanner.nextLine();
		
		System.out.println("Enter CONTROLLERS functions (e.g EQ): ");
		functions = inputScanner.nextLine();
		
		System.out.println("Enter CONTROLLERS file format: ");
		fformat = inputScanner.nextLine();
		
		System.out.println("Enter CONTROLLERS width: ");
		width = inputScanner.nextInt();
		
		System.out.println("Enter CONTROLLERS height: ");
		height = inputScanner.nextInt();
		
		System.out.println("Enter CONTROLLERS freq: ");
		freq = inputScanner.nextInt();
		
		
		stmnt = "INSERT INTO FN71937.ITEMSCONTROLLERS(MODEL, FUNCTIONS, FFORMAT, WIDTH, HEIGHT, FREQ)"
		     	  + " VALUES ('" + model + "','" + functions + "','" + fformat + "','" + width + "','" + height + "','" + freq + "')";
		System.out.println(stmnt);
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	
	private void recordPlayersChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String model; String category; int price; int available; 
		String brand; String color; int speed; int weight; int height; int aux; int bluetooth;
		
		System.out.println("Enter model: ");
		model = inputScanner.nextLine();
		
		System.out.println("Enter category: ");
		category = inputScanner.nextLine();
		
		System.out.println("Enter price: ");
		price = inputScanner.nextInt();
		
		System.out.println("Enter availability: ");
		available = inputScanner.nextInt();
		
		
		stmnt = " INSERT INTO FN71937.ITEMS(MODEL, CATEGORY, PRICE, AVAILABLE)"
		     	  + " VALUES ('" + model + "','" + category + "','" + price + "','" + available + "')";
		
		this.insert(stmnt); 
		inputScanner.nextLine();
		
		System.out.println("Enter RECORDPLAYERS brand: ");
		brand = inputScanner.nextLine();
		
		System.out.println("Enter RECORDPLAYERS color: ");
		color = inputScanner.nextLine();
		
		System.out.println("Enter RECORDPLAYERS speed: ");
		speed = inputScanner.nextInt();
		
		System.out.println("Enter RECORDPLAYERS weight: ");
		weight = inputScanner.nextInt();
		
		System.out.println("Enter RECORDPLAYERS height: ");
		height = inputScanner.nextInt();
		
		System.out.println("Enter RECORDPLAYERS AUX (available): ");
		aux = inputScanner.nextInt();

		System.out.println("Enter RECORDPLAYERS Bluetooth (available): ");
		bluetooth = inputScanner.nextInt();
		
		stmnt = "INSERT INTO FN71937.ITEMSRECORDPLAYERS(MODEL, BRAND, SPEED, COLOR, WEIGHT, HEIGHT, AUX, BLUETOOTH)"
		     	  + " VALUES ('" + model + "','" + brand + "','" + speed + "','" + color + "','" + weight + "','" + height + "','" + aux + "','" + bluetooth + "')";
		System.out.println(stmnt);
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void staffChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String snum; String number; String name; String region; String sname;
		
		System.out.println("Enter STAFF serial number: ");
		snum = inputScanner.nextLine();
		
		System.out.println("Enter STAFF phone number: ");
		number = inputScanner.nextLine();
		
		System.out.println("Enter STAFF name: ");
		name = inputScanner.nextLine();
		
		System.out.println("Enter STAFF region: ");
		region = inputScanner.nextLine();

		System.out.println("Enter STAFF store name: ");
		sname = inputScanner.nextLine();
		
		stmnt = " INSERT INTO FN71937.STAFF(SNUM, NUMBER, NAME, REGION, SNAME)"
		     	  + " VALUES ('" + snum + "','" + number + "','" + name + "','" + region + "','" + sname + "')";
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void clientsChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String phone; String name; String address; String email;
		
		System.out.println("Enter CLIENTS phone: ");
		phone = inputScanner.nextLine();
		
		System.out.println("Enter CLIENTS name: ");
		name = inputScanner.nextLine();
		
		System.out.println("Enter CLIENTS address: ");
		address = inputScanner.nextLine();
		
		System.out.println("Enter CLIENTS email: ");
		email = inputScanner.nextLine();
		
		stmnt = " INSERT INTO FN71937.CLIENTS(PHONE, NAME, ADDRESS, EMAIL)"
		     	  + " VALUES ('" + phone + "','" + name + "','" + address + "','" + email + "')";
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void storesChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String name; String address;
		
		System.out.println("Enter STORE name: ");
		name = inputScanner.nextLine();
		
		System.out.println("Enter STORE address: ");
		address = inputScanner.nextLine();
		
		
		stmnt = " INSERT INTO FN71937.STORES(NAME, ADDRESS)"
		     	  + " VALUES ('" + name + "','" + address + "')";
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void buysChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String cphone; String model; String snum;
		
		System.out.println("Enter CLIENT PHONE: ");
		cphone = inputScanner.nextLine();
		
		System.out.println("Enter ITEM MODEL: ");
		model = inputScanner.nextLine();

		System.out.println("Enter STAFF NUMBER: ");
		snum = inputScanner.nextLine();
		
		stmnt = " INSERT INTO FN71937.BUYS(CPHONE, MODEL, SNUM)"
		     	  + " VALUES ('" + cphone + "','" + model + "','" + snum + "')";
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void areAvailableChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String model; String sname;
		
		System.out.println("Enter STORE NAME: ");
		sname = inputScanner.nextLine();
		
		System.out.println("Enter ITEM MODEL: ");
		model = inputScanner.nextLine();
		
		stmnt = " INSERT INTO FN71937.AREAVAILABLE(SNAME, MODEL)"
		     	  + " VALUES ('" + sname + "','" + model + "')";
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
	
	private void goesToChoice(String stmnt) {
		Scanner inputScanner = new Scanner(System.in);
		
		String cphone; String sname;
		
		System.out.println("Enter CLIENT PHONE: ");
		cphone = inputScanner.nextLine();
		
		System.out.println("Enter STORE NAME: ");
		sname = inputScanner.nextLine();
		
		
		stmnt = " INSERT INTO FN71937.GOESTO(CPHONE, SNAME)"
		     	  + " VALUES ('" + cphone + "','" + sname + "')";
		
		this.insert(stmnt);
		
		inputScanner.close();
	}
}
