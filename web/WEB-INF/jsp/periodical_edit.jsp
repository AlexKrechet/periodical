<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/>
<br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Periodical edit</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/>
<br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<jsp:include page="periodicals.jsp" flush="true"/>
<form name="edit_a_periodical" action="ShoppingServlet" method="POST">
    <fieldset>
        <table>
            <tr>
                <td>Code:</td>
                <td><input class="dataField" id="periodical_id" type="text" readonly name="periodical_id"
                           value="${periodical_id}"/><br/></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input class="dataField" id="periodical_name" type="text" name="periodical_name"
                           value="${periodical_name}"/><br/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input class="dataField" id="periodical_price" input type="number" min="0.01" max="1000000"
                           step="0.01"
                           name="periodical_price" value="${periodical_price}"/><br/></td>
            </tr>
            <tr>
                <td>Publisher:</td>
                <td>
                    <form name="publishers_form" action="ShoppingServlet" method="POST">
                        <select name=periodicals.publisher_selected>
                            <c:forEach var="publisher" items="${periodical_publisherslist}">
                                <option>${publisher.presentation}</option>
                            </c:forEach>
                        </select>
                    </form>
                    <br/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="hidden" name="action" value="UPDATE_PERIODICAL_INFO"> <input type="submit"
                                                                                              class="myButton"
                                                                                              value="Update a periodical"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>