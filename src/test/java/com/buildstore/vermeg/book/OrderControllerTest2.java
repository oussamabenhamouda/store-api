package com.buildstore.vermeg.book;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.buildstore.vermeg.Service.OrderService;
import com.buildstore.vermeg.controller.OrderController;
import com.buildstore.vermeg.helper.Mapper;
import com.buildstore.vermeg.model.Order;
import com.buildstore.vermeg.model.OrderLine;
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest2 {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private OrderService mockedOrderService;
	@InjectMocks
	private OrderController orderController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}
	@Test
	public void shouldReturnOrderList() throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(new Order(null));
		orderList.add(new Order(null));
		when(this.mockedOrderService.getAllOrders()).thenReturn(orderList);
		this.mockMvc.perform(get("/api/orders/getall")).andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(2))).andDo(print());
	}
	
	@Test
	public void shouldAddOrderSuccessfully() throws Exception {
		Order order001 = new Order(1, new ArrayList<OrderLine>());
		doNothing().when(this.mockedOrderService).addorder(any(Order.class));
		this.mockMvc.perform(post("/api/orders/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Mapper.asJsonString(order001))
				).andExpect(status().is2xxSuccessful())
				.andDo(print());
	
	}
	
	@Test
	public void shouldFailAddingOrderAndReturnClientError() throws Exception {
		this.mockMvc.perform(post("/api/orders/add")
				).andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	
}