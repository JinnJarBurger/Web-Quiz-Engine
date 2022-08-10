<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/question" var="questionFormUrl"/>
<html>
<head>
    <title>Question Form</title>
</head>
<body>
<c:set value="${question.new ? 'SAVE' : 'UPDATE'}" var="action"/>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row mx-auto my-auto shadow-lg p-4 w-50 bg-white rounded text-wrap">
            <form:form method="post" modelAttribute="question">
                <div class="row mb-4">
                    <h2 class="text-center fw-normal">
                        <c:choose>
                            <c:when test="${question.new}">
                                <fmt:message key="title.add.question"/>
                            </c:when>

                            <c:otherwise>
                                <fmt:message key="title.update.question"/>
                            </c:otherwise>
                        </c:choose>
                    </h2>
                    <hr class="mt-2 mb-3 w-100"/>
                </div>

                <div class="row mb-3">
                    <form:label path="description" cssClass="ps-0 form-label">
                        <fmt:message key="label.question"/>
                    </form:label>
                    <form:textarea path="description" cssClass="form-control" rows="4"/>
                    <form:errors path="description" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <c:forEach items="${question.options}" varStatus="i">
                        <form:label path="options[${i.index}]" cssClass="ps-0 form-label">
                            <fmt:message key="label.options.${i.index + 1}"/>
                        </form:label>
                        <form:input path="options[${i.index}]" cssClass="form-control"
                                    placeholder="Option ${i.index + 1}"/>
                    </c:forEach>
                    <form:errors path="options" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <form:label path="answer" cssClass="ps-0 form-label">
                        <fmt:message key="label.answer"/>
                    </form:label>
                    <form:input path="answer" cssClass="form-control" placeholder="Answer"/>
                    <form:errors path="answer" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row">
                    <div class="m-0 p-0">
                        <form:button type="submit"
                                     name="action"
                                     value="${action}"
                                     class="btn btn-success">
                            <fmt:message key="btn.${action.toLowerCase()}"/>
                        </form:button>

                        <c:if test="${not question.new}">
                            <fmt:message key="delete.confirmation" var="deleteConfirm"/>
                            <form:button type="submit"
                                         name="action"
                                         value="DELETE"
                                         class="btn btn-danger"
                                         onclick="return confirm('${deleteConfirm}');">
                                <fmt:message key="btn.delete"/>
                            </form:button>
                        </c:if>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
