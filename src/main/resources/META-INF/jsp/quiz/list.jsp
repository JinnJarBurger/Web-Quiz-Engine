<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quizzes</title>
</head>
<body>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row shadow my-auto mx-auto ps-4 py-4">
            <div class="row mb-3">
                <h2 class="text-center fw-normal">
                    <fmt:message key="list.quiz.title"/>
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
                            <fmt:message key="table.header.title"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.header.category"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.header.created.by"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="table.header.actions"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${quizzes}" var="quiz" varStatus="loop">
                        <c:url var="formUrl" value="/quiz">
                            <c:param name="quizId" value="${quiz.id}"/>
                        </c:url>
                        <c:url var="solveQuizUrl" value="/answer">
                            <c:param name="quizId" value="${quiz.id}"/>
                        </c:url>

                        <tr>
                            <th scope="row">
                                <c:out value="${loop.index + 1}"/>
                            </th>
                            <td>
                                <c:out value="${quiz.title}"/>
                            </td>
                            <td>
                                <c:out value="${quiz.category}"/>
                            </td>
                            <td>
                                <c:out value="${quiz.createdBy.username}"/>
                            </td>
                            <td>
                                <a href="${solveQuizUrl}"
                                   class="link-danger m-1 text-decoration-none">
                                    <strong>
                                        <fmt:message key="btn.take.quiz"/>
                                    </strong>
                                </a>
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
