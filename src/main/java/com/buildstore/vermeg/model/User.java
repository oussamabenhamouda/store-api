package com.buildstore.vermeg.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user")

public class User {

	@Id
	@Column
	@GeneratedValue
	 int id_user;
	
	@Column(name="name")
	String name;
	
	@Column(name="surname")
	String surname;
	
	@Column(name="adress")
	String adress;
	@OneToMany(mappedBy="user",fetch= FetchType.EAGER)
	private List<Order> orderlist;
	

	public User() {
		super();
	}

	public User(int id_user, String name, String surname, String adress) {
		super();
		this.id_user = id_user;
		this.name = name;
		this.surname = surname;
		this.adress = adress;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@JsonManagedReference(value="client")
	public List<Order> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}
	
	
}
