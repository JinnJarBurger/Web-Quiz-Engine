<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row mx-auto my-auto shadow-lg p-4 w-50 bg-white rounded text-wrap">
            <form:form method="post" modelAttribute="user">
                <div class="row mb-4">
                    <h2 class="text-center fw-normal">
                        <fmt:message key="title.signup"/>
                    </h2>
                    <hr class="mt-2 mb-3 w-100"/>
                </div>

                <div class="row mb-3">
                    <form:label path="username" cssClass="ps-0 form-label">
                        <fmt:message key="label.username"/>
                    </form:label>
                    <form:input path="username" cssClass="form-control" placeholder="Username"/>
                    <form:errors path="username" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <form:label path="email" cssClass="ps-0 form-label">
                        <fmt:message key="label.email"/>
                    </form:label>
                    <form:input type="email" path="email" cssClass="form-control" placeholder="Email"/>
                    <form:errors path="email" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <form:label path="dateOfBirth" cssClass="ps-0 form-label">
                        <fmt:message key="label.date.of.birth"/>
                    </form:label>
                    <form:input type="date" path="dateOfBirth" cssClass="form-control"/>
                    <form:errors path="dateOfBirth" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <form:label path="password" cssClass="ps-0 form-label">
                        <fmt:message key="label.password"/>
                    </form:label>
                    <form:password path="password" cssClass="form-control" placeholder="Password"/>
                    <form:errors path="password" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <fmt:message key="text.select.role" var="defaultValue"/>
                    <form:label path="role" cssClass="ps-0 form-label">
                        <fmt:message key="label.role"/>
                    </form:label>
                    <form:select path="role" cssClass="form-control">
                        <form:option value="" label="${defaultValue}" cssClass="form-control"/>
                        <form:options items="${roles}" cssClass="form-control"/>
                    </form:select>
                    <form:errors path="role" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row">
                    <div class="m-0 p-0">
                        <form:button type="submit"
                                     name="action"
                                     value="SIGNUP"
                                     class="btn btn-primary">
                            <fmt:message key="btn.signup"/>
                        </form:button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
