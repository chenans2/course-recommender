package com.g5chen.cea;
import java.sql.*;

public class CourseEditions {
	private static final String TABLE_NAME = "course_editions";
	
	private int edition_id;
	private int course_id;
	private String semester;
	private int year;
	private int total_students;
	private String time_day;	
	
	private String insertSQL = "INSERT INTO course_editions (edition_id, course_id, "
			+ "semester, year, total_students, time_day) VALUES (?,?,?,?,?,?)";
	private String updateSQL = "UPDATE course_editions SET course_id=? semester=? "
			+ "year=? total_students=? time_day=? WHERE edition_id=?";
	private String deleteSQL = "DELETE FROM course_editions WHERE edition_id=?";
	PreparedStatement stmt = null;
	
	public void addCourseToDatabase (Connection conn) throws SQLException{
		if (!this.validate()){
			System.out.println("course_editions fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setInt(1,this.edition_id);
	    	stmt.setInt(2,this.course_id);
	    	stmt.setString(3,this.semester);
	    	stmt.setInt(4,this.year);
	    	stmt.setInt(5,this.total_students);
	    	stmt.setString(6,this.time_day);
	    	stmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public void updateCourseInDatabase (Connection conn) throws SQLException{
		if (!this.validate()){
			System.out.println("course_editions fields are not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setInt(1,this.course_id);
	    	stmt.setString(2,this.semester);
	    	stmt.setInt(3,this.year);
	    	stmt.setInt(4,this.total_students);
	    	stmt.setString(5,this.time_day);
	    	stmt.setInt(6,this.edition_id);
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
			System.out.println("edition_id should be set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setInt(1, this.edition_id); 
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
	public static int getEditionID (Connection conn, int course_id,
			String semester, int year, String time_day) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT edition_id FROM course_editions "
	    		+ "WHERE course_id=" + course_id 
	    		+ " AND semester='" + semester + "'"
	    		+ " AND year=" + year
	    		+ " AND time_day='" + time_day + "'";
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int result = Integer.parseInt(rs.getObject(1).toString());
	        stmt.close();
	        return result;
	    } catch (SQLException e) {
	    	//SQLError.show(e);
	    	return 0;
	    }
	}
	
	// Returns the highest ID for new courses
	public static int getNewID (Connection conn) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT MAX(edition_id) FROM course_editions";
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int result = Integer.parseInt(rs.getObject(1).toString()) + 1;
	        stmt.close();
	        return result;
	    } catch (SQLException e) {
	    	SQLError.show(e);
	    }
		return 0;
	}
	
	public static void printTable (Connection conn) throws SQLException {
		Table.print(conn, TABLE_NAME);
	}
	
	public void printSingleEntry (Connection conn) throws SQLException {
		String query = "SELECT * FROM course_editions WHERE edition_id=" + edition_id;
		Table.printAll(conn, query);
	}
	
	private boolean validate () {
		if (this.edition_id == 0)
			return false;
		return true;
	}
	
	// Getters
	public int getEditionID() {
		return this.edition_id;
	}
	
	public int getCourseID() {
		return this.course_id;
	}

	public String getSemester() {
		return this.semester;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public int getTotalStudents() {
		return this.total_students;
	}
	
	public String getTimeDay() {
		return this.time_day;
	}
	
	// Setters
	public void setEditionID (int edition_id){
		this.edition_id = edition_id;
	}
	
	public void setCourseID (int course_id){
		this.course_id = course_id;
	}
	
	public void setSemester (String semester){
		this.semester = semester;
	}
	
	public void setYear (int year){
		this.year = year;
	}
	
	public void setTotalStudents (int total_students){
		this.total_students = total_students;
	}
	
	public void setTimeDay (String time_day){
		this.time_day = time_day;
	}
	
	// toString
	public String toString () {
		return this.edition_id + "\t" + this.course_id + "\t" + this.semester 
				+ "\t" + this.year + "\t" + this.total_students + "\t" + this.time_day;
	}
}
