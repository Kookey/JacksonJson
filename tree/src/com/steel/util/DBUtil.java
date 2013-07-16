package com.steel.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;


public class DBUtil {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static Logger logger = Logger.getLogger("DBUtil");
	
	
	static{
		Properties props = new Properties();
		try {
			props.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username=props.getProperty("username");
			password = props.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("¶ÁÈ¡ÎÄ¼þÊ§°Ü");
		}
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn,Statement stat,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
