package com.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.dao.TccDaoException;;

public class ConnectionTccFactory {
	
	public static Connection getConnection() throws TccDaoException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/sysprime","root","mysqlcodato");			
		}
		catch(Exception e)
		{
			throw new TccDaoException(e.getMessage());
		}
	}
	
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws TccDaoException
	{
		close(conn, stmt, rs);
	}
	
	public static void closeConnection(Connection conn, Statement stmt) throws TccDaoException
	{
		close(conn, stmt, null);
	}
	
	public static void closeConnection(Connection conn) throws TccDaoException
	{
		close(conn,null,null);
	}
	
	private static void close(Connection conn, Statement stmt, ResultSet rs) throws TccDaoException
	{
		try
		{
			if( rs != null) rs.close();
			if( stmt != null) stmt.close();
			if( conn != null) conn.close();		
		}
		catch (Exception e) {
			throw new TccDaoException(e.getMessage());
		}
	}
	

}
