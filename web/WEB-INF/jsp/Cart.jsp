<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div align="center">
    <table border="0" cellpadding="0" width="100%" bgcolor="#FFFFFF">
        <tr>
            <td><b>PERIODICAL_ID</b></td>
            <td><b>PERIODICAL</b></td>
            <td><b>PUBLISHER</b></td>
            <td><b>PRICE</b></td>
            <td><b>QUANTITY</b></td>
            <td></td>
        </tr>
        <c:forEach var="anOrder" items="${sessionScope.shoppingcart}" varStatus="loop">
            <tr>
                <td><b>${anOrder.periodical.id}</b></td>
                <td><b>${anOrder.periodical.name}</b></td>
                <td><b>${anOrder.periodical.publisher.name}</b></td>
                <td><b>${anOrder.periodical.price }</b></td>
                <td><b>${anOrder.periodicalQuantity }</b></td>
                <td>
                    <form name="deleteForm"
                          action="ShoppingServlet"
                          method="POST">
                        <input type="submit" value="Delete">
                        <input type="hidden" name="delindex" value="${loop.index}">
                        <input type="hidden" name="action" value="DELETE">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
    <form name="checkoutForm"
          action="ShoppingServlet"
          method="POST">
        <input type="hidden" name="action" value="CHECKOUT">
        <input type="submit" name="Checkout" value="Checkout">
    </form>
</div>