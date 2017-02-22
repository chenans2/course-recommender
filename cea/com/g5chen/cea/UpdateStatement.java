package com.g5chen.cea;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UpdateStatement {
	public static final String PROGRAM_NAME = "UpdateStatement";
	public static void updatePrice(Connection conn, String pizzaName, double newPrice) throws SQLException {
		PreparedStatement updateStmt = null;
	    try {
	    	String sql = "UPDATE Serves "+
	    			"SET price=? WHERE pizza=?";
	    	updateStmt = conn.prepareStatement(sql);
	    	updateStmt.setDouble(1,newPrice);	
	    	updateStmt.setString(2, pizzaName);	    	    	
	    	
	    	updateStmt.execute();
	    } catch (SQLException e) {
			SQLError.show(e);
		} finally {
			if (updateStmt != null) {
				updateStmt.close();
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
    	
    	updatePrice(conn, "corn", 15.4);
    	
    	Table.print(conn, "Serves");
    }
}
