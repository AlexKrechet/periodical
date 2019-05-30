<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="resource.i18n">
    <html>
    <body>
    <div class="main">
        <fmt:message key="exception.message"/>
    </div>
    </body>
    </html>
</fmt:bundle>