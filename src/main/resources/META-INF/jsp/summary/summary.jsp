<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Summary</title>
</head>
<body>
<div class="container p-5">
    <div class="row mb-4">
        <h2 class="text-center fw-normal">
            <fmt:message key="text.summary.title"/>
        </h2>
        <hr class="mt-2 mb-3 w-100"/>
    </div>

    <div class="card border-0 shadow-lg p-3 m-3">
        <div class="row">
            <h2 class="ms-3 mt-3 text-center fw-normal">
                <fmt:message key="text.your.score"/>
                <strong class="ps-1">
                    <c:out value="${summary.currentScore}"/>
                </strong>
            </h2>
            <h2 class="ms-3 mt-3 text-center fw-normal">
                <fmt:message key="text.total.marks"/>
                <strong class="ps-1">
                    <c:out value="${answer.answers.size()}"/>
                </strong>
            </h2>
            <h2 class="ms-3 mt-3 text-center fw-normal">
                <fmt:message key="text.best.score"/>
                <strong>
                    <c:out value="${summary.bestScore}"/>
                </strong>
            </h2>
        </div>

        <c:choose>
            <c:when test="${empty summary.wrongQuestions}">
                <div class="row m-3">
                    <h3 class="text-center fw-normal">
                        <fmt:message key="text.congratulations"/>
                    </h3>
                    <hr class="m-2 w-100"/>
                </div>
            </c:when>

            <c:otherwise>
                <div class="row m-3">
                    <hr class="mt-5 m-2 w-100"/>
                    <h3 class="text-center fw-normal">
                        <fmt:message key="text.details"/>
                    </h3>
                    <hr class="m-2 w-100"/>
                </div>

                <div class="row m-3">
                    <h4 class="text-center fw-normal">
                        <fmt:message key="text.wrong.answers"/>
                    </h4>
                </div>

                <c:forEach items="${summary.wrongQuestions}" var="wrongQuestion" varStatus="s">
                    <div class="row">
                        <div class="m-3">
                            <strong>
                                <fmt:message key="text.question"/>
                            </strong>
                            <c:out value="${wrongQuestion.description}"/>
                        </div>
                        <div class="m-3">
                            <strong>
                                <fmt:message key="text.your.answer"/>
                            </strong>
                            <c:out value="${answer.wrongAnswers[s.index]}"/>
                        </div>
                        <div class="row">
                            <div class="m-3">
                                <strong>
                                    <fmt:message key="text.correct.answer"/>
                                </strong>
                                <c:out value="${wrongQuestion.answer}"/>
                            </div>
                            <hr class="mt-2 mb-3 w-100"/>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
