package com.library.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.library.dto.Book;
import com.library.dto.User;

public interface BookDaoInterface {

	public void addBook() throws SQLException ;
	public void takeBook() throws SQLException;
	public ArrayList<Book> readAll() throws SQLException ;
	public void returnBook() throws SQLException;
}
