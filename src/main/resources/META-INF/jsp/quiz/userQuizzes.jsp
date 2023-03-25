<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Quizzes</title>
</head>
<body>
<c:if test="${not empty deleteMessage}">
    <div class="alert alert-success alert-dismissible fade show d-flex align-items-center" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:">
            <use xlink:href="#check-circle-fill"/>
        </svg>
        <div>
            <c:out value="${deleteMessage}"/>
        </div>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<div class="container p-5">
    <div class="row mb-4">
        <h2 class="text-center fw-normal">
            <fmt:message key="text.quiz.list.title"/>
        </h2>
        <hr class="mt-2 mb-3 w-100"/>
    </div>

    <c:forEach items="${quizzes}" var="quiz">
        <c:url var="showQuizUrl" value="/quiz">
            <c:param name="action" value="VIEW"/>
            <c:param name="quizId" value="${quiz.id}"/>
        </c:url>

        <div class="card border-0 shadow-lg p-3 m-3">
            <div class="row ms-3 mt-3">
                <small>
                    <fmt:message key="text.category"/>
                    <strong class="ps-1">
                        <c:out value="${quiz.category}"/>
                    </strong>
                </small>
            </div>

            <div class="row ms-3">
                <small class="opacity-25">
                    <fmt:message key="text.created.on"/>
                    <fmt:formatDate value="${quiz.created}" pattern="dd/MM/yyyy"/>
                </small>
            </div>

            <div class="row ms-3">
                <div class="col">
                    <small class="opacity-50">
                        <fmt:message key="text.number.questions"/>
                        <c:out value="${quiz.questions.size()}"/>
                    </small>
                    <small class="opacity-50 ps-2">
                        <fmt:message key="text.marks"/>
                        <c:out value="${quiz.questions.size()}"/>
                    </small>
                </div>
            </div>

            <div class="row m-3">
                <h2 class="fw-bold">
                    <c:out value="${quiz.title}"/>
                </h2>
                <pre class="text-truncate w-100">
                    <c:out value="${quiz.description}"/>
                </pre>
            </div>

            <div class="row ms-3 mb-3">
                <div class="w-25">
                    <a href="${showQuizUrl}" class="btn btn-primary" role="button">
                        <fmt:message key="btn.show.quiz"/>
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
