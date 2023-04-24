package com.cognixia.jumplus.teacher;

import java.util.ArrayList;

import com.cognixia.jumplus.classes.SchoolClass;

public class Teacher {
	
	private String username;
	private String password;
	private String lName;
	
	private ArrayList<SchoolClass> classes;
	
	

	public Teacher(String username, String password, String lName) {
		super();
		this.username = username;
		this.password = password;
		this.lName = lName;
		classes = new ArrayList<SchoolClass>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public ArrayList<SchoolClass> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<SchoolClass> classes) {
		this.classes = classes;
	}
	
	
	
	
}
