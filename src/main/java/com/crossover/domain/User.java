package com.crossover.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is POJO class of User in Test Exam System.
 *
 */
@Entity(name = "user")
public class User {

	@Id
	@Column(name = "user_name")
	private String userName;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_test_exams", joinColumns = @JoinColumn(name = "user_name"), inverseJoinColumns = @JoinColumn(name = "test_exam_id"))
	private List<TestExam> testExams;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTestExams(List<TestExam> testExams) {
		this.testExams = testExams;
	}

	public List<TestExam> getTestExams() {
		return testExams;
	}

}
