package com.buildstore.vermeg.model;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="client_order")
public class Order {
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(mappedBy="order", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	private List<OrderLine> orderLines;

	public Order() {
		super();
	}
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="id_user")
	private User user;
	

	public Order(int id,List<OrderLine> orderLines) {
		super();
		this.id = id;
		this.orderLines = orderLines;
	}
	

	public Order( List<OrderLine> orderLines) {
		super();
		this.orderLines = orderLines;
	}


	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@JsonManagedReference(value="order")
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}


	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	@JsonBackReference(value="client")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
}
