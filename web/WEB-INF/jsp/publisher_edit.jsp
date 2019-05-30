<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/>
<br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Publisher edit</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/>
<br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<jsp:include page="publishers.jsp" flush="true"/>
<form name="create_a_publisher" action="ShoppingServlet" method="POST">
    <fieldset>
        <table>
            <tr>
                <td>Code:</td>
                <td><input class="dataField" id="publisher_id" type="text" readonly name="publisher_id"
                           value="${publisher_id}"/><br/></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input class="dataField" id="publisher_name" type="text" name="publisher_name"
                           value="${publisher_name}"/><br/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="hidden" name="action" value="UPDATE_PUBLISHER_INFO"> <input type="submit"
                                                                                             class="myButton"
                                                                                             value="Update a publisher"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>