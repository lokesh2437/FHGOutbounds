package com.retailsols.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBUtility {

	private static Connection conn = null;

	private DBUtility(Properties properties, Logger logger) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + properties.getProperty("host") + ":"
							+ properties.getProperty("port") + ":"
							+ properties.getProperty("sid"),
					properties.getProperty("user"),
					properties.getProperty("pass"));

		} catch (Exception e) {
			logger.error("", e);
			System.out.println(e);
		}
	}

	public static Connection getConnection(Properties properties, Logger logger) {
		if (conn == null) {
			new DBUtility(properties, logger);
			return conn;
		} else {
			return conn;
		}
	}

	public static boolean closeConnection(Connection con, Logger logger) {
		boolean b = false;
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return b;

	}
}
