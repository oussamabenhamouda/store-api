package com.buildstore.vermeg.model;



import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue
	long id;
	
	@Column(name="title_book")
	String title;
	
	@Column(name="author_book")
	String author;
	
	@Column(name="price_book")
	Double price;
	
	@Column(name="releasedate_book")
	Date releaseDate;
	
	@Column(name="quantity_book")
	int quantity;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="book")
	private List<OrderLine> orderline;

	public Book() {
		super();
	}

	public Book(long id, String title, String author, Double price, Date releaseDate, int quantity) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.releaseDate = releaseDate;
		this.quantity = quantity;
	}

	public Book(long id, String title, String author, Double price, Date releaseDate, int quantity, List<OrderLine> orderline) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.releaseDate = releaseDate;
		this.quantity = quantity;
		this.orderline=orderline;
	
	}
	

	@JsonManagedReference(value="orderline")
	public List<OrderLine> getOrderline() {
		return orderline;
	}

	public void setOrderline(List<OrderLine> orderline) {
		this.orderline = orderline;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

