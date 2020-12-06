package com.buildstore.vermeg.book;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.buildstore.vermeg.Service.BookService;
import com.buildstore.vermeg.controller.BookController;
import com.buildstore.vermeg.helper.Mapper;
import com.buildstore.vermeg.model.Book;

public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private BookService mockedProductService;
	@InjectMocks
	private BookController productController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	@Test
	public void shouldReturnProductList() throws Exception {
		ArrayList<Book> productList = new ArrayList<Book>();
		productList.add(new Book(1, "hello", "test",55d,new Date(54),4));
		productList.add(new Book(1, "hello", "test",55d,new Date(54),4));
		when(mockedProductService.getAllBooks()).thenReturn(productList);
		this.mockMvc.perform(get("/api/book/getall")).andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(2))).andDo(print());
	}
	
	@Test
	public void shouldAddProductSuccessfully() throws Exception {
		Book book = new Book(1, "hello", "test",55d,new Date(54),4);
		doNothing().when(this.mockedProductService).addBook(any(Book.class));
		this.mockMvc.perform(post("/api/book/addBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Mapper.asJsonString(book))
				).andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFailAddingProductAndReturnClientError() throws Exception {
		this.mockMvc.perform(post("/api/book/addBook")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	public void shouldUpdateProductSuccessfully() throws Exception {
		Book book = new Book(1, "hello", "test",55d,new Date(54),4);
		doNothing().when(this.mockedProductService).updateBook(any(Book.class));
		this.mockMvc.perform(put("/api/book/updateBook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Mapper.asJsonString(book))
				).andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void shouldFailUpdatingProductAndReturnClientError() throws Exception {
		this.mockMvc.perform(put("/api/book/updateBook")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteProductSuccessfully() throws Exception {
		Book book = new Book(1, "hello", "test",55d,new Date(54),4);
		doNothing().when(this.mockedProductService).deleteBook(1);
		this.mockMvc.perform(delete("/api/book/deleteBook").param("idbook", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Mapper.asJsonString(book))).
				andExpect(status().is2xxSuccessful())
				.andDo(print());
	}
	
	@Test
	public void shouldFailDeletingAndReturnClientError() throws Exception {
		this.mockMvc.perform(delete("/api/book/deleteBook")
				).andExpect(status().is4xxClientError())
				.andDo(print());
	}
}