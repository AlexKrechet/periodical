<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:setLocale value="${sessionScope.locale}" scope="session"/>
<c:bundle basename="resource.l10n">
    <ul id="main-menu">
        <li> &nbsp;</li>
        <li>
            <form name="user info" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                 value="USER_INFO"><input
                    class="myButtonMenu" type="submit" value="<c:message key="admin_menu.userInfo"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="user registration" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                         value="CLIENT_REGISTRATION">
                <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.createUser"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                   value="UPDATE_USER"> <input
                    class="myButtonMenu" type="submit" value="<c:message key="admin_menu.updateUser"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                   value="PUBLISHER_CREATE_MENU"> <input
                    class="myButtonMenu" type="submit" value="<c:message key="admin_menu.Publisher"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                   value="PERIODICAL_CREATE_MENU">
                <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.Periodical"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                                   value="ORDER_CREATE_MENU_ADMIN">
                <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.Order"/>"></form>
        </li>
        <li> &nbsp;</li>
        <li>
            <form name="logout" action="ShoppingServlet" method="post"><input type="hidden" name="action"
                                                                              value="LOGOUT"> <input
                    class="myButtonMenu" type="submit" value="<c:message key="admin_menu.Logout"/>"></form>
        </li>
        <li> &nbsp;</li>
    </ul>
</c:bundle>

