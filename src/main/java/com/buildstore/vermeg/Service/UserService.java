package com.buildstore.vermeg.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buildstore.vermeg.DAO.UserDAO;
import com.buildstore.vermeg.model.User;

@Service("UserService")
public class UserService {

	@Autowired
	UserDAO userDao;
	
	@Transactional
	public List<User>getAllUsers(){
		return userDao.getALLUsers();
	}
	@Transactional
	public User getuser(int iduser) {
		return userDao.getuser(iduser);
	}

	@Transactional
	public void adduser(User user) {
		userDao.adduser(user);
	}

	@Transactional
	public void updateuser(User user) {
		userDao.updateuser(user);

	}

	@Transactional
	public void deleteuser(int iduser) {
		userDao.deleteuser(iduser);
	}
}
