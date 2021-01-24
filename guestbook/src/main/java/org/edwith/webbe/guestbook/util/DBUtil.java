package org.edwith.webbe.guestbook.util;

import java.sql.*;

public class DBUtil {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&useSSL=false";
	private static final String DB_USER = "connectuser";
	private static final String DB_PASSWD = "connect123!@#";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			return conn;
		} catch (Exception ex) {
			throw new RuntimeException("Connection Error");
		}
	}

}
