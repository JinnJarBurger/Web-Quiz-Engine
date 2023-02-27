<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/quiz" var="quizFormUrl">
    <c:param name="quizId" value="${quiz.id}"/>
</c:url>
<c:url value="/question" var="questionUrl">
    <c:param name="quizId" value="${quiz.id}"/>
</c:url>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
<c:if test="${not empty addMessage}">
    <div class="alert alert-success alert-dismissible fade show d-flex align-items-center" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:">
            <use xlink:href="#check-circle-fill"/>
        </svg>
        <div>
            <c:out value="${addMessage}"/>
        </div>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
<c:if test="${not empty updateMessage}">
    <div class="alert alert-success alert-dismissible fade show d-flex align-items-center" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:">
            <use xlink:href="#check-circle-fill"/>
        </svg>
        <div>
            <c:out value="${updateMessage}"/>
        </div>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
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
    <div class="card shadow-lg p-3 m-3">
        <div class="row ms-3 mt-3 me-3">
            <small>
                <fmt:message key="text.category"/>
                <strong class="ps-1">
                    <c:out value="${quiz.category}"/>
                </strong>

                <a href="${quizFormUrl}"
                   class="float-end text-info text-decoration-none small">
                    <fmt:message key="btn.edit"/>
                </a>
            </small>
        </div>

        <div class="row m-3">
            <h2 class="text-center fw-normal">
                <c:out value="${quiz.title}"/>
            </h2>
            <hr class="mt-2 mb-3 w-100"/>
        </div>

        <div class="row m-3">
            <pre class="text-wrap text-break">
                <c:out value="${quiz.description}"/>
            </pre>
        </div>

        <div class="row m-3">
            <div class="m-0 p-0">
                <a class="btn btn-primary float-end" href="${questionUrl}" role="button">
                    <fmt:message key="btn.add.question"/>
                </a>
            </div>
        </div>

        <div class="row m-3">
            <hr class="w-100 m-0 p-0"/>
            <small class="text-center m-0 p-0">
                <fmt:message key="text.questions"/>
            </small>
            <hr class="w-100 m-0 p-0"/>
        </div>

        <c:forEach items="${quiz.questions}" var="question">
            <c:url var="questionEditUrl" value="${questionUrl}">
                <c:param name="questionId" value="${question.id}"/>
            </c:url>

            <div class="row m-3">
                <div class="row">
                    <small class="p-0">
                        <small>
                            <fmt:message key="text.created.on"/>
                            <fmt:formatDate value="${question.created}" pattern="dd/MM/yyyy"/>
                        </small>

                        <a href="${questionEditUrl}"
                           class="float-end text-info text-decoration-none small">
                            <fmt:message key="btn.edit"/>
                        </a>
                    </small>
                </div>

                <div class="row">
                    <fmt:message key="text.description"/>
                    <c:out value="${question.description}"/>
                </div>

                <br/>
                <br/>

                <div class="row">
                    <fmt:message key="text.options"/>
                    <br>
                    <c:forEach items="${question.options}" var="option" varStatus="s">
                        <c:out value="${s.index + 1 += '. ' += option}"/>
                        <br>
                    </c:forEach>
                </div>

                <br/>
                <br/>

                <div class="row">
                    <fmt:message key="text.answer"/>
                    <c:out value="${question.answer}"/>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
