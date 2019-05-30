<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/> <br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Update</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/> <br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<jsp:include page="users.jsp" flush="true" />

<form name="update" action="ShoppingServlet" method="POST">
    <fieldset>
        <table>
            <tr>
                <td>Id:</td><td><input id="user_id" class = "dataField" type="text" readonly name="user_id" value="${user_id}"/><br/></td>
            </tr>
            <tr>
                <td>Login:</td><td><input id="login" class = "dataField" type="text" disabled name="login" value="${login}"/><br/></td>
            </tr>
            <tr>
                <td>Password:</td><td><input class = "dataField" id="password" type="password" name="password" value="${password}"/><br/></td>
            </tr>
            <tr>
                <td>Password confirmation:</td><td><input class = "dataField" id="password_confirmation" type="password" name="password_confirmation" value="${password_confirmation}"/><br/></td>
            </tr>
            <tr>
                <td>Name:</td><td><input class = "dataField" id="name" type="text" name="name" value="${name}"/><br/></td>
            </tr>
            <tr>
                <td>Surname:</td><td><input class = "dataField" id="surname" type="text" name="surname" value="${surname}"/><br/></td>
            </tr>
            <tr>
                <td>user_status:</td>
                <td>
                    BANNED: <input type="checkbox" id="is_blocked" name="is_blocked" ${is_blocked} />
                    <br/>
                </td>
            </tr>
            <tr>
                <td></td><td><input type="hidden" name="action" value="UPDATE_USER_INFO"> <input type="submit" class="myButton" value="Update user info"/></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>