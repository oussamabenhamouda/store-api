package com.buildstore.vermeg.book;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buildstore.vermeg.DAO.BookDAO;
import com.buildstore.vermeg.Service.BookService;
import com.buildstore.vermeg.model.Book;

@ExtendWith(MockitoExtension.class)
public class BookServiceTestt {
	@Mock
	BookDAO bookDao;
	
	@InjectMocks
	BookService bookService;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllBooks() {
		 List<Book> list = new ArrayList<Book>();
		 Book One = new Book(1, "John", "John",14d,null, 1);
		 Book Two = new Book(2, "John", "John",14d,new Date(55), 1);
		 Book Three = new Book(3, "John", "John",14d,new Date(55), 1);
	         
	        list.add(One);
	        list.add(Two);
	        list.add(Three);
	         
	        when( bookDao.getAllBooks()).thenReturn(list);	 
	        assertTrue(this.bookService.getAllBooks().size() == 3);
	       
	}

	@Test
	public void testGetBook() {
		Book book1 = new Book(1,"jhin","psyco",12d,new Date(45),3);
		when(bookDao.getBook(1)).thenReturn(book1);
		assertTrue(this.bookService.getBook(1).equals(book1));
	}

	@Test
	public void testAddBook() {
		List<Book> books = new ArrayList<Book>();
		Book bookone = new Book(2, "John", "John",14d,new Date(55), 1);
		books.add(bookone);
		bookService.addBook(bookone);
		verify(bookDao,times(1)).addBook(bookone);
	}

	@Test
	 public void testUpdateBook() {
		Book bookone = new Book(2, "John", "John",14d,new Date(55), 1);
		bookService.updateBook(bookone);
		verify(bookDao,times(1)).updateBook(bookone);;
	}

	@Test
	public void testDeleteBook() {
		Book book2=new Book(1,"soussi","karawaki",22d,new Date(6),6);
		bookService.deleteBook(1);
		verify(bookDao,times(1)).deleteBook(1);
	}

}