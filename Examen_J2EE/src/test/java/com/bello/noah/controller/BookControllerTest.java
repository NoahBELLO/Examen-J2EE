package com.bello.noah.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bello.noah.dto.BookDto;
import com.bello.noah.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	//@WithMockUser(username = "user", roles = { "USER" })
	public void testGetWithBook() throws Exception {
		List<BookDto> bookDtos = new ArrayList<>();
		BookDto book = new BookDto();
		book.setTitle("The Little Prince");
        book.setAuthor("Antoine de Saint-Exupéry");
        book.setIsbn("978-0156012195");
        book.setPublishedDate("1943-04-06");
        book.setStatus("available");
		book.setId(1l);
		bookDtos.add(book);
		when(bookService.getAllBooksService()).thenReturn(bookDtos);
		mockMvc.perform(get("/book")).andExpect(status().isOk());
		mockMvc.perform(get("/book")).andExpect(content().contentType(MediaType.APPLICATION_JSON));
		mockMvc.perform(get("/book")).andExpect(content().json("[{\"id\":1,\"title\":\"The Little Prince\",\"author\":\"Antoine de Saint-Exupéry\"}]"));
		
	}
}
