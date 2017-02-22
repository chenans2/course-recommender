package com.g5chen.cea;
import java.sql.*;
import java.util.ArrayList;

public class Student {
	private static final String TABLE_NAME = "students";
	
	private String name;
	private int permission = 0;
	private int age;
	private String gender;
	private String native_country;
	
	private String insertSQL = "INSERT INTO students (username, permission, "
			+ "age, gender, native_country) VALUES (?,?,?,?,?)";
	private String updateSQL = "UPDATE students SET permission=?, age=?, "
			+ "gender=?, native_country=? WHERE username=?";
	private String deleteSQL = "DELETE FROM students WHERE username=?";
	PreparedStatement stmt = null;
	
	public void addToDatabase (Connection conn) throws SQLException {
		if (!this.validate()){
			System.out.println("students fields not set");
			System.exit(1);
		}	
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setString(1, this.name);
	    	stmt.setInt(2, this.permission);
	    	stmt.setInt(3, this.age);    	    	
	    	stmt.setString(4, this.gender);
	    	stmt.setString(5, this.native_country);
	    	stmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void updateInDatabase (Connection conn) throws SQLException {
		if (!this.validate()){
			System.out.println("students fields not set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setInt(1, this.permission);  
	    	stmt.setInt(2, this.age);    	    	
	    	stmt.setString(3, this.gender);
	    	stmt.setString(4, this.native_country);
	    	stmt.setString(5, this.name);
	    	stmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void deleteFromDatabase (Connection conn) throws SQLException{
		if (this.name == null){
			System.out.println("students fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setString(1, this.name); 
	    	stmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public static void printTable (Connection conn)throws SQLException {
		Table.print(conn, TABLE_NAME);
	}
	
/*	public static void getTable (Connection conn)throws SQLException {
		Table.get(conn, TABLE_NAME);
	}*/
	
	public static ArrayList<String> getStudentNames (Connection conn) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT username FROM students";
	    ArrayList<String> allStudents = new ArrayList<String>();  
	    
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	allStudents.add(rs.getObject(1).toString());
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	    return allStudents;
	}
	
	public static int getAge (Connection conn, String username) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT age FROM students WHERE username='" + username + "'";
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int result = Integer.parseInt(rs.getObject(1).toString());
	        stmt.close();
	        return result;
	    } catch (SQLException e ) {
	    	//SQLError.show(e);
	    	return 0;
	    }
	}
	
	public static String getGender (Connection conn, String username) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT gender FROM students WHERE username='" + username + "'";
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        String result = rs.getObject(1).toString();
	        stmt.close();
	        return result;
	    } catch (SQLException e ) {
	    	//SQLError.show(e);
	    	return null;
	    }
	}
	
	public static String getNativeCountry (Connection conn, String username) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT native_country FROM students WHERE username='" + username + "'";
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        String result = rs.getObject(1).toString();
	        stmt.close();
	        return result;
	    } catch (SQLException e ) {
	    	//SQLError.show(e);
	    	return null;
	    }
	}
	
	private boolean validate () {
		if (this.name == null)
			return false;
		if (this.age <=0)
			return false;
		if (this.gender == null)
			return false;
		if (this.native_country == null)
			return false;
		return true;
	}
	
	// Getters	
	public String getName() {
		return this.name;
	}

	public int getAge () {
		return this.age;
	}
	
	public String getGender () {
		return this.gender;
	}
	
	public String getNativeCountry () {
		return this.native_country;
	}
	
	// Setters
	public void setName (String name){
		this.name = name;
	}
	
	public void setAge (int age){
		this.age = age;
	}
	
	public void setGender (String gender){
		this.gender = gender;
	}
	
	public void setNativeCountry (String native_country) {
		this.native_country = native_country;
	}
	
	// toString method
	public String toString () {
		return this.name + "\t" +this.age + "\t" + this.gender + "\t" 
				+ this.native_country;
	}
}
