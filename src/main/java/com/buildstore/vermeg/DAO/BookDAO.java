package com.buildstore.vermeg.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buildstore.vermeg.model.Book;

@Repository
public class BookDAO {

	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	public List<Book> getAllBooks() {
		return this.sessionFactory.getCurrentSession().createQuery("from Book").list();
	}

	public Book getBook(int idbook) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = (Book) session.get(Book.class, new Integer(idbook));
		return book;
	}

	public Book addBook(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(book);
		return book;
	}

	public void updateBook(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(book);
	}

	public void deleteBook(int idbook) {
		Session session = this.sessionFactory.getCurrentSession();
		Book b = (Book) session.load(Book.class, new Integer(idbook));
		if (null != b) {
			session.delete(b);
		}
	}
	
}
