package com.cognixia.jumplus.model;

public class Expense {
	
	private int id;
	private String name;
	private double monthlyCost;
	
	public Expense(int id, String name, double monthlyCost) {
		super();
		this.id = id;
		this.name = name;
		this.monthlyCost = monthlyCost;
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

	public double getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}
	
	
	

}
