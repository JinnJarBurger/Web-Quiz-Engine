<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Categories</title>
</head>
<body>
<div class="d-flex flex-column" style="min-height: 80vh">
    <div class="my-auto mx-auto">
        <div class="row justify-content-center fw-normal font-monospace">
            <h2 class="text-center">
                <fmt:message key="text.categories"/>
            </h2>
            <hr class="mt-3 mb-3 w-100"/>
        </div>

        <c:forEach var="category" items="${categories}">
            <c:url value="/quiz/list" var="quizzesUrl">
                <c:param name="category" value="${category}"/>
            </c:url>

            <a href="${quizzesUrl}"
               class="btn btn-outline-dark btn-rounded m-1 w-100 p-3 rounded-pill" data-mdb-ripple-color="dark">
                <c:out value="${category}"/>
            </a>
        </c:forEach>
    </div>
</div>
</body>
</html>
