package com.cognixia.jumplus;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserList {
	
	private ArrayList<User> users;

	public UserList() {
		super();
		users = new ArrayList<User>();
	}
	
	public boolean authenticate(String email, String password) {

		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getEmail().equals(email)) {
				if(users.get(i).getPassword().equals(password)) {
					System.out.println("Welcome, " + email +".");
					return true;
				}
				else {
					System.out.println("You have entered the wrong password for this account.");
					return false;
				}
			}

		}
		System.out.println("There is no account associated with this email.");
		return false;
	}
	
	public boolean addUser(String email, String password) {
		
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			System.out.println("You have entered an invalid email address.");
			return false;
		}
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getEmail().equals(email)) {
				System.out.println("A user with that email already exists.");
				return false;
			}
		}
		users.add(new User(email, password));
		return true;
	}

	@Override
	public String toString() {
		String allUsers = "";
		for(int i = 0; i < users.size(); i++) {
			allUsers.concat((users.get(i).getEmail() + "/n"));
		}
		return allUsers;
	}
	
	public void printSize() {
		System.out.println(users.size());
	}
	
	
}
