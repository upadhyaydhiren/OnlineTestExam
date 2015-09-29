package com.crossover.service;

import java.util.List;
import com.crossover.domain.TestExam;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Service interface of TestExam class in Test Exam System.
 *
 */
public interface TestExamService {
	public boolean insertTestExamRow(TestExam testExam) throws Exception;

	public boolean updateTestExamRow(TestExam testExam) throws Exception;

	public TestExam getTestExamRowById(Long testExamId) throws Exception;

	public List<TestExam> getAllTestExams() throws Exception;

}
