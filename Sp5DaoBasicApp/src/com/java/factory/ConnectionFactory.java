package com.java.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static Connection con;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "springUser", "welcome1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return con;
	}
}
