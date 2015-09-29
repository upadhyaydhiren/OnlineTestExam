package com.crossover.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.domain.Question;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Dao Implementation  class of Question in Test Exam System.
 *
 */
@Repository
@Transactional
public class QuestionsDaoImpl implements QuestionsDao {

	@Autowired
	SessionFactory sessionFactory;

	public boolean insertQuestionRow(Question question) throws Exception {
		sessionFactory.getCurrentSession().save(question);
		return (Long) sessionFactory.getCurrentSession()
				.getIdentifier(question) > 0;
	}

	public boolean updateQuestionRow(Question question) throws Exception {
		sessionFactory.getCurrentSession().update(question);
		return (Long) sessionFactory.getCurrentSession()
				.getIdentifier(question) > 0;
	}

	public Question getQuestionRowById(Long questionId) throws Exception {
		return (Question) sessionFactory.getCurrentSession().get(
				Question.class, questionId);
	}

	@SuppressWarnings("unchecked")
	public List<Question> getAllQuestionRow() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Question.class)
				.add(Restrictions.sqlRestriction("1=1 order by rand()"))
				.setMaxResults(10).list();
	}

}
