<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="successImageUrl" value="/META-INF/assets/images/verifySuccessTick.png"/>
<html>
<head>
    <title>Success</title>
</head>
<body>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row mx-auto my-auto p-4">
            <img src="${successImageUrl}" alt="success" class="w-25 h-25 m-auto"/>
            <h3 class="text-center text-success">
                <fmt:message key="text.success"/>
            </h3>
        </div>
    </div>
</div>
</body>
</html>
