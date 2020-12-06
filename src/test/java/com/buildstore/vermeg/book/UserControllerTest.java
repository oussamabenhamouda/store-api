package com.buildstore.vermeg.book;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
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

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.buildstore.vermeg.Service.UserService;
import com.buildstore.vermeg.controller.UserController;
import com.buildstore.vermeg.helper.Mapper;
import com.buildstore.vermeg.model.User;

public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private UserService mockedUserService;
	@InjectMocks
	private UserController userController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	@Test
	public void shouldReturnUserList() throws Exception {
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User(1,"jacer", "sfar","marseille"));
		userList.add(new User(2,"acer", "lahmer","madrid"));
		when(this.mockedUserService.getAllUsers()).thenReturn(userList);
		this.mockMvc.perform(get("/api/user/getAll")).andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(2))).andDo(print());
	}
	
	@Test
	public void shouldAddClientSuccessfully() throws Exception {
		User user = new User(1,"jacer", "sfar","marseille");
		doNothing().when(this.mockedUserService).adduser(any(User.class));
		this.mockMvc.perform(post("/api/user/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Mapper.asJsonString(user))
				).andExpect(status().isOk())
				.andDo(print());
		
	}
	
	@Test
	public void shouldFailAddingUserAndReturnClientError() throws Exception {
		this.mockMvc.perform(post("/api/user/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	public void shouldUpdateClientSuccessfully() throws Exception {
		User user = new User(1,"jacer", "sfar","marseille");
		
		doNothing().when(this.mockedUserService).updateuser(any(User.class));
		this.mockMvc.perform(put("/api/user/updateUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Mapper.asJsonString(user))
				).andExpect(status().isOk());
	}
	
	@Test
	public void shouldFailUpdatingUserAndReturnClientError() throws Exception {
		this.mockMvc.perform(put("/api/user/updateUser")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	public void shouldDeleteUserSuccessfully() throws Exception {
		User user = new User(1,"jacer", "sfar","marseille");
		doNothing().when(this.mockedUserService).deleteuser(1);
		this.mockMvc.perform(delete("/api/user/deleteUser").param("iduser", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Mapper.asJsonString(user))
				).andExpect(status().isOk())
				.andDo(print());		
	}
	
	@Test
	public void shouldFailDeletingAndReturnClientError() throws Exception {
		this.mockMvc.perform(delete("/api/user/deleteUser")
				).andExpect(status().is4xxClientError())
				.andDo(print());
	}
}
