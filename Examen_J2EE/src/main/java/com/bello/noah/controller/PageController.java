package com.bello.noah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bello.noah.dto.BookDto;
import com.bello.noah.service.BookService;

@Controller
public class PageController {
	@Autowired
	private BookService bookService;

	/** Home page. */
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "Welcome");
		return "home";
	}

	/** Home page. */
	@GetMapping("/bookPage")
	public String bookPage(Model model) {
		model.addAttribute("message", "Listes des livres");
		model.addAttribute("books", bookService.getAllBooksService());
		return "bookPage";
	}
	
	@GetMapping("/ajoutBook")
	public String ajoutBook(Model model) {
		BookDto book = new BookDto();
		model.addAttribute("bookDto", book);
		model.addAttribute("bookLien", "/bookPage");
		return "ajoutBook";
	}
}