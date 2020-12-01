package com.buildstore.vermeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buildstore.vermeg.Service.BookService;
import com.buildstore.vermeg.model.Book;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	@RequestMapping(value= "/getall",method=RequestMethod.GET,produces = "application/json")
	public List<Book> getbooks(){
	    return this.bookService.getAllBooks();
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public Book getBookById(@RequestParam int idbook) {
		return bookService.getBook(idbook);
	}

	@RequestMapping(value="/addBook",method=RequestMethod.POST)
	public void addbook(@RequestBody Book book) {
		bookService.addBook(book);
	}
	@RequestMapping(value="/updateBook",method=RequestMethod.PUT)
	public String updateBook(@RequestBody Book book) {
		bookService.updateBook(book);
	return "bookDetails";
	}
	@RequestMapping(value="/deleteBook",method=RequestMethod.DELETE)
	public String  deletebook(@RequestParam int idbook ){
		bookService.deleteBook(idbook);
			return "redirect:/getAllBooks";
	}
	
}
