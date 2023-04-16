package com.cognixia.jumplus;

import java.util.Random;
import java.util.Scanner;

public class TopLevel {
	
	public static UserList generateRandomUserList(int length, MovieList ml) {
		UserList generatedList = new UserList();
		for(int i = 0; i < length; i++) {
			generatedList.addUser("user" + i + "@email.com", "pwd" + i);
		}
		Random rand = new Random();
		for(int i = 0; i < 15; i++) {
			ml.rateMovie((i+1)%5, (rand.nextDouble() * 5));
		}
		return generatedList;
	}
	
	public static void printMainMenu() {
		
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.format("%24s","Selection Number");
		System.out.println("");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		String [] data = {"REGISTER","LOGIN","EXIT"};
		for(int i = 0; i < data.length; i++) {
			System.out.format("%24s", (i + 1) + ").");
			System.out.format("%24s", data[i]);
			System.out.println("");
		}
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");

		for(int i = 0; i<8;i++) {
			System.out.println("");
		}
	}
	
	public static void runTopLevel() {
		
		Scanner scan = new Scanner(System.in);
		MovieList movies = new MovieList();
		UserList users = TopLevel.generateRandomUserList(5, movies);
		int option = 1;
		while(true) {
			printMainMenu();
			try {
				option = scan.nextInt();
			}
			catch(Exception e) {
				System.out.println("        Enter a number.");
				option = 0;
				scan.nextLine();
				continue;
			}
			
			switch(option) {
			case 1: {
				boolean didRegister = false;
				do {
					System.out.println("        Enter your email: ");
					String email = scan.next();
					System.out.println("        Enter your password: ");
					String password = scan.next();
					didRegister = users.addUser(email, password);
				}
				while(!didRegister);
				continue;
			}
			case 2: {
				boolean login = false;
				do {
					System.out.println("        Enter your email: ");
					String email = scan.next();
					System.out.println("        Enter your password: ");
					String password = scan.next();
					login = users.authenticate(email, password);
				}
				while(!login);
				break;
			}
			case 3: {
				System.out.println("        Program Terminated.");
				return;
			}

			default: {
				System.out.println("        Enter a number between 1-3.");
				continue;
			}
			}
			break;
		}
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.println("        View ratings or use a selection number to rate a movie.");
		System.out.print("+");
		for(int i = 0; i < 94; i++) {
			System.out.print("-");
		}
		System.out.print("+");
		System.out.println("");
		System.out.println("");	
		double rateOption = 0;
		while(true) {
			movies.printMovies();
			try {
				option = scan.nextInt();
				if(option == 6) {
					System.out.println("        Program Terminated.");
					scan.close();
					return;
				}
				while(true) {
					movies.printRateScreen(option - 1);
					rateOption = scan.nextDouble();
					if(rateOption == 6) {
						System.out.println("        Program Terminated.");
						scan.close();
						return;
					}
					if(!movies.rateMovie(option - 1, rateOption)) {
						throw new Exception();
					}
					break;
				}
			}
			catch(Exception e) {
				scan.nextLine();
				System.out.println("        Enter a valid number.");
				continue;
			}
		}
			
		
	}



}
