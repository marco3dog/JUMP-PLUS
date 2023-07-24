package com.cognixia.jumplus.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cognixia.jumplus.contact.connection.BetterConnectionManager;
import com.cognixia.jumplus.contact.model.Contact;
import com.cognixia.jumplus.contact.model.User;
import com.cognixia.jumplus.contact.utils.ConsoleColors;




public class ContactManagerDao {
	private static Connection conn = BetterConnectionManager.getConnection();
	
	
	//CREATE
	public static void addUser(String email, String password) {
		
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user VALUES(null, ?, ?);")) {
			
			ps.setString(1, email);
			ps.setString(2, password);
			ps.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println( ConsoleColors.GREEN + "User added!\n" + ConsoleColors.RESET);
	}
	
	public static void addContact(int userid, String name, String email) {
		
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user VALUES(null, ?, ?, ?);")) {
			
			ps.setInt(1, userid);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println( ConsoleColors.GREEN + "Contact added!\n" + ConsoleColors.RESET);
	}
	
	public static User getUser(String email, String password) {
		
		String getStmt = "SELECT * FROM user";
		
		try (PreparedStatement ps = conn.prepareStatement(getStmt);
			 ResultSet rs = ps.executeQuery();) {
			
			while (rs.next()) {
				
				String usr = rs.getString("email");
				String pass = rs.getString("password");
				
				if (usr.equals(email) && pass.equals(password)) {
					
						return new User(rs.getInt("userid"), usr, pass);
					
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void createUserContactList(User user) {
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT c.contactid, c.name, c.email FROM user u JOIN contact a on u.userid = c.userid WHERE u.userid = " + user.getId());
				) {
			ArrayList<Contact> temp = new ArrayList<>(); 
			while (rs.next()) {
				int id = rs.getInt("contactid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Contact account = new Contact(id, name, email);
				temp.add(account);
			}
			user.setContacts(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static User login(String email, String password) {

		try(PreparedStatement ps = conn.prepareStatement(
				"SELECT * from user WHERE email = ? AND password = ?")){

			ps.setString(1, email);
			ps.setString(2, password);

			User foundUser = null;
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int userId = rs.getInt("userid");
				String userEmail = rs.getString("email");
				String userPassword = rs.getString("password");
				foundUser = new User(userId, userEmail, userPassword);
			}
			rs.close();
			return foundUser;

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void deleteContact(int contactId) {
		
		try (PreparedStatement pstmt = conn.prepareStatement("delete from contact where contactid = ?;");) {

			pstmt.setInt(1, contactId);
			pstmt.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
