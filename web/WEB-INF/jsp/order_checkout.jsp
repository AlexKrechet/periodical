<%@ page session="true" import="java.util.*, ua.andrii.project_19.entity.PeriodicalOrder" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/>
<br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Periodicals</h2>
</head>
<body>
<jsp:include page="client_menu.jsp"/>
<br>
<span>${message}</span>
<span class="error">${error}</span>
<br>

<div align="left">
    <fieldset>
        <table border="0"
               cellpadding="0"
               width="100%" bgcolor="#FFFFFF">
            <tr>
                <td><b>Periodical_Code</b></td>
                <td><b>Periodical</b></td>
                <td><b>Publisher</b></td>
                <td><b>Price</b></td>
                <td><b>Quantity</b></td>
                <td></td>
            </tr>
            <c:forEach var="periodical_order" items="${sessionScope.shoppingcart}">
                <tr>
                    <td><b>${periodical_order.periodical.id}</b></td>
                    <td><b>${periodical_order.periodical.name}</b></td>
                    <td><b>${periodical_order.periodical.publisher.name}</b></td>
                    <td><b>${periodical_order.periodical.price}</b></td>
                    <td><b>${periodical_order.periodicalQuantity }</b></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td><b><br><br></b></td>
                <td></td>
            </tr>

            <tr>
                <td></td>
                <td></td>
                <td><b>TOTAL</b></td>
                <td><b>${amount}</b></td>
                <td>
                    <form name="send_order" action="ShoppingServlet" method="POST">
                        <input type="hidden" name="action" value="SEND_ORDER">
                        <input type="submit" name="Submit" value="Send Order">
                    </form>
                </td>

            </tr>
        </table>
    </fieldset>
</div>
</body>
</html>