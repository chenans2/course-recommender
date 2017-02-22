package com.g5chen.cea;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DeleteStatement {
	public static final String PROGRAM_NAME = "DeleteStatement";
	public static void deletePizza (Connection conn,String pizzeria, String pizza)throws SQLException {
		PreparedStatement deleteStmt = null;
	    try {
	    	String sql = "DELETE FROM Serves "+
	    			"WHERE pizzeria=? AND pizza=?";
	    	deleteStmt = conn.prepareStatement(sql);
	    	deleteStmt.setString(1,pizzeria);	
	    	deleteStmt.setString(2, pizza);	    	    	
	    	
	    	deleteStmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public static void main (String [] args) throws IOException, SQLException  {
		if (args.length == 0){
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
    	
    	deletePizza(conn, "Pizza Hut", "corn");
    	
    	Table.print(conn, "Serves");
    }
}
