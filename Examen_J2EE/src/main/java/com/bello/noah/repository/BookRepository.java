package com.bello.noah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bello.noah.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Long> {
	
	public List<BookModel> findByTitleLikeOrAuthorLike(String title, String author);
	
}
	