package com.library.main;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.library.dao.BookDaoImpl;
import com.library.dao.UserDaoImpl;

public class Main {
	public static void main(String[] args) throws SQLException {

		UserDaoImpl user = new UserDaoImpl();
		BookDaoImpl book = new BookDaoImpl();
		ArrayList<com.library.dto.Book> books=book.readAll();
		System.out.println("To add user please enter 0! ");
		System.out.println("To add a new book please enter 1! ");
		Scanner input = new Scanner(System.in);
		int choose = input.nextInt();
		switch (choose) {
		case 0:
			user.addUser();

			break;
		case 1:
			book.addBook();
			break;
		case 2:
			book.takeBook();
			System.out.println("Book taken!");
			break;
		case 3:
			
			book.returnBook();
			System.out.println("Book returned! ");
			
			
break;
		}
	}
}
