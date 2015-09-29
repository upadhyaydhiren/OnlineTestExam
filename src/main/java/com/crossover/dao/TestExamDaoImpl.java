package com.crossover.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.domain.TestExam;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Dao Implementation  class of TestExam in Test Exam System.
 *
 */
@Repository
@Transactional
public class TestExamDaoImpl implements TestExamDao {

	@Autowired
	SessionFactory sessionFactory;

	public boolean insertTestExamRow(TestExam testExam) throws Exception {
		sessionFactory.getCurrentSession().save(testExam);
		return (Long) sessionFactory.getCurrentSession()
				.getIdentifier(testExam) > 0;
	}

	public boolean updateTestExamRow(TestExam testExam) throws Exception {
		sessionFactory.getCurrentSession().update(testExam);
		return (Long) sessionFactory.getCurrentSession()
				.getIdentifier(testExam) > 0;
	}

	public TestExam getTestExamRowById(Long testExamId) throws Exception {
		return (TestExam) sessionFactory.getCurrentSession().get(
				TestExam.class, testExamId);
	}

	@SuppressWarnings("unchecked")
	public List<TestExam> getAllTestExams() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(TestExam.class).list();
	}

}
