<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Solve Quiz</title>
</head>
<body>
<div class="container p-5 w-50">
  <div class="card shadow-lg">
    <div class="row ms-3 mt-3 me-3">
      <small>
        <fmt:message key="text.category"/>
        <strong class="ps-1">
          <c:out value="${answer.quiz.category}"/>
        </strong>
      </small>
    </div>

    <div class="row m-3">
      <h2 class="text-center fw-normal">
        <c:out value="${answer.quiz.title}"/>
      </h2>
      <hr class="mt-2 mb-3 w-100"/>
    </div>

    <div class="row m-3">
            <pre class="text-wrap text-break">
                <c:out value="${answer.quiz.description}"/>
            </pre>
    </div>

    <div class="row m-3">
      <h3 class="text-center fw-normal">
        <fmt:message key="text.questions"/>
      </h3>
    </div>

    <form:form method="post" modelAttribute="answer">
      <div class="row m-5">
        <c:forEach items="${answer.quiz.questions}" var="question" varStatus="s">
                    <pre class="text-wrap text-break m-0 p-0">
                        <c:out value="${s.index + 1 += '. ' += question.description}"/>
                    </pre>
          <div class="form-check ms-3 mb-3 mt-1">
            <c:forEach items="${question.options}" var="option" varStatus="i">
              <form:radiobutton path="answers[${s.index}]" value="${option}" cssClass="form-check-input"/>
              <form:label path="answers[${s.index}]" cssClass="form-check-label">
                <c:out value="${option}"/>
              </form:label>
              <br>
            </c:forEach>
          </div>
        </c:forEach>
        <form:errors path="answers" cssClass="text-danger small ps-0"/>
      </div>

      <div class="row m-5">
        <div class="m-0 p-0">
          <form:button type="submit"
                       class="btn btn-primary">
            <fmt:message key="btn.submit.quiz"/>
          </form:button>
        </div>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>
