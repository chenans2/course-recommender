package com.g5chen.cea;
import java.sql.*;

public class TopicInterests {
	private static final String TABLE_NAME = "topic_interests";
	
	private int course_id;
	private int edition_id;
	private String username;
	private int topic_id;
	private int interest_before;
	private int interest_after;
	
	private String insertSQL = "INSERT INTO topic_interests (course_id, "
			+ "edition_id, username, topic_id, interest_before, interest_after) "
			+ "VALUES (?,?,?,?,?,?)";
	private String updateSQL = "UPDATE topic_interests SET course_id=?, "
			+ "interest_before=?, interest_after=?, "
			+ "WHERE username=? AND edition_id=? AND topic_id=?";
	private String deleteSQL = "DELETE FROM topic_interests "
			+ "WHERE username=? AND edition_id=? AND topic_id=?";
	PreparedStatement stmt = null;
	
	public void addToDatabase (Connection conn) throws SQLException {
		if (!this.validate()){
			System.out.println("topic_interests fields not set");
			System.exit(1);
		}	
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setInt(1, this.course_id);
	    	stmt.setInt(2, this.edition_id);
	    	stmt.setString(3, this.username);
	    	stmt.setInt(4, this.topic_id);
	    	stmt.setInt(5, this.interest_before);
	    	stmt.setInt(6, this.interest_after);
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
			System.out.println("topic_interests fields not set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setInt(1, this.course_id);
	    	stmt.setInt(2, this.interest_before);
	    	stmt.setInt(3, this.interest_after);
	    	stmt.setString(4, this.username);
	    	stmt.setInt(5, this.edition_id);
	    	stmt.setInt(6, this.topic_id);
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
			System.out.println("topic_interests fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setString(1, this.username); 
	    	stmt.setInt(2, this.edition_id);
	    	stmt.setInt(3, this.topic_id);
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
		if (this.topic_id <=0)
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
	
	public int getTopicID () {
		return this.topic_id;
	}
	
	public int getInterestBefore () {
		return this.interest_before;
	}
	
	public int getInterstAfter () {
		return this.interest_after;
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
	
	public void setTopicID (int topic_id){
		this.topic_id = topic_id;
	}
	
	public void setInterestBefore (int interest_before){
		this.interest_before = interest_before;
	}
	
	public void setInterestAfter (int interest_after) {
		this.interest_after = interest_after;
	}
	
	// toString method
	public String toString () {
		return this.course_id + "\t" + this.edition_id + "\t" + this.username 
				+ "\t" + this.topic_id + "\t" + this.interest_before
				+ "\t" + this.interest_after;
	}
}
