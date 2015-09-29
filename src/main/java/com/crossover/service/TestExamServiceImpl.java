package com.crossover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.dao.TestExamDao;
import com.crossover.domain.TestExam;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Service Implementation  class of TestExam in Test Exam System.
 *
 */
@Service
@Transactional
public class TestExamServiceImpl implements TestExamService {

	@Autowired
	TestExamDao testExamDaoImpl;

	public boolean insertTestExamRow(TestExam testExam) throws Exception {
		return testExamDaoImpl.insertTestExamRow(testExam);
	}

	public boolean updateTestExamRow(TestExam testExam) throws Exception {
		return testExamDaoImpl.updateTestExamRow(testExam);
	}

	public TestExam getTestExamRowById(Long testExamId) throws Exception {
		return testExamDaoImpl.getTestExamRowById(testExamId);
	}

	public List<TestExam> getAllTestExams() throws Exception {
		return testExamDaoImpl.getAllTestExams();
	}

}
