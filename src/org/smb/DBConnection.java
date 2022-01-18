package org.smb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
		private DBConnection(){
		}
		/*public static Connection getDBConnection(){
			Connection con = null;
			if(con == null){
				try{
					String url = "jdbc:mysql://localhost:3306/mysql";
					//String url = "us-cdbr-iron-east-04.cleardb.net:3306/ad_aeca8e9e7e78f64";
					Class.forName("com.mysql.jdbc.Driver"); 
					con=DriverManager.getConnection(url,"root","root"); 
					//con=DriverManager.getConnection(url,"b80197998e7f7d","c986e885"); 
				}catch (Exception e) {System.out.println(e);}
			}
			return con;
			
		}*/
		/*public static Connection getDBConnection(){
			Connection con = null;
			try {
				System.out.println("DBConnectvity getConnection()");
		         Class.forName("org.hsqldb.jdbc.JDBCDriver");
		         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/SMB_DEV", "SA", "");
		         
		         
			 } catch (Exception e) {
		         System.out.println("Exception -->"+e);
		      }
			return con;
		}*/
		
		public static Connection getDBConnection(){
			Connection con = null;
			String dbName = "smbfinance";
			  String userName = "smbfinance";
			  String password = "smbfinance";
			  //String hostname = "smbfinance.ctalowkhqpjl.ap-south-1.rds.amazonaws.com";
			  //Updated on 18-Dec-2018
			  //String hostname = "smbfinance.c72i9etyklid.ap-south-1.rds.amazonaws.com";
			  //Updated on 21-Dec-2018
			  //String hostname = "smbfinance.c72i9etyklid.ap-south-1.rds.amazonaws.com";
			  //Updated on 09-Dec-2021
			  String hostname = "smbfinance.cggz4shjrmjy.ap-south-1.rds.amazonaws.com";
			  
			  String port = "3306";
			  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
			    port + "/" + dbName + "?user=" + userName + "&password=" + password;
			   try {
				    System.out.println("Loading driver...");
				    Class.forName("com.mysql.jdbc.Driver");
				    System.out.println("Driver loaded!");
				    con = DriverManager.getConnection(jdbcUrl);
				  } catch (ClassNotFoundException e) {
				    throw new RuntimeException("Cannot find the driver in the classpath!", e);
				 }catch (SQLException ex) {
					 	System.out.println("SQLException: " + ex.getMessage());
					    System.out.println("SQLState: " + ex.getSQLState());
					    System.out.println("VendorError: " + ex.getErrorCode());
				}catch (Exception e) {
					System.out.println("Exception: " + e);
				}
			 return con;
		}
		
}
