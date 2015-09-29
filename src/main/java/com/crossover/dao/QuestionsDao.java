package com.crossover.dao;

import java.util.List;

import com.crossover.domain.Question;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Dao interface of Question in Test Exam System.
 *
 */
public interface QuestionsDao {

	public boolean insertQuestionRow(Question question) throws Exception;

	public boolean updateQuestionRow(Question question) throws Exception;

	public Question getQuestionRowById(Long questionId) throws Exception;

	public List<Question> getAllQuestionRow() throws Exception;
}
