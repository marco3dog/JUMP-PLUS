package com.cognixia.jumplus.contact.controller;

import com.cognixia.jumplus.contact.dao.ContactManagerDao;
import com.cognixia.jumplus.contact.model.User;
import com.cognixia.jumplus.contact.utils.ConsoleColors;
import com.cognixia.jumplus.contact.utils.ConsoleScanner;

public class ContactManagerController {
	
	private static User currentUser;
	
	public static void run() {
		
		while(true) {
			
			System.out.println(ConsoleColors.CYAN_BOLD + "+---------------------+");
			System.out.println("+------ Welcome ------+");
			System.out.println("+---------------------+" + ConsoleColors.RESET);
			System.out.println("1. Create account");
			System.out.println("2. Login");
			System.out.println("3. Exit program\n");
			System.out.print(ConsoleColors.ITALIC + "Choose an option (1-3): " + ConsoleColors.RESET);
			String choice = ConsoleScanner.getString();
			
			while (!choice.matches("^[1-3]$")) {
				System.out.println(ConsoleColors.RED + "Not a valid choice." + ConsoleColors.RESET);
				System.out.print(ConsoleColors.ITALIC + "Choose an option (1-3): "+ ConsoleColors.RESET);
				choice = ConsoleScanner.getString();
			}
			System.out.println();
			
			if (choice.equals("1")) {
				
				while(true) {
					
					System.out.println(ConsoleColors.CYAN_BOLD + "+---------------------+");
					System.out.println("+------ Register -----+");
					System.out.println("+---------------------+\n" + ConsoleColors.RESET);
					System.out.print("Enter your email: ");
					String username = ConsoleScanner.getString();
					
					while (!username.matches("^\\w+$")) {
						System.out.println("Not a valid username.");
						System.out.print("Enter a username: ");
						username = ConsoleScanner.getString();
					}
					
					System.out.print("Enter a password: ");
					String password = ConsoleScanner.getString();
					
					while (!password.matches("^.{1,}$")) {
						System.out.println("Not a valid password.");
						System.out.print("Enter a password: ");
						password = ConsoleScanner.getString();
					}
					
					if (ContactManagerDao.getUser(username,password) != null) 
						System.out.println(ConsoleColors.RED + "Username already taken" + ConsoleColors.RESET);
					
					else
						ContactManagerDao.addUser(username, password);
						break;
				}
			}
			
			else if (choice.equals("2")) {
				
				while (true) {
					
					System.out.println(ConsoleColors.CYAN_BOLD +"+---------------------+");
					System.out.println("+------- Login -------+");
					System.out.println("+---------------------+" + ConsoleColors.RESET);
					System.out.print("Email: ");
					String username = ConsoleScanner.getString();
					System.out.print("Password: ");
					String password = ConsoleScanner.getString();
					currentUser = ContactManagerDao.login(username, password);
					
					while (currentUser == null) {
						
						System.out.println(ConsoleColors.RED + "Invalid credentials. Try again." + ConsoleColors.RESET);
						System.out.print("Username: ");
						username = ConsoleScanner.getString();
						System.out.print("Password: ");
						password = ConsoleScanner.getString();
						currentUser = ContactManagerDao.login(username, password);
					}
						userSession(currentUser);
						break;			
				}
			}
			else {
				System.out.println(ConsoleColors.ITALIC + ConsoleColors.GREEN + "Have a great day!" + ConsoleColors.RESET);
				return;
			}
		}
		

	}
	
	public static void userSession(User currentUser) {
		return;
	}

}
