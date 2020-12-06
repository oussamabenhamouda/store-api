package com.buildstore.vermeg.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="orderline")
public class OrderLine {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="id_order")
	private Order order;
	
	@ManyToOne( fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="book")
	private Book book;
	
	@Column(name="quantity")
	private int quantity;

	public OrderLine(long id, Order order, Book book, int quantity) {
		super();
		this.id = id;
		this.order = order;
		this.book=book;
		this.quantity = quantity;
	}
	public OrderLine(Book book, int quantity) {
		super();
		this.book=book;
		this.quantity = quantity;
	}
	public OrderLine() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonBackReference(value="order")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@JsonBackReference(value="orderline")
	public Book getBook() {
		return book;
	}

	public void setProduct(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
