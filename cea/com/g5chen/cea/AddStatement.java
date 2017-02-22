package com.g5chen.cea;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AddStatement {
	public static final String PROGRAM_NAME = "AddStatement";
	public static void addPizza (Connection conn) throws SQLException{
		Statement statement = null;

		String insertTableSQL = "INSERT INTO Serves (pizzeria, pizza, price)"
				+ " VALUES ('Pizza Hut','corn',10.5)";	

		try {			
			statement = conn.createStatement();
			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			int qResult = statement.executeUpdate(insertTableSQL);

			System.out.println("Rows affected="+qResult);
		} catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}
	
	public static void main (String [] args) throws IOException, SQLException {
		if (args.length == 0) {
    		System.out.println("Usage: "+PROGRAM_NAME+" <name of properties file>");
    		System.exit(1);
    	}
		
    	Properties props = new Properties();
    	FileInputStream in = new FileInputStream(args[0]);
    	props.load(in);
    	in.close();
    	
    	java.sql.Connection conn = DBConnection.getConnection (props);
    	if (conn == null) {
    		System.exit(1);
    	}
    	
    	addPizza(conn);
    	
    	Table.print(conn, "Serves");
    }

}
