<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%-- <c:choose>
	    <c:when test="userIsLoggedIn">
	        normal header, body
	    </c:when>
	    <c:otherwise>
	        login form
	    </c:otherwise>
	</c:choose>--%>
        <form name="forma" method="post" action="<%=request.getContextPath()%>/Auth">
            Username:<br />
            <input type="text" name="uname" /><br />
            Password:<br />
            <input type="text" name="passwd" /><br />
            <input type="submit" name="prijava" value="Prijava" />
        </form>
    </body>
</html>