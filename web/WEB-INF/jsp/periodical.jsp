<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/>
<br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Periodicals</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/>
<br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<jsp:include page="periodicals.jsp" flush="true"/>

<form name="periodicals_list" action="ShoppingServlet" method="POST">
    <fieldset>
        <table width="100%">
            <thead>
            <tr>
                <td><b>
                    <PERIODICALS/>
                </b></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="periodical" items="${periodicalslist}">
                <tr>
                    <td valign="center">${periodical.presentation}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</form>
</body>
</html>