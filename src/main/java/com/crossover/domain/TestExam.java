package com.crossover.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is POJO class of Test Exam in Test Exam System.
 *
 */

@Entity(name = "test_exam")
public class TestExam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "test_exam_id")
	private Long testExamId;
	@Column(name = "test_title")
	private String testTitle;
	private String description;
	@Column(name = "test_duration")
	private Long testDuration;
	@Column(name = "test_completion_time")
	private Long testCompletionTime;
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "test_exam_questions", joinColumns = @JoinColumn(name = "test_exam_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> attemptQuestions;
	private Integer testScore;

	public Long getTestExamId() {
		return testExamId;
	}

	public void setTestExamId(Long testExamId) {
		this.testExamId = testExamId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTestDuration() {
		return testDuration;
	}

	public void setTestDuration(Long testDuration) {
		this.testDuration = testDuration;
	}

	public Long getTestCompletionTime() {
		return testCompletionTime;
	}

	public void setTestCompletionTime(Long testCompletionTime) {
		this.testCompletionTime = testCompletionTime;
	}

	public void setAttemptQuestions(List<Question> attemptQuestions) {
		this.attemptQuestions = attemptQuestions;
	}

	public List<Question> getAttemptQuestions() {
		return attemptQuestions;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	public String getTestTitle() {
		return testTitle;
	}

	public void setTestScore(Integer testScore) {
		this.testScore = testScore;
	}

	public Integer getTestScore() {
		return testScore;
	}
}
