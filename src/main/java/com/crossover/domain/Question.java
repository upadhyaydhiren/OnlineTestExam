package com.crossover.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is POJO class of Question in Test Exam System.
 *
 */
@Entity(name = "test_questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Long questionId;
	private String question;
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionTable(name = "questions_answers", joinColumns = @JoinColumn(name = "question_id"))
	private List<String> answers;
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionTable(name = "questions_correct_answers", joinColumns = @JoinColumn(name = "question_id"))
	private List<String> correctAnswers;
	@Column(name = "question_score", nullable = false)
	private Integer questionScore;

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(List<String> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Integer getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(Integer questionScore) {
		this.questionScore = questionScore;
	}

}
