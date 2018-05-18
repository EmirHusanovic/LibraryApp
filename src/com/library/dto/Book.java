package com.library.dto;

public class Book {
	int idNum;
	String BookName;
	boolean available;
	public Book(){
		
	}
	public Book(int idNum, String BookName, boolean available){
		
	}
	

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
