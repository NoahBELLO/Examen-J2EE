package com.bello.noah.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class BookModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	private String isbn; 
	private String publishedDate;
	private String status; //(ex : available, borrowed)
	
	public BookModel(String title, String author, String isbn, String publishedDate, String status) {
		this.title = title; 
		this.author = author; 
		this.isbn = isbn; 
		this.publishedDate = isbn; 
		this.status = status;
	}
	
	public BookModel() {
		
	}

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
