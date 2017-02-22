package com.g5chen.cea;
import java.sql.*;

public class SkillRankings {
	private static final String TABLE_NAME = "skill_rankings";
	
	private int course_id;
	private int edition_id;
	private String username;
	private int skill_id;
	private int rank_before;
	private int rank_after;
	
	private String insertSQL = "INSERT INTO skill_rankings (course_id, "
			+ "edition_id, username, skill_id, rank_before, rank_after) "
			+ "VALUES (?,?,?,?,?,?)";
	private String updateSQL = "UPDATE skill_rankings SET course_id=?, "
			+ "rank_before=?, rank_after=?, "
			+ "WHERE username=? AND edition_id=? AND skill_id=?";
	private String deleteSQL = "DELETE FROM skill_rankings "
			+ "WHERE username=? AND edition_id=? AND skill_id=?";
	PreparedStatement stmt = null;
	
	public void addToDatabase (Connection conn) throws SQLException {
		if (!this.validate()){
			System.out.println("skill_rankings fields not set");
			System.exit(1);
		}	
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setInt(1, this.course_id);
	    	stmt.setInt(2, this.edition_id);
	    	stmt.setString(3, this.username);
	    	stmt.setInt(4, this.skill_id);
	    	stmt.setInt(5, this.rank_before);
	    	stmt.setInt(6, this.rank_after);
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
			System.out.println("skill_rankings fields not set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setInt(1, this.course_id);
	    	stmt.setInt(2, this.rank_before);
	    	stmt.setInt(3, this.rank_after);
	    	stmt.setString(4, this.username);
	    	stmt.setInt(5, this.edition_id);
	    	stmt.setInt(6, this.skill_id);
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
		if (this.username == null){
			System.out.println("skill_rankings fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setString(1, this.username); 
	    	stmt.setInt(2, this.edition_id);
	    	stmt.setInt(3, this.skill_id);
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
	
	private boolean validate () {
		if (this.username == null)
			return false;
		if (this.edition_id <=0)
			return false;
		if (this.skill_id <=0)
			return false;
		return true;
	}
	
	// Getters	
	public int getCourseID() {
		return this.course_id;
	}

	public int getEditionID () {
		return this.edition_id;
	}
	
	public String getUsername () {
		return this.username;
	}
	
	public int getSkillID () {
		return this.skill_id;
	}
	
	public int getRankBefore () {
		return this.rank_before;
	}
	
	public int getRankAfter () {
		return this.rank_after;
	}
	
	// Setters
	public void setCourseID (int course_id){
		this.course_id = course_id;
	}
	
	public void setEditionID (int edition_id){
		this.edition_id = edition_id;
	}
	
	public void setUsername (String username){
		this.username = username;
	}
	
	public void setSkillID (int skill_id){
		this.skill_id = skill_id;
	}
	
	public void setRankBefore (int rank_before){
		this.rank_before = rank_before;
	}
	
	public void setRankAfter (int rank_after) {
		this.rank_after = rank_after;
	}
	
	// toString method
	public String toString () {
		return this.course_id + "\t" + this.edition_id + "\t" + this.username 
				+ "\t" + this.skill_id + "\t" + this.rank_before
				+ "\t" + this.rank_after;
	}
}
