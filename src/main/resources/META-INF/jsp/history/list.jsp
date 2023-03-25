<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>History</title>
</head>
<body>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row shadow my-auto mx-auto ps-4 py-4">
            <div class="row mb-3">
                <h2 class="text-center fw-normal">
                    <fmt:message key="list.history.title"/>
                </h2>
                <hr class="mt-2 mb-3 w-100"/>
            </div>

            <div class="row">
                <table class="table table-borderless table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th scope="col">
                            <fmt:message key="label.id"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.quiz.title"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.history.score"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.wrong.answers"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.best.score"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.attempted.on"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${histories}" var="history" varStatus="loop">
                        <c:url value="/answer" var="answerUrl">
                            <c:param name="quizId" value="${history.answer.quiz.id}"/>
                        </c:url>
                        <tr>
                            <th scope="row">
                                <c:out value="${loop.index + 1}"/>
                            </th>
                            <td>
                                <a href="${answerUrl}"
                                   class="link-danger m-1 text-decoration-none">
                                    <strong>
                                        <c:out value="${history.answer.quiz.title}"/>
                                    </strong>
                                </a>
                            </td>
                            <td>
                                <c:out value="${history.score}"/>
                            </td>
                            <td>
                                <c:out value="${history.answer.wrongAnswers.size()}"/>
                            </td>
                            <td>
                                <c:out value="${history.summaryLog.bestScore}"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${history.created}" pattern="dd/MM/yyyy hh:mm:ss a"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
