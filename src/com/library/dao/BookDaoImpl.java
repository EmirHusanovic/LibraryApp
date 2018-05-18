package com.library.dao;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.library.dto.Book;
import com.library.dto.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BookDaoImpl implements BookDaoInterface {

	Connection connection = (Connection) ConnectionManager.getInstance().getConnection();

	@Override
	public ArrayList<Book> readAll() throws SQLException {
		ArrayList<Book> books = new ArrayList<>();
		String query = "Select * from Books";

		Statement stm = (Statement) connection.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while (rs.next()) {
			Book books1 = new Book();
			books1.setBookName(rs.getString("nameOfBook"));
			books1.setIdNum(rs.getInt("idBooks"));
			books1.setAvailable(rs.getBoolean("status"));
			books.add(books1);
		}

		return books;

	}

	@Override
	public void addBook() throws SQLException {

		String query = "Insert into Books(nameOfBook,status) Values( ?,?)";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name of the book: ");
		String name = input.nextLine();
		input.close();
		boolean status = false;
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, name);
			statement.setBoolean(2, status);
			statement.executeUpdate();
			System.out.println("Book added! ");
		}

	}

	@Override
	public void takeBook() throws SQLException {
		BookDaoImpl book1 = new BookDaoImpl();
		ArrayList<Book> books = book1.readAll();
		UserDaoImpl user = new UserDaoImpl();
		ArrayList<User> users = user.readAll();

		String query = "Update Books set status=? where idBooks=?";
		String query1 = "Update Users set NumOfBooks=? where idUser=?";
		int numofBooks = 0;

		Scanner input = new Scanner(System.in);
		System.out.println("Please, enter the number of the book: ");
		int idNumBook = input.nextInt();
		for (int i = 0; i < books.size(); i++) {
			if (idNumBook > books.size()) {
				System.out.println("There isn't book with that number! ");
				System.exit(1);
			}
			if (idNumBook == books.get(i).getIdNum() && books.get(i).isAvailable() == true) {
				System.out.println("Book isn't avaliable!  ");
				System.exit(1);
			}
		}
		System.out.println("Please, enter the User ID: ");
		int idUser = input.nextInt();
		for (int i = 0; i < users.size(); i++) {
			if (idUser > users.size()) {
				System.out.println("There isn't user with that number! ");
				System.exit(1);
			}
			if (users.get(i).getIdNum() == idUser) {
				numofBooks = users.get(i).getNumOfBooks();
				if ((numofBooks = users.get(i).getNumOfBooks()) > 3) {
					System.out.println("You already have three books! ");
					System.exit(1);
				}
			}

		}
		numofBooks = numofBooks + 1;
		try (PreparedStatement statement = connection.prepareStatement(query);
				PreparedStatement statement1 = connection.prepareStatement(query1)) {
			statement.setBoolean(1, true);
			statement.setInt(2, idNumBook);
			statement1.setInt(1, numofBooks);
			statement1.setInt(2, idUser);
			statement.executeUpdate();
			statement1.executeUpdate();
		}

	}

	@Override
	public void returnBook() throws SQLException {
		BookDaoImpl book1 = new BookDaoImpl();
		ArrayList<Book> books = book1.readAll();
		UserDaoImpl user = new UserDaoImpl();
		ArrayList<User> users = user.readAll();

		String query = "Update Books set status=? where idBooks=?";
		String query1 = "Update Users set NumOfBooks=? where idUser=?";
		int numofBooks = 0;

		Scanner input = new Scanner(System.in);
		System.out.println("Please, enter the number of the book: ");
		int idNumBook = input.nextInt();
		for (int i = 0; i < books.size(); i++) {
			if (idNumBook > books.size()) {
				System.out.println("There isn't book with that number! ");
				System.exit(1);
			}

		}
		System.out.println("Please, enter the User ID: ");
		int idUser = input.nextInt();
		for (int i = 0; i < users.size(); i++) {
			if (idUser > users.size()) {
				System.out.println("There isn't user with that number! ");
				System.exit(1);
			}

			if (idUser == users.get(i).getIdNum()) {
				numofBooks = users.get(i).getNumOfBooks();
			}
		}
		numofBooks = numofBooks - 1;
		try (PreparedStatement statement = connection.prepareStatement(query);
				PreparedStatement statement1 = connection.prepareStatement(query1)) {
			statement.setBoolean(1, false);
			statement.setInt(2, idNumBook);
			statement1.setInt(1, numofBooks);
			statement1.setInt(2, idUser);
			statement.executeUpdate();
			statement1.executeUpdate();
		}

	}

}
