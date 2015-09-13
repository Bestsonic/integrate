package com.bestsonic.integrate.core.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

	private static String url;
	private static String username;
	private static String password;
	
	static{
		Properties prop = null; 
		InputStream in = null;
		try {
			prop = new Properties();
			in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(in);
			username = (String) prop.get("jdbc.username");
			password = (String) prop.get("jdbc.password");
			url = (String) prop.get("jdbc.jdbcUrl");
		} catch (IOException e) {
			throw new RuntimeException("配置文件加载失败!");
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	public void close(Closeable close){
		if(close != null)
			try {
				close.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		close = null;
	}
}
