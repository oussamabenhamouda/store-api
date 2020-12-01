package com.buildstore.vermeg.Service;

import java.util.List;

import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buildstore.vermeg.DAO.OrderDAO;
import com.buildstore.vermeg.model.Order;
import com.buildstore.vermeg.model.OrderLine;

@Service("orderService")

public class OrderService {

	@Autowired
	OrderDAO orderDao;
	
	@Transactional
	public List<Order> getAllOrders(){
		return orderDao.getALLOrders();
	}
	@Transactional
	public double getsum(int orderId) {
		Order or =  this.orderDao.findorderById(orderId);
		double sum=0;
		for(OrderLine ol : or.getOrderLines() ) {
		sum= sum+ ol.getQuantity()*ol.getBook().getPrice();
		}
		return sum;
	}
	
	@Transactional
	public Order getorder(int idorder) {
		return orderDao.getOrder(idorder);
	}

	@Transactional
	public void addorder(Order order) {
		orderDao.addOrder(order);
	}

	@Transactional
	public void updatorder(Order order) {
		orderDao.updatOrder(order);

	}

	
}