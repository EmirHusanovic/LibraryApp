package com.library.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.library.dto.User;

public interface UserDaoInterface {

	public void addUser() throws SQLException;
	public ArrayList<User> readAll() throws SQLException;

}
