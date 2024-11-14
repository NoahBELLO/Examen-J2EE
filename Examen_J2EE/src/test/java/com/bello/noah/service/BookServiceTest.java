package com.bello.noah.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bello.noah.dto.BookDto;
import com.bello.noah.exception.PostBookIdException;
import com.bello.noah.exception.PostBookStatusException;
import com.bello.noah.model.BookModel;
import com.bello.noah.repository.BookRepository;

public class BookServiceTest {
	@Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getServiceBook_shouldReturnBook_whenBookExists() {
        // Arrange
    	BookModel book = new BookModel();
        book.setId(1L);
        book.setTitle("The Little Prince");
        book.setAuthor("Antoine de Saint-Exupéry");
        book.setIsbn("978-0156012195");
        book.setPublishedDate("1943-04-06");
        book.setStatus("available");
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        BookDto result = bookService.getBookService(1L);

        // Assert
        assertNotNull(result);
        assertEquals("The Little Prince", result.getTitle());
        assertEquals("Antoine de Saint-Exupéry", result.getAuthor());
        assertEquals("978-0156012195", result.getIsbn());
        assertEquals("1943-04-06", result.getPublishedDate());
        assertEquals("available", result.getStatus());
    }

    @Test
    void postServiceBook_shouldSaveBook() throws PostBookIdException, PostBookStatusException{
        // Arrange
        BookDto bookDto = new BookDto();
        bookDto.setTitle("To Kill a Mockingbird");
        bookDto.setAuthor("Harper Lee");
        bookDto.setIsbn("978-0060935467");
        bookDto.setPublishedDate("1960-07-11");
        bookDto.setStatus("available");

        BookModel savedBookModel = new BookModel();
        savedBookModel.setId(1L); // Assurez-vous de définir un ID ou tout autre champ utilisé dans modelToDto

        when(bookRepository.save(any(BookModel.class))).thenReturn(savedBookModel);

        // Act
        bookService.postBookService(bookDto);

        // Assert
        verify(bookRepository, times(1)).save(any(BookModel.class));
    }

    @Test
    void deleteServiceBook_shouldDeleteBook_whenBookExists() {
        // Arrange
        when(bookRepository.existsById(1L)).thenReturn(true);

        // Act
        bookService.deleteBookService(1L);

        // Assert
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteServiceBook_shouldThrowException_whenBookDoesNotExist() {
        // Arrange
        when(bookRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookService.deleteBookService(1L));
    }
}
