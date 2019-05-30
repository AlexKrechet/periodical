<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:setLocale value="${sessionScope.locale}"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
<html>
<body>
<span>${message}</span>
<span class="error">${error}</span>
<fieldset>
    <form name="new_user_password" action="ShoppingServlet" method="post">
        <table>
            <tr>
                <td><b>Login: </b></td>
                <td>${user.login}<br/></td>
            </tr>
            <tr>
                <td>Old password:</td>
                <td><input id="old_password" class="dataField" type="password" name="old_password"
                           value="${old_password}"/><br/></td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input class="dataField" id="new_password" type="password" name="new_password"
                           value="${new_password}"/><br/></td>
            </tr>
            <tr>
                <td>Confirm password:</td>
                <td><input class="dataField" id="password_confirmation" type="password" name="password_confirmation"
                           value="${password_confirmation}"/><br/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="hidden" name="action" value="NEW_USER_PASSWORD"> <input type="submit" class="myButton"
                                                                                         value="Change password"/></td>
            </tr>
        </table>
    </form>
</fieldset>
</body>
</html>