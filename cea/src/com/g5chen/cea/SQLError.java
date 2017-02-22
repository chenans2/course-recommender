package com.g5chen.cea;
import java.sql.*;

public class SQLError {
	public static void print(SQLException ex) {
	    while (ex != null) {
		      System.err.println("SQLState: " + ex.getSQLState());
		      System.err.println("Error Code: " + ex.getErrorCode());
		      System.err.println("Message: " + ex.getMessage());
		      Throwable t = ex.getCause();
		      while (t != null) {
		        System.out.println("Cause: " + t);
		        t = t.getCause();
		      }
		      ex = ex.getNextException();
	    }
	}
	
	public static void show(SQLException ex){
		System.out.println(ex.getMessage()+"\n"+ex.getSQLState());
		System.out.println("SQL error "+ex.getErrorCode());
		//javax.swing.JOptionPane.showMessageDialog(null,ex.getMessage()+"\n"+ex.getSQLState(),"SQL error "+ex.getErrorCode(),javax.swing.JOptionPane.ERROR_MESSAGE);
	}
}
