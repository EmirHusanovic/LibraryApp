package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

import com.library.dto.Book;
import com.library.dto.User;
import com.mysql.jdbc.Statement;

public class UserDaoImpl implements UserDaoInterface {

	Connection connection = ConnectionManager.getInstance().getConnection();

	@Override
	public ArrayList<User> readAll() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		String query = "Select * from Users";

		Statement stm = (Statement) connection.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while (rs.next()) {
			User user1 = new User();
			user1.setName(rs.getString("Name"));
			user1.setIdNum(rs.getInt("idUser"));
			user1.setNumOfBooks(rs.getInt("NumOfBooks"));
			users.add(user1);
		}

		return users;

	}

	@Override
	public void addUser() throws SQLException {
		String query = "Insert into Users(Name,NumOfBooks) Values( ?,?)";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your name");
		String name = input.nextLine();
		input.close();
		int numOfBooks = 0;
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, name);
			statement.setInt(2, numOfBooks);
			statement.executeUpdate();
			System.out.println("User added! ");
		}

	}

}
