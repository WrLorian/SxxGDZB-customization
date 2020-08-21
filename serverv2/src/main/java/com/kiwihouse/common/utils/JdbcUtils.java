package com.kiwihouse.common.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author yjzn
 * @date 2020-1-16 17:50:08
 */
public class JdbcUtils {
 
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	static{
		try{
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("application.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			driver = prop.getProperty("kiwihouse.datasource.driverClassName");
			url = prop.getProperty("kiwihouse.datasource.url");
			username = prop.getProperty("kiwihouse.datasource.username");
			password = prop.getProperty("kiwihouse.datasource.password");
			
			Class.forName(driver);
			
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	public static Connection getConnection() throws SQLException{
		
		return DriverManager.getConnection(url, username,password);
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs){
		
		if(rs!=null){
			try{
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
 
		}
		if(st!=null){
			try{
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(conn!=null){
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}