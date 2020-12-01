package com.buildstore.vermeg.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buildstore.vermeg.model.Order;
import com.buildstore.vermeg.model.OrderLine;

@Repository
public class OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory=sf;
	}
	
	public List<Order> getALLOrders(){
		Session session = this.sessionFactory.getCurrentSession();
       
		return session.createQuery("from Order").list();
	}
	public Order findorderById(int idorder) {
		Session session = this.sessionFactory.getCurrentSession();
		Order o =(Order)session.get(Order.class, idorder);
		
	  return o;
		
	}
	public Order getOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Order o = (Order) session.get(Order.class, new Integer(id));
		return o;
	}

	public Order addOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(order);
		return order;
	}

	public void updatOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(order);
	}

		
}
