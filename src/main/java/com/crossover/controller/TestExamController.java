package com.crossover.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.domain.Question;
import com.crossover.domain.TestExam;
import com.crossover.domain.User;
import com.crossover.service.QuestionsService;
import com.crossover.service.TestExamService;
import com.crossover.service.UserService;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is Spring Annotated  Controller Class for Handling all Request.
 */
@Controller
public class TestExamController {

	@Autowired
	QuestionsService questionService;
	@Autowired
	TestExamService testExamService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/")
	public String index() {
		return "redirect:index";
	}

	// Generic modal Attribute for User object population
	@ModelAttribute
	public User getUser() {
		try {
			User user = new User();
			List<TestExam> testExams = new ArrayList<TestExam>();
			List<TestExam> fetchedExam = testExamService.getAllTestExams();
			if (fetchedExam != null && !fetchedExam.isEmpty())
				testExams.add(fetchedExam.get(0));// only Use first Test Exam By
													// Default Because in this
													// system current user have
													// no option to select with
													// list of test exams;
			else
				testExams.add(new TestExam());
			user.setTestExams(testExams);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Home Request Handler
	@RequestMapping(value = "index")
	public String home(HttpServletRequest request, ModelMap map) {
		try {
			if (request.getSession().getAttribute("validuser") != null)
				return "redirect:startexam";
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	// Authenticate Request handler
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView signIn(@ModelAttribute User user,
			HttpServletRequest request, ModelMap modelMap) {
		try {
			User fetchedUser = userService
					.getUserByUserName(user.getUserName());
			if (fetchedUser != null
					&& fetchedUser.getPassword().equals(user.getPassword())) {
				fetchedUser.setTestExams(user.getTestExams());
				request.getSession().setAttribute("validuser", fetchedUser);
				return new ModelAndView("redirect:startexam");
			} else {
				modelMap.addAttribute("info", "Your Information is Incorrect");
				return new ModelAndView("index", modelMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("error", "Server Error");
			return new ModelAndView("index", modelMap);
		}
	}

	// Start Exam
	@RequestMapping(value = "startexam", method = RequestMethod.GET)
	public ModelAndView startExam(HttpServletRequest request, ModelMap modelMap) {
		try {
			Object userObject = request.getSession().getAttribute("validuser");
			if (userObject != null) {
				User user = (User) userObject;
				if (callBackTimer(request.getSession(), user)) {
					List<Question> questions = questionService
							.getAllQuestionRow();
					modelMap.addAttribute("questions", questions);
					modelMap.addAttribute("validuser", user);
					modelMap.addAttribute("success", "Your Test is start now");
					return new ModelAndView("index", modelMap);
				} else {
					request.getSession().invalidate();
					modelMap.addAttribute("info", "Your Test time is passed");
					return new ModelAndView("index", modelMap);
				}
			} else {
				request.getSession().invalidate();
				modelMap.addAttribute("info", "Session TimeOut");
				return new ModelAndView("index", modelMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().invalidate();
			modelMap.addAttribute("error", "Server Error");
			return new ModelAndView("index", modelMap);
		}
	}

	private Boolean callBackTimer(HttpSession session, User user) {
		if (session.getLastAccessedTime() - session.getCreationTime() < user
				.getTestExams().get(0).getTestDuration())
			return true;
		return false;
	}

	// CallBack Timer
	@RequestMapping(value = "timer")
	@ResponseBody
	public Boolean verifyTimer(HttpServletRequest request) {
		try {
			Object userObject = request.getSession().getAttribute("validuser");
			if (userObject == null)
				return false;
			User user = (User) userObject;
			return callBackTimer(request.getSession(), user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Logut Handler
	@RequestMapping(value = "logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:index";
	}

	// Test Submit Handler
	@RequestMapping(value = "finishtest", method = RequestMethod.POST)
	public ModelAndView submitAnswer(@ModelAttribute User user,
			HttpServletRequest request, ModelMap map) {
		try {
			Object object = request.getSession().getAttribute("validuser");
			if (object == null)
				return new ModelAndView("redirect:index");
			User validUser = (User) object;
			User fetchedUser = userService.getUserByUserName(validUser
					.getUserName());
			int testScore = 0;
			TestExam fetchTestExam = testExamService.getTestExamRowById(user
					.getTestExams().get(0).getTestExamId());
			if (user != null)
				for (int i = 0; i < user.getTestExams().get(0)
						.getAttemptQuestions().size(); i++) {
					Question fetchedQuestion = questionService
							.getQuestionRowById(user.getTestExams().get(0)
									.getAttemptQuestions().get(i)
									.getQuestionId());
					fetchTestExam.getAttemptQuestions().add(fetchedQuestion);
					for (String correctAnswer : fetchedQuestion
							.getCorrectAnswers()) {
						for (String userAnswer : user.getTestExams().get(0)
								.getAttemptQuestions().get(i).getAnswers()) {
							if (correctAnswer.equals(userAnswer)) {
								testScore += fetchedQuestion.getQuestionScore();
							}

						}
					}
				}
			fetchTestExam.setTestCompletionTime(user.getTestExams().get(0)
					.getTestCompletionTime());
			fetchTestExam.setTestScore(testScore);
			fetchedUser.getTestExams().add(fetchTestExam);
			userService.updateUserRow(fetchedUser);
			map.put("success", "Your Score is Submit.You got " + testScore
					+ " marks");
			request.getSession().invalidate();
			return new ModelAndView("index", map);
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().invalidate();
			map.put("error", "Server Error");
			return new ModelAndView("index", map);
		}
	}
}