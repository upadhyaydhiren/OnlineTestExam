package com.crossover.dao;

import java.util.List;

import com.crossover.domain.TestExam;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Dao interface of TestExam in Test Exam System.
 *
 */
public interface TestExamDao {
	public boolean insertTestExamRow(TestExam testExam) throws Exception;

	public boolean updateTestExamRow(TestExam testExam) throws Exception;

	public TestExam getTestExamRowById(Long testExamId) throws Exception;

	public List<TestExam> getAllTestExams() throws Exception;
}
