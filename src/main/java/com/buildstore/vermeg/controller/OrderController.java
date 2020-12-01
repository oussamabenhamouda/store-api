package com.buildstore.vermeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buildstore.vermeg.Service.OrderService;
import com.buildstore.vermeg.model.Order;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value= "/getall",method=RequestMethod.GET,produces = "application/json")
	public List<Order> getorders(){
	    return this.orderService.getAllOrders();
	}
	
	
	@RequestMapping(value="/getSum",method=RequestMethod.GET,produces="application/json")
	public double getSum(@RequestParam int idorder ) {
		return orderService.getsum(idorder);
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void addorder(@RequestBody Order order) {
		this.orderService.addorder(order);
	}
	
	
}
