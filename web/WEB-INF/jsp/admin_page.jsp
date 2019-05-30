<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:setLocale value="${sessionScope.locale}" scope="session"/>
<c:bundle basename="resource.l10n">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <html>
    <jsp:include page="languages.jsp"/>
    <br>

    <head>
        <h2> &nbsp;&nbsp; Info</h2>
    </head>
    <body>

    <jsp:include page="admin_menu.jsp"/>
    <br>

    <span>${message}</span>
    <span class="error">${error}</span>

    <table>
        <tr>
            <td><b>Login: </b></td>
            <td>${user.login}<br/></td>
        </tr>
        <tr>
            <td><b>Name: </b></td>
            <td>${user.name}<br/></td>
        </tr>
        <tr>
            <td><b>Surname: </b></td>
            <td>${user.surname}<br/></td>
        </tr>
        <tr>
            <td><b>Ban status: </b></td>
            <td>${user.isBlocked ?  "Access is banned" : "Access is allowed"}<br/></td>
        </tr>
        <tr>
            <form name="change_password" action="ShoppingServlet" method="post">
                <td><input type="hidden" name="action" value="UPDATE_USER_PASSWORD"> <input type="submit"
                                                                                            class="myButton"
                                                                                            value="Change password"/>
                </td>
            </form>
        </tr>
    </table>
    </body>
    </html>
</c:bundle>
