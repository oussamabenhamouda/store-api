package com.buildstore.vermeg.book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buildstore.vermeg.DAO.OrderDAO;
import com.buildstore.vermeg.Service.OrderService;
import com.buildstore.vermeg.model.Order;

@ExtendWith(MockitoExtension.class)

public class OrderServiceTe {

	
	@Mock
	OrderDAO orderDao;
	
	@InjectMocks
	OrderService orderService;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllOrders() {
		List<Order> orders=new ArrayList<Order>();
		Order order1 = new Order(2,null);
		Order order2 = new Order(3,null);
		orders.add(order1);
		orders.add(order2);
		when(orderDao.getALLOrders()).thenReturn(orders);
		assertTrue(this.orderService.getAllOrders().size()==2);
	}

	@Test
	public void testGetorder() {
		Order order1= new Order(1,null);
		when(orderDao.getOrder(1)).thenReturn(order1);
		assertTrue(this.orderService.getorder(1).equals(order1));
	}

	@Test
	public void testAddorder() {
		List<Order> orders=new ArrayList<Order>();
		Order order1 = new Order(2,null);
		Order order2 = new Order(3,null);
		orderService.addorder(order1);
		orderService.addorder(order2);
		verify(orderDao,times(1)).addOrder(order1);
		verify(orderDao,times(1)).addOrder(order2);
	}

	@Test
	public void testUpdateorder() {
		Order order2 = new Order(3,null);
		orderService.updatorder(order2);
		verify(orderDao,times(1)).updatOrder(order2);
	}

}