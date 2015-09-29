package com.crossover.dao;

import java.util.List;
import com.crossover.domain.User;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Dao interface of User in Test Exam System.
 *
 */
public interface UserDao {
	public void insertUserRow(User user) throws Exception;

	public void updateUserRow(User user) throws Exception;

	public User getUserByUserName(String userName) throws Exception;

	public List<User> getAllUsers() throws Exception;
}
