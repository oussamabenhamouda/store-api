package com.buildstore.vermeg.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buildstore.vermeg.DAO.BookDAO;
import com.buildstore.vermeg.model.Book;

@Service("BookService")
public class BookService {

	@Autowired
	BookDAO bookDao;
	
	@Transactional
	public List<Book>getAllBooks(){
		return bookDao.getAllBooks();
		
	}
	@Transactional
	public Book getBook(int idbook) {
		return bookDao.getBook(idbook);
	}
	
	@Transactional
	public void addBook(Book book) {
		bookDao.addBook(book);
	}
	@Transactional
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}
	
	@Transactional
	public void deleteBook(int idbook) {
		bookDao.deleteBook(idbook);
	}
	
}

