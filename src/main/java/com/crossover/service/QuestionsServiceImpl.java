package com.crossover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.dao.QuestionsDao;
import com.crossover.domain.Question;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Service Implementation  class of Question in Test Exam System.
 *
 */
@Service
@Transactional
public class QuestionsServiceImpl implements QuestionsService {
	@Autowired
	QuestionsDao questionsDaoImpl;

	public boolean insertQuestionRow(Question question) throws Exception {

		return questionsDaoImpl.insertQuestionRow(question);
	}

	public boolean updateQuestionRow(Question question) throws Exception {

		return questionsDaoImpl.updateQuestionRow(question);
	}

	public Question getQuestionRowById(Long questionId) throws Exception {
		return questionsDaoImpl.getQuestionRowById(questionId);
	}

	public List<Question> getAllQuestionRow() throws Exception {
		return questionsDaoImpl.getAllQuestionRow();
	}

}
