package com.cognixia.jumplus.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private int id;
	private String name;
	private double monthlyBudget;
	private List<Expense> expenses;
	
	public Account(int id, String name, double monthlyBudget) {
		super();
		this.id = id;
		this.name = name;
		this.monthlyBudget = monthlyBudget;
		this.expenses = new ArrayList<Expense>();
	}
	
	/*  
	 * Functional Methods
	 */
	
	public double getYearlyBudget() {
		return 12 * monthlyBudget;
	}
	
	public double getMonthlyExpenses() {
		double result = 0;
		for(int i = 0; i < expenses.size(); i++) {
			result += expenses.get(i).getMonthlyCost();
		}
		return result;
	}
	
	public double getYearlyExpenses() {
		return 12 * getMonthlyExpenses();
	}
	
	
	/******************************************************/
	

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

	public double getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(double monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}	

}
