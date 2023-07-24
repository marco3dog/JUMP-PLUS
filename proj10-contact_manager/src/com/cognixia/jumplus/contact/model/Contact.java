package com.cognixia.jumplus.contact.model;

import java.time.LocalDateTime;

public class Contact {
	
	private int id;
	private String name;
	private String email;
	private LocalDateTime timeAdded;
	
	public Contact(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		timeAdded = LocalDateTime.now(); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(LocalDateTime timeAdded) {
		this.timeAdded = timeAdded;
	}
	
	
	
	
}
