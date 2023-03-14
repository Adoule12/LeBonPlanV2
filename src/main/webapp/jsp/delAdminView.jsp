<%--
  Created by IntelliJ IDEA.
  User: 33625
  Date: 14/03/2023
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Supprimer le grade Admin</title>
</head>
<body>
<c:forEach var="mail" items="${requestScope.list}">
    ${mail}<br>
</c:forEach>

<form action="Controller?id=delAdmin" method="post">
    <input type="email" name="email" placeholder="email">
    <input type="submit" value="Supprimer">
    <input type="hidden" name="id" value="delAdmin">
</form>
<a href="Controller?id=goViews" >Retour</a>


</body>
</html>
