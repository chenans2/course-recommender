package com.g5chen.cea;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Table {
	public static final String PROGRAM_NAME = "PrintTable";
	
	public static void print (Connection conn, String tblName) throws SQLException {
		Statement stmt = null;
	    String query = "SELECT * FROM " + tblName;
	   
	    try {
	    	if (tblName.equals("part4")) {
	    		stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        int cols = rs.getMetaData().getColumnCount();
		        // Column names
		        String columnNames = "";
		        for (int h = 1; h <= cols; h++) {
		        	columnNames += rs.getMetaData().getColumnName(h) + "\t";
		        }
		        System.out.println(columnNames);
		        
		        while (rs.next()) {
		        	for (int i = 0; i < cols; i++) {
		        		Object a = rs.getObject(i + 1);
		        		
		        		if (i == 0 && a.toString().length() < 8 || 
		        			((i == 3 && a == null) || 
		        			(i == 3 && a.toString().length() < 8 ))) {
		        			System.out.print (a + "\t\t");
		        		} else {
		        			System.out.print (a + "\t");
		        		}
		        	}
		        	System.out.print("\n");	           
		        }
	    	} else {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        int cols = rs.getMetaData().getColumnCount();
		        while (rs.next()) {
		        	for (int i = 0; i < cols; i++) {
		        		Object a = rs.getObject(i + 1);
		        		if (tblName.equals("students") && i == 0 && a.toString().length() < 8) {
		        			System.out.print (a + "\t\t");
		        		} else {
		        			System.out.print (a + "\t");
		        		}
		        	}
		        	System.out.print("\n");	           
		        }
	    	}
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static void printAll (Connection conn, String tblName) throws SQLException {
		Statement stmt = null;
	    String query = tblName;
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int cols = rs.getMetaData().getColumnCount();
	        while (rs.next()) {
	        	for (int i = 0; i < cols; i++) {
	        		System.out.print (rs.getObject(i + 1) + "\t");
	        	}
	        	System.out.print("\n");	           
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static boolean exists (Connection conn, String input) throws SQLException {
		Statement stmt = null;
	    String query = input;
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        boolean result = rs.next();
	        stmt.close();
	        return result;
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    }
		return false;
	}
	
	public static void dropView (Connection conn, String view) throws SQLException {
		Statement stmt = null;
	    String query = "DROP VIEW IF EXISTS " + view;
	    
	    try {
	        stmt = conn.createStatement();
	        stmt.executeQuery(query);
	        
	    } catch (SQLException e ) {
	    	//SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static void buildPart3 (Connection conn, String username,
			Map<Integer, Integer> topics, Map<Integer, Integer> skills) throws SQLException {
		Statement stmt = null;
	    String query = "CREATE VIEW part3 AS ";
	    
	    ArrayList<String> allStudents = Student.getStudentNames(conn); // Number of students
	    
	    // Build query (view)
	    for (int i = 0; i < allStudents.size(); i++) {
	    	// Get student info
	    	query += "SELECT DISTINCT username, "
	    			+ "printf('%.2f', COALESCE(age, (SELECT AVG(age) FROM (SELECT * "
	    			+ "FROM students EXCEPT SELECT * FROM students "
	    			+ "WHERE username='" + username + "')))) AS age, "
	    			
	    			+ "gender, "
	    			+ "COALESCE(native_country, 'na') AS native_country, ";
	    	String query2 = "";
	    	// Get all topics
	    	for (int curr_topic : topics.keySet()) {
	    		// Current user, add in his topic interests
	    		if (allStudents.get(i).equals(username)) {
	    			query += topics.get(curr_topic) + " AS t_"
		    				+ curr_topic + " , ";
	    		} else {
	    			query += "(SELECT DISTINCT interest_before "
		    				+ "FROM students NATURAL JOIN topic_interests "
		    				+ "WHERE username='" + allStudents.get(i)
		    				+ "' AND topic_id=" + curr_topic + ") AS t_"
		    				+ curr_topic + " , ";
	    		}
	    		
	    		query2 += "t_" + curr_topic + " IS NOT NULL OR ";
	    	}
	    	
	    	// Get all skills
	    	for (int curr_skill : skills.keySet()) {
	    		// Current user, add in his skill rankings
	    		if (allStudents.get(i).equals(username)) {
	    			query += skills.get(curr_skill) + " AS s_"
		    				+ curr_skill + " , ";
	    		} else {
	    			query += "(SELECT DISTINCT rank_before "
	    				+ "FROM students NATURAL JOIN skill_rankings "
	    				+ "WHERE username='" + allStudents.get(i)
	    				+ "' AND skill_id=" + curr_skill + ") AS s_"
	    				+ curr_skill + " , ";
	    		}
	    		
	    		query2 += "s_" + curr_skill + " IS NOT NULL OR ";
	    		
	    	}
	    	
	    	query = query.substring(0, query.length() - 2); // Remove last comma
	    	
	    	query2 = query2.substring(0, query2.length() - 3); // Remove last OR
	    	
	    	// Union with other students
	    	query += " FROM students WHERE username='" 
	    			+ allStudents.get(i) + "' AND (" + query2 + ") UNION ";
	    }
	    
	    query = query.substring(0, query.length() - 6); // Remove last UNION
	    
	    //System.out.println(query);
	    
	    try {
	        stmt = conn.createStatement();
	        stmt.executeQuery(query);
	    } catch (SQLException e ) {
	    	//SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static void buildPart4 (Connection conn, String username,
			Map<Integer, Integer> topics, Map<Integer, Integer> skills) throws SQLException {
		Statement stmt = null;
	    String query = "CREATE VIEW part4 AS ";
	    
	    ArrayList<String> allStudents = Student.getStudentNames(conn); // Number of students
	    
	    // Calculate distances
	    for (int i = 0; i < allStudents.size(); i++) {
	    	query += "SELECT username, age, gender, native_country, ";
	    	for (int curr_topic : topics.keySet()) {
	    		query += "t_" + curr_topic + ", ";		
	    	}
	    	for (int curr_skill : skills.keySet()) {
	    		query += "s_" + curr_skill + ", ";		
	    	}
	    	
	    	int age = Student.getAge(conn, username);
	    	String gender = Student.getGender(conn, username);
	    	String native_country = Student.getNativeCountry(conn, username);
	    	
	    	query += "(SELECT ((age - " + age + ")*(age - " + age + ") + "
	    			+ "(CASE WHEN gender='" + gender + "' THEN 0 ELSE 1 END) + "
	    			+ "(CASE WHEN native_country='" + native_country + "' THEN 0 ELSE 1 END) + ";
	    	
	    	for (int curr_topic : topics.keySet()) {
	    		query += "(CASE WHEN t_" + curr_topic + " IS NULL THEN 25 ELSE "
	    				+ "(t_" + curr_topic + " - " + topics.get(curr_topic) 
	    				+ ")*(t_" + curr_topic + " - "  + topics.get(curr_topic) 
	    				+ ") END) + ";		
	    	}
	    	
	    	for (int curr_skill : skills.keySet()) {
	    		query += "(CASE WHEN s_" + curr_skill + " IS NULL THEN 25 ELSE "
	    				+ "(s_" + curr_skill + " - " + skills.get(curr_skill) 
	    				+ ")*(s_" + curr_skill + " - "  + skills.get(curr_skill) 
	    				+ ") END) + ";		
	    	}
	    	
	    	query = query.substring(0, query.length() - 3); // Remove last +
	    	
	    	query += ") AS distance FROM part3 WHERE username='" + allStudents.get(i) + "') distance ";
	    	query += "FROM part3 WHERE username='" + allStudents.get(i) + "' AND (";
	    	
	    	for (int curr_topic : topics.keySet()) {
	    		query += "t_" + curr_topic + " NOT NULL OR ";
	    	}
	    	
	    	for (int curr_skill : skills.keySet()) {
	    		query += "s_" + curr_skill + " NOT NULL OR ";
	    	}
	    	
	    	query = query.substring(0, query.length() - 4); // Remove last OR
	    	query += ") UNION ";
	    }
	    
	    query = query.substring(0, query.length() - 6); // Remove last UNION
	    //query += "; ";
	    //System.out.println(query);
	    
	    try {
	        stmt = conn.createStatement();
	        stmt.executeQuery(query);
	    } catch (SQLException e ) {
	    	//SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static void buildRec1 (Connection conn, String username) throws SQLException {
		Statement stmt = null;
		String query = "";
		
		
	    // Best predicted grade 
			query = "SELECT dept_code, course_number, avg(max_grade) AS avg_course_grade "
	    		+ "FROM enrollments NATURAL JOIN course_editions NATURAL JOIN courses NATURAL JOIN letter_grades "
	    		+ "WHERE username IN (SELECT username FROM part4 "
	    		+ "EXCEPT SELECT username FROM part4 WHERE username='" + username + "') "
	    		+ "GROUP BY course_id "
	    		+ "ORDER BY avg_course_grade DESC "
	    		+ "LIMIT 5";

	   
	    try {
    		stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int cols = rs.getMetaData().getColumnCount();
	        // Column names
	        System.out.println("course\t\t" + rs.getMetaData().getColumnName(3));
	        
	        while (rs.next()) {
	        	for (int i = 0; i < cols; i++) {
	        		Object a = rs.getObject(i + 1);
	        		
	        		if (i == 0) {
	        			System.out.print (a + "");
	        		} else {
	        			System.out.print (a + "\t\t");
	        		}
	        	}
	        	System.out.print("\n");	           
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static void buildRec2 (Connection conn, String username) throws SQLException {
		Statement stmt = null;
		String query = "";
	
		
		// Increase topic interest
	    	query = "SELECT dept_code, course_number, avg((interest_after - interest_before)) as avg_interest_incr "
	    		+ "FROM topic_interests NATURAL JOIN courses "
	    		+ "WHERE username IN (SELECT username FROM part4 "
	    		+ "EXCEPT SELECT username FROM part4 WHERE username='" + username + "') "
	    		+ "GROUP BY course_id "
	    		+ "ORDER BY avg_interest_incr DESC "
	    		+ "LIMIT 5";

	   
	    try {
			stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int cols = rs.getMetaData().getColumnCount();
	        // Column names
	        System.out.println("course\t\t" + rs.getMetaData().getColumnName(3));
	        
	        while (rs.next()) {
	        	for (int i = 0; i < cols; i++) {
	        		Object a = rs.getObject(i + 1);
	        		
	        		if (i == 0) {
	        			System.out.print (a + "");
	        		} else {
	        			System.out.print (a + "\t\t");
	        		}
	        	}
	        	System.out.print("\n");	           
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static void buildRec3 (Connection conn, String username) throws SQLException {
		Statement stmt = null;
		String query = "";

		
	    // Increase skill ranking
			query = "SELECT dept_code, course_number, avg((rank_after - rank_before)) as avg_rank_incr "
	    		+ "FROM skill_rankings NATURAL JOIN courses "
	    		+ "WHERE username IN (SELECT username FROM part4 "
	    		+ "EXCEPT SELECT username FROM part4 WHERE username='" + username + "') "
	    		+ "GROUP BY course_id "
	    		+ "ORDER BY avg_rank_incr DESC "
	    		+ "LIMIT 5";

	   
	    try {
    		stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int cols = rs.getMetaData().getColumnCount();
	        // Column names
	        System.out.println("course\t\t" + rs.getMetaData().getColumnName(3));
	        
	        while (rs.next()) {
	        	for (int i = 0; i < cols; i++) {
	        		Object a = rs.getObject(i + 1);
	        		
	        		if (i == 0) {
	        			System.out.print (a + "");
	        		} else {
	        			System.out.print (a + "\t\t");
	        		}
	        	}
	        	System.out.print("\n");	           
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}
	
	public static void buildRec4 (Connection conn, String username) throws SQLException {
		Statement stmt = null;
		String query = "";
		
	    // Best course ranking
			query = "SELECT dept_code, course_number, avg(course_ranking) AS avg_course_rank "
	    		+ "FROM enrollments NATURAL JOIN course_editions NATURAL JOIN courses "
	    		+ "WHERE username IN (SELECT username FROM part4 "
	    		+ "EXCEPT SELECT username FROM part4 WHERE username='" + username + "') "
	    		+ "GROUP BY course_id "
	    		+ "ORDER BY avg_course_rank DESC "
	    		+ "LIMIT 5";
		
	    try {
    		stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int cols = rs.getMetaData().getColumnCount();
	        // Column names
	        System.out.println("course\t\t" + rs.getMetaData().getColumnName(3));
	        
	        while (rs.next()) {
	        	for (int i = 0; i < cols; i++) {
	        		Object a = rs.getObject(i + 1);
	        		
	        		if (i == 0) {
	        			System.out.print (a + "");
	        		} else {
	        			System.out.print (a + "\t\t");
	        		}
	        	}
	        	System.out.print("\n");	           
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	    } finally {
	        if (stmt != null) { 
	        	stmt.close(); 
	        }
	    }
	}

	// TESTING PURPOSES ONLY
	public static void main (String [] args) throws IOException, SQLException {
		if (args.length == 0){
    		System.out.println("Usage: "+PROGRAM_NAME+" <name of properties file>");
    		System.exit(1);
    	}
		Properties props = new Properties();
    	FileInputStream in = new FileInputStream(args[0]);
    	props.load(in);
    	in.close();
    	
    	java.sql.Connection conn = DBConnection.getConnection (props);
    	
    	String username = "auser1";
    	// Store user's topics and skills and their corresponding rating
    	Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
    	Map<Integer, Integer> skills = new HashMap<Integer, Integer>();
    	
    	topics.put(5, 4);
    	topics.put(82, 2);
    	topics.put(72, 3);
    	topics.put(52, 4);
    	topics.put(22, 4);
    	
    	skills.put(57, 2);
    	skills.put(7, 1);
    	skills.put(40, 3);
    	skills.put(35, 2);
    	skills.put(77, 2);
    	
    	System.out.println("------------List of Neighbours---------");
    	Table.dropView(conn, "part3");
    	Table.buildPart3(conn, username, topics, skills);
    	Table.dropView(conn, "part4");
    	Table.buildPart4(conn, username, topics, skills);
    	Table.print(conn, "part4");
    	
    	System.out.println("------------Course Recommendation---------");
    	System.out.println();
    	Table.buildRec1(conn, username);
    	System.out.println();
    	Table.buildRec2(conn, username);
    	System.out.println();
    	Table.buildRec3(conn, username);
    	System.out.println();
    	Table.buildRec4(conn, username);
    	System.out.println();
	}
}

