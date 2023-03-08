<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 27/02/2023
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="gradeAdmin" value="admin" scope="page" />
<p>CONNECTER CA a circuleé</p>
<c:choose>
    <c:when test="${content == gradeAdmin}">
        Vous êtes Admin !
    </c:when>
    <c:otherwise>
        Vous êtes clients !
    </c:otherwise>
</c:choose>
<p>${content}</p>


</body>
</html>
