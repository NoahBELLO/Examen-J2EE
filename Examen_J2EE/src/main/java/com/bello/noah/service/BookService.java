package com.bello.noah.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bello.noah.dto.BookDto;
import com.bello.noah.exception.PostBookIdException;
import com.bello.noah.exception.PostBookStatusException;
import com.bello.noah.exception.RessourceNotFoundException;
import com.bello.noah.model.BookModel;
import com.bello.noah.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Collection<BookDto> getAllBooksService() {
		return modelsToDtos(bookRepository.findAll());
	}
	
	public Collection<BookDto> getBookByTitleService(String title) {
		return modelsToDtos(bookRepository.findByTitleLikeOrAuthorLike("%"+title+"%", "%"+title+"%"));
	}
	
	public BookDto getBookService(Long id) {
		Optional<BookModel> bookModel = bookRepository.findById(id);
		if(bookModel.isPresent()) {
			return modelToDto(bookModel.get());	
		}
		return null;
		
	}
	
	public void postBookService(BookDto dto) throws PostBookIdException, PostBookStatusException{
		if(dto.getId()!=null) {
			throw new PostBookIdException("Il est interdit de mettre un Id afin de créer un livre");
		}
		if(!dto.getStatus().equals("available") && !dto.getStatus().equals("borrowed")) {
			throw new PostBookStatusException("Le statut d'un livre est soit disponible, soit emprunté");
		}
		modelToDto(bookRepository.save(dtoToModel(dto)));
	}
	
	public BookDto putBookService(Long id, BookDto dto) throws PostBookStatusException{
		BookModel book = bookRepository.findById(id).orElse(null);
		book.setTitle(dto.getTitle());
		book.setAuthor(dto.getAuthor());
		book.setIsbn(dto.getIsbn());
		book.setPublishedDate(dto.getPublishedDate());
		if(!dto.getStatus().equals("available") && !dto.getStatus().equals("borrowed")) {
			throw new PostBookStatusException("Le statut d'un livre est soit disponible, soit emprunté");
		}
		else {
			book.setStatus(dto.getStatus());				
		}		
		return modelToDto(bookRepository.save(book));
	}
	
	public void deleteBookService(Long id) throws RessourceNotFoundException {
		if (!bookRepository.existsById(id)) {
	        throw new RessourceNotFoundException("Le livre avec l'id " + id + " n'existe pas");
	    }
		bookRepository.deleteById(id);
	}
	
	private BookDto modelToDto(BookModel bookModel) {
		if (bookModel == null) {
	        return null; 
	    }
		BookDto bookDto = new BookDto();
		bookDto.setId(bookModel.getId());
		bookDto.setTitle(bookModel.getTitle());
		bookDto.setAuthor(bookModel.getAuthor());
		bookDto.setIsbn(bookModel.getIsbn());
		bookDto.setPublishedDate(bookModel.getPublishedDate());
		bookDto.setStatus(bookModel.getStatus());		
		return bookDto;
	}

	private BookModel dtoToModel(BookDto bookDto) {
		BookModel bookModel = new BookModel();		
		bookModel.setId(bookDto.getId());
		bookModel.setTitle(bookDto.getTitle());
		bookModel.setAuthor(bookDto.getAuthor());
		bookModel.setIsbn(bookDto.getIsbn());
		bookModel.setPublishedDate(bookDto.getPublishedDate());
		bookModel.setStatus(bookDto.getStatus());	
		return bookModel;
	}
	
	private Collection<BookDto> modelsToDtos(Iterable<BookModel> bookModels) {
		Collection<BookDto> bookDtos = new ArrayList<>();
		bookModels.forEach(bookModel -> {
			bookDtos.add(modelToDto(bookModel));
		});
		return bookDtos;
	}
}