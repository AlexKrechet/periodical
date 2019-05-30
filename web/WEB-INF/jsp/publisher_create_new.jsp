<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/> <br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Publisher Create</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/> <br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<jsp:include page="publishers.jsp" flush="true" />
<form name="create_a_publisher" action="ShoppingServlet" method="POST">
    <fieldset>
        <table>
            <tr>
                <td>Name:</td><td><input class = "dataField" id="name" type="text" name="name" value=""/><br/></td>
            </tr>
            <tr>
                <td></td><td><input type="hidden" name="action" value="ADD_NEW_PUBLISHER_BY_ADMIN"> <input type="submit" class="myButton" value="Create a publisher"/></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>