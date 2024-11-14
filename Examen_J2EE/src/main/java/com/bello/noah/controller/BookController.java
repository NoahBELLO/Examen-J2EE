package com.bello.noah.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bello.noah.dto.BookDto;
import com.bello.noah.exception.PostBookIdException;
import com.bello.noah.exception.PostBookStatusException;
import com.bello.noah.exception.RessourceNotFoundException;
import com.bello.noah.service.BookService;

@RestController
@RequestMapping("book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping()
	public Collection<BookDto> getAllBooks() {
		return bookService.getAllBooksService();
	}
	
	@GetMapping("/{id}")
	public BookDto getBook(@PathVariable("id") Long id) {
		return bookService.getBookService(id);
	}
	
	@GetMapping("/title/{title}")
	public Collection<BookDto> getBookByTitle(@PathVariable("title") String name) {
		return bookService.getBookByTitleService(name);
	}
	
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable("id") Long id) throws RessourceNotFoundException {
		bookService.deleteBookService(id);
		return "Livre supprimé";
	}
	
	@PostMapping("/ajoutBook")
	public String createBook(@ModelAttribute BookDto bookDto, Model model) throws PostBookIdException, PostBookStatusException {
		bookService.postBookService(bookDto);
		model.addAttribute("message", "Livre enregistré avec succès !");
	    model.addAttribute("bookDto", new BookDto());  // Réinitialiser l'objet bookDto pour réinitialiser le formulaire
	    model.addAttribute("bookLien", "/bookPage");
	    return "redirect:/ajoutBook"; 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) throws PostBookStatusException{
		BookDto updatedBook = bookService.putBookService(id, bookDto);
	    return ResponseEntity.ok(updatedBook);
	}
	
	@GetMapping("creationBDD")
	public void populate() throws PostBookIdException, PostBookStatusException {
		BookDto bookDto = new BookDto();
		bookDto.setTitle("Le Petit Prince");
		bookDto.setAuthor("Antoine de Saint-Exupéry");
		bookDto.setIsbn("978-0156012195");
		bookDto.setPublishedDate("1943-04-06");
		bookDto.setStatus("available");
		bookService.postBookService(bookDto);
		
		bookDto = new BookDto();
		bookDto.setTitle("1984");
		bookDto.setAuthor("George Orwell");
		bookDto.setIsbn("978-0451524935");
		bookDto.setPublishedDate("1949-06-08");
		bookDto.setStatus("borrowed");
		bookService.postBookService(bookDto);
		
		bookDto = new BookDto();
		bookDto.setTitle("Pride and Prejudice");
		bookDto.setAuthor("Jane Austen");
		bookDto.setIsbn("978-0141439518");
		bookDto.setPublishedDate("1813-01-28");
		bookDto.setStatus("available");
		bookService.postBookService(bookDto);
		
		bookDto = new BookDto();
		bookDto.setTitle("To Kill a Mockingbird");
		bookDto.setAuthor("Harper Lee");
		bookDto.setIsbn("978-0060935467");
		bookDto.setPublishedDate("1960-07-11");
		bookDto.setStatus("borrowed");
		bookService.postBookService(bookDto);
	}
}
