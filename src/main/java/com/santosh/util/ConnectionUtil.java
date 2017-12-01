package com.santosh.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static ConnectionUtil conUtil = new ConnectionUtil();
	
	static
	{
		try
		{
			System.out.println("done");
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ConnectionUtil() {
		super();
		
	}
	
	public static ConnectionUtil getConnectionUtil() {
		return conUtil;
	}
	
	public Connection getConnection() throws SQLException{
		
		Properties prop = new Properties();
		InputStream dbProps = ConnectionUtil.class.getClassLoader().getResourceAsStream("database.properties");
		
		try {
			prop.load(dbProps);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		
	}
}
