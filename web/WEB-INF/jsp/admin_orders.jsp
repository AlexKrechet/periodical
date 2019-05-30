<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/>
<br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Orders</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/>
<br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<fieldset>
    <table width="100%">
        <tr>
            <td><b>ORDER</b></td>
            <td align="center"><b>PAID</b></td>
            <td><b></b></td>
            <td align="center"><b>DELETE</b></td>
        </tr>
        <c:forEach var="order" items="${sessionScope.orderslist}" varStatus="loop">
            <tr>
                <td valign="center">${order.presentation}</td>
                <td align="center">
                    <form name="deleteOrder" action="ShoppingServlet" method="POST">
                        <input type="submit" value="Paid">
                        <input type="hidden" name="paidindex" value="${loop.index}">
                        <input type="hidden" name="action" value="PAY_ORDER_ADMIN">
                    </form>
                </td>
                <td> &nbsp;</td>
                <td align="center">
                    <form name="payOrder" action="ShoppingServlet" method="POST">
                        <input type="submit" value="Delete">
                        <input type="hidden" name="delindex" value="${loop.index}">
                        <input type="hidden" name="action" value="DELETE_ORDER_ADMIN">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</fieldset>
</body>
</html>