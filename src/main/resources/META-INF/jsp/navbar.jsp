<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/" var="home"/>
<c:url value="/quiz/categories" var="categoriesUrl"/>
<c:url value="/user/login" var="loginUrl"/>
<c:url value="/user/logout" var="logoutUrl"/>
<c:url value="/quiz" var="quizFormUrl"/>
<c:url value="/quiz/list" var="quizzesUrl"/>
<c:url value="/quiz/user/quizzes" var="userQuizzesUrl"/>
<c:url value="/history/list" var="historiesUrl"/>
<html>
<head>
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
        </symbol>
        <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"></path>
        </symbol>
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
        </symbol>
    </svg>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"/>">
    <script src="<c:url value="/webjars/jquery/3.6.2/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>"></script>
    <title></title>
</head>
<body>
<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a href="${home}" class="navbar-brand">
            <fmt:message key="btn.web.quiz.engine"/>
        </a>

        <button type="button"
                class="navbar-toggler"
                data-bs-toggle="collapse"
                data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav">
                <a href="${home}" class="nav-item nav-link active">
                    <fmt:message key="btn.home"/>
                </a>
                <c:if test="${user.student or user.admin}">
                    <a href="${categoriesUrl}" class="nav-item nav-link">
                        <fmt:message key="btn.view.categories"/>
                    </a>
                </c:if>
                <c:if test="${user.student or user.admin}">
                    <a href="${quizzesUrl}" class="nav-item nav-link">
                        <fmt:message key="btn.view.quizzes"/>
                    </a>
                </c:if>
                <c:if test="${user.teacher or user.admin}">
                    <a href="${quizFormUrl}" class="nav-item nav-link">
                        <fmt:message key="btn.view.quiz.form"/>
                    </a>
                </c:if>
                <c:if test="${user.teacher or user.admin}">
                    <a href="${userQuizzesUrl}" class="nav-item nav-link">
                        <fmt:message key="btn.view.user.quizzes"/>
                    </a>
                </c:if>
                <c:if test="${user.student or user.admin}">
                    <a href="${historiesUrl}" class="nav-item nav-link">
                        <fmt:message key="btn.view.user.history"/>
                    </a>
                </c:if>
            </div>

            <c:choose>
                <c:when test="${isLoggedIn}">
                    <div class="navbar-nav ms-auto">
                        <a href="${logoutUrl}" class="nav-item nav-link">
                            <fmt:message key="btn.logout"/>
                        </a>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="navbar-nav ms-auto">
                        <a href="${loginUrl}" class="nav-item nav-link">
                            <fmt:message key="btn.login"/>
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
</body>
</html>
