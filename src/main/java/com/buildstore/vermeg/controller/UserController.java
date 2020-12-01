package com.buildstore.vermeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buildstore.vermeg.Service.UserService;
import com.buildstore.vermeg.model.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value ="/getAll", method= RequestMethod.GET, produces="application/json")
	public List<User> getUsers(){
		
		return this.userService.getAllUsers();
		
	}
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public User getUserById(@RequestParam int iduser) {
		return userService.getuser(iduser);
	}

	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		this.userService.adduser(user);
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
			
		       this.userService.updateuser(user);;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	public String deleteUser(@RequestParam  int iduser) {
			userService.deleteuser(iduser);
			return "redirect:/getAllUsers";

	}	
}

