<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="left">
 <form name="users_form" action="ShoppingServlet" method="POST">
  <b>Choose user :</b>
  <select name=users.user_selected>
      <c:forEach var="user" items="${userslist}">
          <option>${user.presentation}</option>
      </c:forEach>
  </select>
  <input type="hidden" name="action" value="SELECT_USER">
  <input type="submit" name="Submit" value="Get User Info">
 </form>
</div>

