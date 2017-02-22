package com.g5chen.cea;
import java.sql.*;

public class Course {
	private static final String TABLE_NAME = "courses";
	
	private int course_id;
	private String dept_code;
	private int course_number;
	private String course_name = null;	
	
	private String insertSQL = "INSERT INTO courses (course_id, dept_code, "
			+ "course_number, course_name) VALUES (?,?,?,?)";
	private String updateSQL = "UPDATE courses SET dept_code=? course_number=? "
			+ "course_name=? WHERE course_id=?";
	private String deleteSQL = "DELETE FROM courses WHERE course_id=?";
	PreparedStatement stmt = null;
	
	public void addCourseToDatabase (Connection conn) throws SQLException{
		if (!this.validate()){
			System.out.println("courses fields not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(insertSQL);
	    	stmt.setInt(1,this.course_id);
	    	stmt.setString(2,this.dept_code);
	    	stmt.setInt(3,this.course_number);
	    	stmt.setString(4,this.course_name);
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
			System.out.println("courses fields are not set");
			System.exit(1);
		}		
		
	    try {	    	
	    	stmt = conn.prepareStatement(updateSQL);
	    	stmt.setString(1,this.dept_code);
	    	stmt.setInt(2,this.course_number);
	    	stmt.setString(3,this.course_name);
	    	stmt.setInt(4,this.course_id);
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
			System.out.println("course_id should be set");
			System.exit(1);
		}		
		
	    try {
	    	stmt = conn.prepareStatement(deleteSQL);	    	
	    	stmt.setInt(1, this.course_id); 
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
		String query = "SELECT * FROM courses WHERE course_id=" + course_id;
		Table.printAll(conn, query);
	}
	
	private boolean validate () {
		if (this.course_id == 0)
			return false;
		return true;
	}
	
	// Getters	
	public int getCourseID() {
		return this.course_id;
	}

	public String getDeptCode () {
		return this.dept_code;
	}
	
	public int getCourseNumber () {
		return this.course_number;
	}
	
	// Setters
	public void setCourseID (int course_id){
		this.course_id = course_id;
	}
	
	public void setDeptCode (String dept_code){
		this.dept_code = dept_code;
	}
	
	public void setCourseNumber (int course_number){
		this.course_number = course_number;
	}
	
	// toString
	public String toString () {
		return this.course_id + "\t" + this.dept_code + "\t" 
				+ this.course_number + "\t" + this.course_name;
	}
}
