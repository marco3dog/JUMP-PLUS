package com.cognixia.jumplus.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private int id;
	private String name;
	private String password;
	private List<Account> list;
	
	public Customer(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.list = new ArrayList<Account>();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Account> getList() {
		return list;
	}

	public void setList(List<Account> list) {
		this.list = list;
	}
	
	
	

}
