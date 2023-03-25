<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Quiz Form</title>
</head>
<body>
<c:set value="${quiz.new ? 'SAVE' : 'UPDATE'}" var="action"/>
<div class="container">
    <div class="d-flex" style="min-height: 80vh">
        <div class="row mx-auto my-auto shadow-lg p-4 w-50 bg-white rounded text-wrap">
            <form:form method="post" modelAttribute="quiz">
                <div class="row mb-4">
                    <h2 class="text-center fw-normal">
                        <c:choose>
                            <c:when test="${quiz.new}">
                                <fmt:message key="title.create.quiz"/>
                            </c:when>

                            <c:otherwise>
                                <fmt:message key="title.update.quiz"/>
                            </c:otherwise>
                        </c:choose>
                    </h2>
                    <hr class="mt-2 mb-3 w-100"/>
                </div>

                <div class="row mb-3">
                    <form:label path="title" cssClass="ps-0 form-label">
                        <fmt:message key="label.title"/>
                    </form:label>
                    <form:input path="title" cssClass="form-control" placeholder="Title"/>
                    <form:errors path="title" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <form:label path="description" cssClass="ps-0 form-label">
                        <fmt:message key="label.description"/>
                    </form:label>
                    <form:textarea path="description" cssClass="form-control" rows="4"/>
                    <form:errors path="description" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row mb-3">
                    <fmt:message key="text.select.category" var="defaultValue"/>
                    <form:label path="category" cssClass="ps-0 form-label">
                        <fmt:message key="label.category"/>
                    </form:label>
                    <form:select path="category" cssClass="form-control">
                        <form:option value="" label="${defaultValue}" cssClass="form-control"/>
                        <form:options items="${categories}" cssClass="form-control"/>
                    </form:select>
                    <form:errors path="category" cssClass="text-danger small ps-0"/>
                </div>

                <div class="row">
                    <div class="m-0 p-0">
                        <form:button type="submit"
                                     name="action"
                                     value="${action}"
                                     class="btn btn-success">
                            <fmt:message key="btn.${action.toLowerCase()}"/>
                        </form:button>

                        <c:if test="${not quiz.new}">
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
