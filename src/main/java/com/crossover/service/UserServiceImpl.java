package com.crossover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.dao.UserDao;
import com.crossover.domain.User;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Service Implementation  class of User in Test Exam System.
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDaoImpl;

	public void insertUserRow(User user) throws Exception {
		userDaoImpl.insertUserRow(user);

	}

	public void updateUserRow(User user) throws Exception {
		userDaoImpl.updateUserRow(user);
	}

	public User getUserByUserName(String userName) throws Exception {
		return userDaoImpl.getUserByUserName(userName);
	}

	public List<User> getAllUsers() throws Exception {
		return userDaoImpl.getAllUsers();
	}

}
