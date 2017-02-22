package com.g5chen.cea;
import java.sql.*;

public class Topic {
	private static final String TABLE_NAME = "topics";
	
	private int topic_id = 0;
	private String topic;
	
	private String insertSQL = "INSERT INTO topics (topic_id, topic) VALUES (?,?)";
	private String updateSQL = "UPDATE topics SET topic=? WHERE topic_id=?";
	private String deleteSQL = "DELETE FROM topics WHERE topic_id=?";
	PreparedStatement stmt = null;
	
	public void addToDatabase (Connection conn) throws SQLException {
		if (!this.validate()){
			System.out.println("topics fields not set");
			System.exit(1);
		}	
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setInt(1, this.topic_id);
	    	stmt.setString(2, this.topic);
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
			System.out.println("topics fields not set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setString(1, this.topic);
	    	stmt.setInt(2, this.topic_id);
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
		if (this.topic_id == 0){
			System.out.println("topics fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setInt(1, this.topic_id); 
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
		String query = "SELECT * FROM topics WHERE topic_id=" + topic_id;
		Table.printAll(conn, query);
	}
	
	// Returns the topic in DB given topic_id
	public static String getTopic (Connection conn, int topic_id) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT topic FROM topics WHERE topic_id=" + topic_id;
	   
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
	
	// Returns the topic_id in DB given topic
	public static int getTopicID (Connection conn, String topic) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT topic_id FROM topics WHERE topic='" + topic + "'";
	   
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
	
	// Returns the highest ID for new topics
	public static int getNewID (Connection conn) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT MAX(topic_id) FROM topics";
	   
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
		if (this.topic_id == 0)
			return false;
		if (this.topic == null)
			return false;
		return true;
	}
	
	// Getters
	public int getTopicID() {
		return this.topic_id;
	}
	
	public String getTopic() {
		return this.topic;
	}
	
	// Setters
	public void setTopicID(int topic_id){
		this.topic_id = topic_id;
	}
	
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	// toString method
	public String toString() {
		return this.topic_id + "\t" + this.topic;
	}
}
