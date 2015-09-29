package com.crossover.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.domain.User;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Dao Implementation  class of User in Test Exam System.
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	public void insertUserRow(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);
	}

	public void updateUserRow(User user) throws Exception {
		sessionFactory.getCurrentSession().update(user);

	}

	public User getUserByUserName(String userName) throws Exception {
		return (User) sessionFactory.getCurrentSession().get(User.class,
				userName);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(User.class)
				.list();
	}

}
