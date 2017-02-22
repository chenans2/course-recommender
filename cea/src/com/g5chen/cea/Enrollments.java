package com.g5chen.cea;
import java.sql.*;

public class Enrollments {
	private static final String TABLE_NAME = "enrollments";
	
	private int edition_id;
	private String username;
	private String letter_grade;
	private int course_ranking;
	private int instr_ranking;	
	
	private String insertSQL = "INSERT INTO enrollments (edition_id, username, "
			+ "letter_grade, course_ranking, instr_ranking) "
			+ "VALUES (?,?,?,?,?)";
	private String updateSQL = "UPDATE enrollments SET letter_grade=? "
			+ "course_ranking=? instr_ranking=? "
			+ "WHERE edition_id=? AND username=?";
	private String deleteSQL = "DELETE FROM enrollments WHERE edition_id=? "
			+ "AND username=?";
	PreparedStatement stmt = null;
	
	public void addToDatabase (Connection conn) throws SQLException{
		if (!this.validate()){
			System.out.println("enrollments fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setInt(1,this.edition_id);
	    	stmt.setString(2,this.username);
	    	stmt.setString(3,this.letter_grade);
	    	stmt.setInt(4,this.course_ranking);
	    	stmt.setInt(5,this.instr_ranking);
	    	stmt.execute();
	    } catch (SQLException e) {
			//SQLError.show(e);
	    	updateInDatabase(conn);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void updateInDatabase (Connection conn) throws SQLException{
		if (!this.validate()){
			System.out.println("enrollments fields are not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setString(1,this.letter_grade);
	    	stmt.setInt(2,this.course_ranking);
	    	stmt.setInt(3,this.instr_ranking);
	    	stmt.setInt(4,this.edition_id);
	    	stmt.setString(5,this.username);
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
		if (!this.validate() ){
			System.out.println("edition_id and username should be set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setInt(1, this.edition_id); 
	    	stmt.setString(2,this.username);
	    	stmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	// Returns the course_id in DB given dept_code and course_number
	public static int getCourseID (Connection conn, String dept_code, 
			int course_number) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT course_id FROM courses WHERE dept_code='" 
	    		+ dept_code + "' AND course_number=" + course_number;
	   
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
	
	// Returns the highest ID for new courses
	public static int getNewID (Connection conn) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT MAX(course_id) FROM courses";
	   
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
	
	public static void printTable (Connection conn) throws SQLException {
		Table.print(conn, TABLE_NAME);
	}
	
	public void printSingleEntry (Connection conn) throws SQLException {
		String query = "SELECT * FROM enrollments WHERE edition_id=" + edition_id
				+ " AND username='" + username + "'";
		Table.printAll(conn, query);
	}
	
	private boolean validate () {
		if (this.edition_id == 0)
			return false;
		if (this.username == null)
			return false;
		return true;
	}
	
	// Getters	
	public int getEditionID() {
		return this.edition_id;
	}

	public String getUsername () {
		return this.username;
	}
	
	public String getLetterGrade () {
		return this.letter_grade;
	}
	
	public int getCourseRanking () {
		return this.course_ranking;
	}
	
	public int getInstrRanking () {
		return this.instr_ranking;
	}
	
	// Setters
	public void setEditionID (int edition_id){
		this.edition_id = edition_id;
	}
	
	public void setUsername (String username){
		this.username = username;
	}
	
	public void setLetterGrade (String letter_grade){
		this.letter_grade = letter_grade;
	}
	
	public void setCourseRanking (int course_ranking){
		this.course_ranking = course_ranking;
	}
	
	public void setInstrRanking (int instr_ranking){
		this.instr_ranking = instr_ranking;
	}
	
	// toString
	public String toString () {
		return this.edition_id + "\t" + this.username + "\t" 
				+ this.letter_grade + "\t" + this.course_ranking
				+ "\t" + this.instr_ranking;
	}
}
