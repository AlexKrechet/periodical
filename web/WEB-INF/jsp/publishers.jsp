<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="left">
    <table>
        <tr>
            <td>
                <form name="publishers_form" action="ShoppingServlet" method="POST">
                    <b>Choose a publisher :</b>
                    <select name=publishers.publisher_selected>
                        <c:forEach var="publisher" items="${publisherslist}">
                            <option>${publisher.presentation}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="EDIT_PUBLISHER_LINK">
                    <input type="submit" name="Submit" value="Edit Publisher">
                </form>
            </td>
            <td>
                <form name="publisher_add_new" action="ShoppingServlet" method="POST">
                    <input type="hidden" name="action" value="PUBLISHER_CREATE_LINK">
                    <input type="submit" name="Submit" value="Add new Publisher">
                </form>
            </td>
        </tr>
    </table>
</div>
