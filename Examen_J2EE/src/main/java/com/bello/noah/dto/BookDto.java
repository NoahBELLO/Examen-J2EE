package com.bello.noah.dto;

public class BookDto {
	private Long id;
	private String title;
	private String author;
	private String isbn; 
	private String publishedDate;
	private String status; //(ex : available, borrowed)
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	public String getIsbn() {
		return isbn;
	}
	
	public String getPublishedDate() {
		return publishedDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}