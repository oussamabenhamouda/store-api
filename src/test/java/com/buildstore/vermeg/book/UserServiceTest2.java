package com.buildstore.vermeg.book;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buildstore.vermeg.DAO.UserDAO;
import com.buildstore.vermeg.Service.UserService;
import com.buildstore.vermeg.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest2 {
	
	@Mock
	UserDAO userDao;
	
	@InjectMocks
	UserService userService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	public void testGetAllUsers() {
		List<User> list = new ArrayList<User>();
		 User One = new User(1, "John", "John","madrid");
		 User Two = new User(2, "John", "John","marseille");
		 User Three = new User(3, "John", "John","paris");
	         
	        list.add(One);
	        list.add(Two);
	        list.add(Three);
	         
	        when( userDao.getALLUsers()).thenReturn(list);	 
	        assertTrue(this.userService.getAllUsers().size() == 3);
	       
	}

	@Test
	public void testGetUser() {
		User user1 = new User(1, "John", "John","madrid");
		when(userDao.getuser(1)).thenReturn(user1);
		assertTrue(this.userService.getuser(1).equals(user1));
	}

	@Test
	public void testAdduser() {
		List<User> users = new ArrayList<User>();
		User user1=new User(1, "John", "John","madrid");
		userService.adduser(user1);
		verify(userDao,times(1)).adduser(user1);
	}

	@Test
	 public void testUpdateuser() {
		User user1=new User(1, "John", "John","madrid");
		userService.updateuser(user1);
		verify(userDao,times(1)).updateuser(user1);;
	}

	@Test
	public void testDeleteuser() {
		User user1=new User(2, "John", "John","madrid");
		userService.deleteuser(2);
		verify(userDao,times(1)).deleteuser(2);
	}


}