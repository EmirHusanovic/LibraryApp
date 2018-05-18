package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static ConnectionManager instance = null;

	private Connection connection = null;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "guitarhero1";
	private static final String CONN_STRING = "jdbc:mysql://localhost/Library?useSSL=false&serverTimezone=UTC";
	private ConnectionManager() {

	}


	//
	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	private boolean openConnection()  {
		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public Connection getConnection()  {
		if (connection == null) {
			if (openConnection()) {
				System.out.println("Connection open!");
				return connection;
			} else {
				return null;
			}
		}
		return connection;
	}

	public void close() {
		System.out.println("Connection closed! ");
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
