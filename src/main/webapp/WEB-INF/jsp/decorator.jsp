<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <sitemesh:write property='head'/>
    <title>
        <sitemesh:write property='title'/>
    </title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/assets/images/favicon.ico"/>" />
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/assets/images/favicon.ico"/>" />
</head>
<body>
<jsp:include page="navbar.jsp"/>
<sitemesh:write property='body'/>
</body>
</html>
