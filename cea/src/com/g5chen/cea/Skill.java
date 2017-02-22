package com.g5chen.cea;
import java.sql.*;

public class Skill {
	private static final String TABLE_NAME = "skills";
	
	private int skill_id = 0;
	private String skill;
	
	private String insertSQL = "INSERT INTO skills (skill_id, skill) VALUES (?,?)";
	private String updateSQL = "UPDATE skills SET skill=? WHERE skill_id=?";
	private String deleteSQL = "DELETE FROM skills WHERE skill_id=?";
	PreparedStatement stmt = null;
	
	public void addToDatabase (Connection conn) throws SQLException {
		if (!this.validate()){
			System.out.println("skills fields not set");
			System.exit(1);
		}	
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setInt(1, this.skill_id);
	    	stmt.setString(2, this.skill);
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
			System.out.println("skills fields not set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setString(1, this.skill);
	    	stmt.setInt(2, this.skill_id);
	    	stmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void deleteFromDatabase (Connection conn) throws SQLException {
		if (this.skill_id == 0){
			System.out.println("skills fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setInt(1, this.skill_id); 
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
	
	public void printSingleEntry (Connection conn) throws SQLException {
		String query = "SELECT * FROM skills WHERE skill_id=" + skill_id;
		Table.printAll(conn, query);
	}
	
	// Returns the skill in DB given skill_id
	public static String getSkill (Connection conn, int skill_id) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT skill FROM skills WHERE skill_id=" + skill_id;
	   
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
	
	// Returns the skill_id in DB given topic
	public static int getSkillID (Connection conn, String skill) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT skill_id FROM skills WHERE skill='" + skill + "'";
	   
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
	
	// Returns the highest ID for new skills
	public static int getNewID (Connection conn) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT MAX(skill_id) FROM skills";
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int result = Integer.parseInt(rs.getObject(1).toString()) + 1;
	        stmt.close();
	        return result;
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    }
		return 0;
	}
	
	private boolean validate() {
		if (this.skill_id == 0)
			return false;
		if (this.skill == null)
			return false;
		return true;
	}
	
	// Getters
	public int getSkillID() {
		return this.skill_id;
	}
	
	public String getSkill() {
		return this.skill;
	}
	
	// Setters
	public void setSkillID(int skill_id){
		this.skill_id = skill_id;
	}
	
	public void setSkill(String skill){
		this.skill = skill;
	}
	
	// toString method
	public String toString() {
		return this.skill_id + "\t" + this.skill;
	}
}
