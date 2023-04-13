package com.cognixia.jumplus;

import java.util.Random;

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
}
