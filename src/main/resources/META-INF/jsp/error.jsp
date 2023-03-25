<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<fmt:message key="error.something.wrong" var="defaultError"/>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row mx-auto my-auto p-4">
            <div class="m-auto">
                <h1 class="text-center">
                    <c:out value="${status}"/>
                </h1>
            </div>
            <div class="m-auto">
                <h2 class="text-center text-danger">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:">
                        <use xlink:href="#exclamation-triangle-fill"/>
                    </svg>
                    <c:out value="${exception.message}" default="${defaultError}" />
                </h2>
            </div>
        </div>
    </div>
</div>
</body>
</html>
