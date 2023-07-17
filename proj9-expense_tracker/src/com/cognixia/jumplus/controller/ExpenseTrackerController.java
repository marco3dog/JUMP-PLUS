package com.cognixia.jumplus.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;

import com.cognixia.jumplus.dao.ExpenseTrackerDao;
import com.cognixia.jumplus.model.Account;
import com.cognixia.jumplus.model.Customer;
import com.cognixia.jumplus.model.Expense;
import com.cognixia.jumplus.utills.ConsoleColors;
import com.cognixia.jumplus.utills.ConsoleScanner;

public class ExpenseTrackerController {

	private static Customer currentUser;
	
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
					System.out.print("Enter a username: ");
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
					
					if (ExpenseTrackerDao.getUser(username,password) != null) 
						System.out.println(ConsoleColors.RED + "Username already taken" + ConsoleColors.RESET);
					
					else
						ExpenseTrackerDao.addUser(username, password);
						break;
				}
			}
			
			else if (choice.equals("2")) {
				
				while (true) {
					
					System.out.println(ConsoleColors.CYAN_BOLD +"+---------------------+");
					System.out.println("+------- Login -------+");
					System.out.println("+---------------------+" + ConsoleColors.RESET);
					System.out.print("Username: ");
					String username = ConsoleScanner.getString();
					System.out.print("Password: ");
					String password = ConsoleScanner.getString();
					currentUser = ExpenseTrackerDao.login(username, password);
					
					while (currentUser == null) {
						
						System.out.println(ConsoleColors.RED + "Invalid credentials. Try again." + ConsoleColors.RESET);
						System.out.print("Username: ");
						username = ConsoleScanner.getString();
						System.out.print("Password: ");
						password = ConsoleScanner.getString();
						currentUser = ExpenseTrackerDao.login(username, password);
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
	
	public static void userSession(Customer user) {
		System.out.println(ConsoleColors.GREEN + ConsoleColors.ITALIC + "\nWelcome to your Expense Tracker!\n" + ConsoleColors.RESET);
		ExpenseTrackerDao.createUserAccountList(currentUser);
		for(Account a:currentUser.getList()) {
			ExpenseTrackerDao.createAccountExpenseList(a);
		}
		if(user.getList().isEmpty()) {
			System.out.println(ConsoleColors.YELLOW + ConsoleColors.ITALIC + "Currently not tracking expenses\n" + ConsoleColors.RESET);
		}
		else {
			System.out.printf(ConsoleColors.YELLOW_UNDERLINED + "%-20s %-20s %-20s\n", "Account", "Monthly Expenses", "Yearly Expenses" + ConsoleColors.RESET);
			for(Account a : currentUser.getList()) {
				double monthlyE = a.getMonthlyExpenses();
				double monthlyB = a.getMonthlyBudget();
				double yearlyE = a.getYearlyExpenses();
				double yearlyB = a.getYearlyBudget();
				if(monthlyE <= monthlyB) {
					System.out.printf("%-20s %-20s %-20s\n", 
							a.getName(),
							monthlyE + "/" + monthlyB,
							yearlyE + "/" + yearlyB);
				}else {
					System.out.printf(ConsoleColors.RED + "%-20s %-20s %-20s\n", 
							a.getName(),
							monthlyE + "/" + monthlyB,
							yearlyE + "/" + yearlyB + ConsoleColors.RESET);
				}

			}
			System.out.println("");
		}
		
		int option = 0;
		while(true) {
			System.out.println("1. Create a new account.");
			System.out.println("2. Add/Remove expenses.");
			System.out.println("3. View your account(s).");
			System.out.println("4. Exit.");
			
			try {
				option = ConsoleScanner.getInt();
				if (option < 1 || option > 4) {
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED + "Enter a number." + ConsoleColors.RESET);
				option = 0;
				ConsoleScanner.getString();
				continue;
			}
			catch (Exception e) {
				System.out.println(ConsoleColors.RED + "Not a valid option." + ConsoleColors.RESET);
				option = 0;
				continue;
			}
			System.out.println();
			
			switch(option) {
			
			case 1:
			{
				addAccount();
				break;
			}
			case 2:
			{
				editExpenses();
				break;
			}
			case 3:
			{
				ExpenseTrackerDao.createUserAccountList(currentUser);
				for(Account a:currentUser.getList()) {
					ExpenseTrackerDao.createAccountExpenseList(a);
				}
				System.out.printf(ConsoleColors.YELLOW_UNDERLINED + "%-20s %-20s %-20s\n", "Account", "Monthly Expenses", "Yearly Expenses" + ConsoleColors.RESET);
				for(Account a : currentUser.getList()) {
					double monthlyE = a.getMonthlyExpenses();
					double monthlyB = a.getMonthlyBudget();
					double yearlyE = a.getYearlyExpenses();
					double yearlyB = a.getYearlyBudget();
					ArrayList<Expense> expensesToPrint = new ArrayList<>();
					for(Expense e : a.getExpenses()) {
						expensesToPrint.add(e);
					}
					if(monthlyE <= monthlyB) {
						System.out.printf("%-20s %-20s %-20s\n", 
								a.getName(),
								monthlyE + "/" + monthlyB,
								yearlyE + "/" + yearlyB);
						for(Expense e : expensesToPrint) {
							System.out.printf("%-20s %-20s %-20s\n", 
									"  -  " + e.getName(),
									e.getMonthlyCost(),
									e.getMonthlyCost() * 12);
						}
					}else {
						System.out.printf(ConsoleColors.RED + "%-20s %-20s %-20s\n", 
								a.getName(),
								monthlyE + "/" + monthlyB,
								yearlyE + "/" + yearlyB + ConsoleColors.RESET);
						for(Expense e : expensesToPrint) {
							System.out.printf("%-20s %-20s %-20s\n", 
									"  -  " + e.getName(),
									e.getMonthlyCost(),
									e.getMonthlyCost() * 12);
						}
					}

				}
				System.out.println("");
				break;
			}
			case 4:
			{
				System.out.println(ConsoleColors.ITALIC + ConsoleColors.GREEN + "Goodbye!\n"+ ConsoleColors.RESET );
				return;
			}
			default:
			{
				continue;
			}
			}
		}
	}
	
	private static void addAccount() {
		ExpenseTrackerDao.createUserAccountList(currentUser);
		for(Account a:currentUser.getList()) {
			ExpenseTrackerDao.createAccountExpenseList(a);
		}
		System.out.println();
		System.out.print(ConsoleColors.ITALIC + "Enter the name of the account you wish to add: " + ConsoleColors.RESET);
		String accountName = ConsoleScanner.getString();
		System.out.print(ConsoleColors.ITALIC + "What is your monthly budget for " + accountName + "?: "+ ConsoleColors.RESET);
		String monthlyB = ConsoleScanner.getString();
		while(!monthlyB.matches("\\d+\\.\\d+")){
			System.out.println(ConsoleColors.RED + "Not valid double input." + ConsoleColors.RESET);
			System.out.print(ConsoleColors.ITALIC + "What is your monthly budget for " + accountName + "?: "+ ConsoleColors.RESET);
			monthlyB = ConsoleScanner.getString();
		}
		ExpenseTrackerDao.addAccount(currentUser.getId(), accountName, Double.parseDouble(monthlyB));
				
	}
	
	private static void editExpenses() {
		ExpenseTrackerDao.createUserAccountList(currentUser);
		for(Account a:currentUser.getList()) {
			ExpenseTrackerDao.createAccountExpenseList(a);
		}
		if(currentUser.getList().isEmpty()) {
			System.out.println(ConsoleColors.RED + "You must create an account first." + ConsoleColors.RESET);
			return;
		}
		System.out.print(ConsoleColors.ITALIC + "Select an account.\n\n"+ ConsoleColors.RESET);
		Account selected;
		String accountOption;
		for(int i = 0; i < currentUser.getList().size(); i++) {
			System.out.println(i+1 + ".) " + currentUser.getList().get(i).getName());
		}
		accountOption = ConsoleScanner.getString();
		while(!accountOption.matches("^\\d+$") || Integer.parseInt(accountOption) <= 0 || Integer.parseInt(accountOption) > currentUser.getList().size()) {
			System.out.println(ConsoleColors.RED + "Not a valid numeric input." + ConsoleColors.RESET);
			accountOption = ConsoleScanner.getString();
		}
		selected = currentUser.getList().get(Integer.parseInt(accountOption)-1);
		System.out.print(ConsoleColors.ITALIC + "Would you like to [add] or [delete] an expense from " + selected.getName() + ": "+ ConsoleColors.RESET);
		String addOrDelete = ConsoleScanner.getString();
		switch (addOrDelete) {
		case "add": {
			addExpense(selected);
			break;
		}
		case "delete":{
			if(selected.getExpenses().isEmpty()) {
				System.out.println(ConsoleColors.RED + selected.getName() + " has no expenses yet.\n" + ConsoleColors.RESET);
				return;
			}
			deleteExpense(selected);
			break;
		}
		default:
			return;
		}		
	}
	
	private static void addExpense(Account account) {
		System.out.println();
		System.out.print(ConsoleColors.ITALIC + "Enter the name of the expense you wish to add to " + account.getName() + ": " + ConsoleColors.RESET);
		String expenseName = ConsoleScanner.getString();
		System.out.print(ConsoleColors.ITALIC + "What is your monthly expenditure for " + expenseName + "?: "+ ConsoleColors.RESET);
		String monthlyE = ConsoleScanner.getString();
		while(!monthlyE.matches("\\d+\\.\\d+")){
			System.out.println(ConsoleColors.RED + "Not valid double input." + ConsoleColors.RESET);
			System.out.print(ConsoleColors.ITALIC + "What is your monthly expenditure for " + expenseName + "?: "+ ConsoleColors.RESET);
			monthlyE = ConsoleScanner.getString();
		}
		ExpenseTrackerDao.addExpense(account.getId(), expenseName, Double.parseDouble(monthlyE));
	}
	
	private static void deleteExpense(Account account) {
		Expense selected;
		String expenseOption;
		System.out.print(ConsoleColors.ITALIC + "Which expense would you like to delete?: \n\n" + ConsoleColors.RESET);
		for(int i = 0; i < account.getExpenses().size(); i++) {
			System.out.println(i+1 + ".) " + account.getExpenses().get(i).getName());
		}
		expenseOption = ConsoleScanner.getString();
		while(!expenseOption.matches("^\\d+$") || Integer.parseInt(expenseOption) <= 0 || Integer.parseInt(expenseOption) > currentUser.getList().size()) {
			System.out.println(ConsoleColors.RED + "Not a valid numeric input." + ConsoleColors.RESET);
			expenseOption = ConsoleScanner.getString();
		}
		selected = account.getExpenses().get(Integer.parseInt(expenseOption)-1);
		ExpenseTrackerDao.deleteExpense(selected.getId());
		
	}
	
}
