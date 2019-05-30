<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="left">
    <table>
        <tr>
            <td>
                <form name="periodicals_form" action="ShoppingServlet" method="POST">
                    <b>Choose a periodical :</b>
                    <select name=periodicals.periodical_selected>
                        <c:forEach var="periodical" items="${periodicalslist}">
                            <option>${periodical.presentation}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="EDIT_PERIODICAL_LINK">
                    <input type="submit" name="Submit" value="Edit Periodical">
                </form>
            </td>
            <td>
                <form name="periodical_add_new" action="ShoppingServlet" method="POST">
                    <input type="hidden" name="action" value="PERIODICAL_CREATE_LINK">
                    <input type="submit" name="Submit" value="Add new Periodical">
                </form>
            </td>
        </tr>
    </table>
</div>

