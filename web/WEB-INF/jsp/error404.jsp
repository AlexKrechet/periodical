<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="resource.i18n">
    <html>
    <body>
    <div class="main">
        <fmt:message key="error404.message.start"/> <i><b>"${resource_uri}"</b></i> <fmt:message
            key="error404.message.end"/>
    </div>
    </body>
    </html>
</fmt:bundle>