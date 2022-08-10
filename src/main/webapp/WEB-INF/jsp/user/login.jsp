<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="signupUrl" value="/user/signup"/>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row mx-auto my-auto shadow-lg p-4 w-50 bg-white rounded text-wrap">
            <form:form method="post" modelAttribute="user">
                <div class="row mb-4">
                    <h2 class="text-center fw-normal">
                        <fmt:message key="title.login"/>
                    </h2>
                    <hr class="mt-2 mb-3 w-100"/>
                </div>

                <div class="row mb-3">
                    <form:label path="username" cssClass="ps-0 form-label">
                        <fmt:message key="label.username"/>
                    </form:label>
                    <form:input path="username" cssClass="form-control" placeholder="Username"/>
                </div>

                <div class="row mb-3">
                    <form:label path="password" cssClass="ps-0 form-label">
                        <fmt:message key="label.password"/>
                    </form:label>
                    <form:password path="password" cssClass="form-control" placeholder="Password"/>

                    <form:errors path="username" cssClass="text-danger small ps-0"/>
                    <form:errors path="password" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row">
                    <div class="m-0 p-0">
                        <form:button type="submit"
                                     name="action"
                                     value="LOGIN"
                                     class="btn btn-success">
                            <fmt:message key="btn.login"/>
                        </form:button>
                    </div>
                </div>
            </form:form>

            <div class="row mb-3">
                <span class="p-0">
                    <fmt:message key="label.dont.have.account"/>
                    <a href="${signupUrl}" class="ms-1 text-info text-decoration-none">
                        <fmt:message key="a.signup"/>
                    </a>
                </span>
            </div>
        </div>
    </div>
</div>
</body>
</html>
