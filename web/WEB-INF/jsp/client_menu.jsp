<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:setLocale value="${sessionScope.locale}" scope="session"/>
<c:bundle basename="resource.l10n">
    <ul id="main-menu">
        <li> &nbsp;</li>
        <li>
            <form name="user_info" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                 value="CLIENT_INFO"><input
                    class="myButtonMenu" type="submit" value="<c:message key="client_menu.userInfo"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="create order" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                    value="ORDER_CREATE_MENU_CLIENT">
                <input class="myButtonMenu" type="submit" value="<c:message key="client_menu.OrderCreate"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="logout" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                              value="LOGOUT"> <input
                    class="myButtonMenu" type="submit" value="<c:message key="client_menu.Logout"/>"></form>
        </li>
        <li> &nbsp;</li>
    </ul>
</c:bundle>

