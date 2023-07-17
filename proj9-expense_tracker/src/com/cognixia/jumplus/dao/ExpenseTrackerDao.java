package com.cognixia.jumplus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cognixia.jumplus.connection.BetterConnectionManager;
import com.cognixia.jumplus.model.Account;
import com.cognixia.jumplus.model.Customer;
import com.cognixia.jumplus.model.Expense;
import com.cognixia.jumplus.utills.ConsoleColors;




public class ExpenseTrackerDao {
	
	private static Connection conn = BetterConnectionManager.getConnection();
	
	
	//CREATE
	public static void addUser(String username, String password) {
		
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user VALUES(null, ?, ?);")) {
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println( ConsoleColors.GREEN + "User added!\n" + ConsoleColors.RESET);
	}
	
	public static void addAccount(int userid, String name, double monthlyBudget) {
		
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO account VALUES(null, ?, ?, ?);")) {
			
			ps.setInt(1, userid);
			ps.setString(2, name);		
			ps.setDouble(3, monthlyBudget);
			ps.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println( ConsoleColors.GREEN + "Account added!\n" + ConsoleColors.RESET);
	}
	
	public static void addExpense(int accountid, String name, double monthlyCost) {
		
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO expense VALUES(null, ?, ?, ?);")) {
			
			ps.setInt(1, accountid);
			ps.setString(2, name);		
			ps.setDouble(3, monthlyCost);
			ps.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println( ConsoleColors.GREEN + "Expense added!\n" + ConsoleColors.RESET);
	}
	
	public static void createUserAccountList(Customer user) {
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT a.accountid, a.name, a.monthlyBudget FROM user u JOIN account a on u.userid = a.userid WHERE u.userid = " + user.getId());
				) {
			ArrayList<Account> temp = new ArrayList<>(); 
			while (rs.next()) {
				int id = rs.getInt("accountid");
				String name = rs.getString("name");
				double monthlyBudget = rs.getInt("monthlyBudget");
				Account account = new Account(id, name, monthlyBudget);
				temp.add(account);
			}
			user.setList(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createAccountExpenseList(Account account) {
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT e.expenseid, e.name, e.monthlyCost FROM account a JOIN expense e on a.accountid = e.accountid WHERE a.accountid = " + account.getId());
				) {
			ArrayList<Expense> temp = new ArrayList<>(); 
			while (rs.next()) {
				int id = rs.getInt("expenseid");
				String name = rs.getString("name");
				double monthlyCost = rs.getInt("monthlyCost");
				Expense expense = new Expense(id, name, monthlyCost);
				temp.add(expense);
			}
			account.setExpenses(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//READ
	public static Customer login(String username, String password) {

		try(PreparedStatement ps = conn.prepareStatement(
				"SELECT * from user WHERE username = ? AND password = ?")){

			ps.setString(1, username);
			ps.setString(2, password);

			Customer foundUser = null;
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int userId = rs.getInt("userid");
				String userUsername = rs.getString("username");
				String userPassword = rs.getString("password");
				foundUser = new Customer(userId, userUsername, userPassword);
			}
			rs.close();
			return foundUser;

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Customer getUser(String username, String password) {
		
		String getStmt = "SELECT * FROM user";
		
		try (PreparedStatement ps = conn.prepareStatement(getStmt);
			 ResultSet rs = ps.executeQuery();) {
			
			while (rs.next()) {
				
				String usr = rs.getString("username");
				String pass = rs.getString("password");
				
				if (usr.equals(username) && pass.equals(password)) {
					
						return new Customer(rs.getInt("userid"), usr, pass);
					
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//DELETE
	public static void deleteExpense(int expenseId) {
		
		try (PreparedStatement pstmt = conn.prepareStatement("delete from expense where expenseid = ?;");) {

			pstmt.setInt(1, expenseId);
			pstmt.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
