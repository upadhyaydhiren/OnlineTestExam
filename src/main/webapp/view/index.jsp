<%@page import="java.util.Date"%>
<%@page import="com.crossover.domain.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.3/css/bootstrap-select.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/flipclock.css" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.3/js/bootstrap-select.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/flipclock.js"></script>
<style type="text/css">
body {
	padding-top: 50px;
	padding-bottom: 20px;
}
</style>
</head>
<body>
	<c:if test="${error ne null}">
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Warning!</strong>${error}
		</div>
	</c:if>
	<c:if test="${success ne null}">
		<div class="alert alert-success alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Well done!</strong>${success}
		</div>
	</c:if>
	<c:if test="${info ne null}">
		<div class="alert alert-info alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Heads up!</strong>${info}
		</div>
	</c:if>
	<%
		Object userObject = request.getSession().getAttribute("validuser");
		if (userObject == null) {
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">CrossOver Test Exam</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form:form cssClass="navbar-form navbar-right" modelAttribute="user"
					action="login" method="post">
					<div class="form-group">
						<form:input path="userName" cssClass="form-control"
							placeholder="Username" />
					</div>
					<div class="form-group">
						<form:password path="password" cssClass="form-control"
							placeholder="Password" />
					</div>
					<form:hidden path="testExams[0].testExamId" />
					<form:button type="submit" class="btn btn-success">Sign In</form:button>
				</form:form>
			</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1>
				<c:if test="${user.getTestExams().get(0).getTestTitle() ne null}">
		${user.getTestExams().get(0).getTestTitle()}
		</c:if>
			</h1>
			<h2>Description</h2>
			<p>
				<c:if test="${user.getTestExams().get(0).getDescription() ne null}">
		${user.getTestExams().get(0).getDescription()}
		</c:if>
			</p>
			<p>
				<b>Test Durection</b>:&nbsp;
				<c:if test="${user.getTestExams().get(0).getTestDuration() ne null}">
					<c:set var="duration"
						value="${user.getTestExams().get(0).getTestDuration()}" />
					<%
						long duration = Long.valueOf(pageContext.getAttribute(
										"duration").toString());
								int seconds = (int) (duration / 1000) % 60;
								int minutes = (int) ((duration / (1000 * 60)) % 60);
								int hours = (int) ((duration / (1000 * 60 * 60)) % 24);
								String durationString = "";
								if (hours != 0) {
									durationString = String.format(
											"%d hours %d minutes %d seconds ", hours,
											minutes, seconds);
								} else if ((hours == 0) && (minutes != 0)) {
									durationString = String.format("%d minutes %d seconds",
											minutes, seconds);
								} else if ((hours == 0) && (minutes == 0)) {
									durationString = String.format("%d seconds", seconds);
								}
					%>
					<%=durationString%>
				</c:if>
			</p>
			<p>Dont Refresh The Page</p>
		</div>
	</div>
	<%
		}
		if (userObject != null) {
			User user = (User) userObject;
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">CrossOver Test Exam</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<div class="navbar-right">
					Welcome
					<%=user.getUserName()%>
					<a href="logout" class="btn btn-info">Log out</a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<select class="selectpicker" id="questionCombo">
			<c:forEach items="${questions}" var="question"
				varStatus="questionCount">
				<option value="${questionCount.count}">Question${questionCount.count}</option>
			</c:forEach>
		</select>
		<button type="button" onclick="goToQuestion();"
			class="btn btn-primary">Go</button>
		<div class="pull-right">
			<div class="clock"></div>
		</div>
		<form:form modelAttribute="validuser" action="finishtest"
			name="testExam">
			<c:forEach items="${questions}" var="question"
				varStatus="questionCount">
				<c:if test="${questionCount.count==1}">
					<div class="container" id="questiondiv${questionCount.count}">
						<div class="row">Q${questionCount.count}.&nbsp;${question.getQuestion()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${question.getQuestionScore()}
							marks</div>
						<div id="answerdiv${questionCount.count}">
							Answer:<br>
							<form:hidden
								path="testExams[0].attemptQuestions[${questionCount.index}].questionId"
								value="${question.getQuestionId()}" />
							<c:forEach items="${question.getAnswers()}" var="answer"
								varStatus="answerCount">
								<form:checkbox
									path="testExams[0].attemptQuestions[${questionCount.index}].answers[${answerCount.index}]"
									value="${answer}" />${answer}<br>
							</c:forEach>
							<button type="button"
								onclick="submitAnswer('${questionCount.count+1}');"
								id="nextQuestion${questionCount.count}" class="btn btn-primary">Submit
								Answer</button>
						</div>
					</div>
				</c:if>
				<c:if
					test="${questionCount.count>1&&questionCount.count<questions.size()}">
					<div class="container collapse"
						id="questiondiv${questionCount.count}">
						<div class="row">Q${questionCount.count}.&nbsp;${question.getQuestion()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${question.getQuestionScore()}
							marks</div>
						<div id="answerdiv${questionCount.count}">
							Answer:<br>
							<form:hidden
								path="testExams[0].attemptQuestions[${questionCount.index}].questionId"
								value="${question.getQuestionId()}" />
							<form:hidden
								path="testExams[0].attemptQuestions[${questionCount.index}].questionScore"
								value="${question.getQuestionScore()}" />
							<c:forEach items="${question.getAnswers()}" var="answer"
								varStatus="answerCount">
								<form:checkbox
									path="testExams[0].attemptQuestions[${questionCount.index}].answers[${answerCount.index}]"
									value="${answer}" />${answer}
								<br>
							</c:forEach>
							<button type="button"
								onclick="submitAnswer('${questionCount.count+1}');"
								id="nextQuestion${questionCount.count}" class="btn btn-primary">Submit
								Answer</button>
						</div>
					</div>
				</c:if>
				<c:if test="${questionCount.count==questions.size()}">
					<div class="container collapse"
						id="questiondiv${questionCount.count}">
						<div class="row">Q${questionCount.count}.&nbsp;${question.getQuestion()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${question.getQuestionScore()}
							marks</div>
						<div id="answerdiv${questionCount.count}" class="row">
							Answer:<br>
							<form:hidden
								path="testExams[0].attemptQuestions[${questionCount.index}].questionId"
								value="${question.getQuestionId()}" />
							<form:hidden
								path="testExams[0].attemptQuestions[${questionCount.index}].questionScore"
								value="${question.getQuestionScore()}" />
							<c:forEach items="${question.getAnswers()}" var="answer"
								varStatus="answerCount">
								<form:checkbox
									path="testExams[0].attemptQuestions[${questionCount.index}].answers[${answerCount.index}]"
									value="${answer}" />${answer}<br>
							</c:forEach>

							<button type="button" onclick="finishTest();" id="next"
								class="btn btn-primary">Finish Test</button>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<br>
			<form:hidden path="testExams[0].testCompletionTime"
				id="completiontime" />
			<button type="button" onclick="finishTest();" class="btn btn-primary"
				id="finish">Finish Test</button>
		</form:form>
	</div>
	<script type="text/javascript">
		var time = '${user.getTestExams().get(0).getTestDuration()}';
		var timeNumber = Number(time) / 1000;
		var clock = $('.clock').FlipClock(timeNumber, {
			countdown : true
		});
		console.log(time + " " + clock.getTime());
	</script>
	<script type="text/javascript">
		function disableF5(e) {
			if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82)
				e.preventDefault();
		};
		$(document).on("keydown", disableF5);
	</script>
	<%
		}
	%>
</body>
<script type="text/javascript">
	$('.selectpicker').selectpicker();
</script>
<script type="text/javascript">
	function goToQuestion() {
		var selectVal = $('#questionCombo').val();
		console.log(selectVal);
		submitAnswer(selectVal);
	}
	function submitAnswer(param) {
		var lastValue = $('#questionCombo option:last-child').val();
		if (param == lastValue) {
			$("#finish").hide();
		} else {
			$("#finish").show();
		}
		$('#questionCombo option').each(function() {
			if (param !== $(this).val()) {
				$('#questiondiv' + $(this).val()).hide();
				$('#nextQuestion' + $(this).val()).hide();
			} else {
				$('#questiondiv' + $(this).val()).show();
				$('#nextQuestion' + $(this).val()).show();
			}
		});
	}
	function finishTest() {
		var attepmtQuestion = [];
		var message = "";
		$('#questionCombo option').each(
				function() {
					var questionNo = $(this).val();
					$('#answerdiv' + questionNo).children("input:checked").map(
							function() {
								attepmtQuestion.push(questionNo);
							});
				});
		if (attepmtQuestion.length > 0) {
			message += "You are only Question no. "
			for (var i = 0; i < attepmtQuestion.length - 1; i++) {
				message += (attepmtQuestion[i]) + ", ";
			}
			message += attepmtQuestion[attepmtQuestion.length - 1]
					+ " is attempt.";
		} else {
			message += "You are not attempt any Question";
		}
		message += " You Want to Finish Test";
		var answer = confirm(message);
		if (answer == true) {
			var completeTime = clock.getTime();
			completeTime = completeTime * 1000;
			completeTime = timeNumber * 1000 - completeTime;
			$('#completiontime').val(completeTime);
			document.testExam.submit();
		} else {
			return false;
		}
	}
</script>
</html>