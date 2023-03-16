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
    <link href="css/view.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "frameDelAdmi">
    <p class = "title">Supprimer le grade administrateur</p><br>
<c:forEach var="mail" items="${requestScope.list}">
    ${mail}<br>
</c:forEach>
<form action="Controller?id=delAdmin" method="post">
    <input class = "button" type="email" name="email" placeholder="email">
    <input class = "button" type="submit" value="Supprimer">
</form>
    <input type="hidden" name="id" value="delAdmin">
<a id = "MenuHome" href="Controller?id=goViews" >Retour</a> <br><br>
</div>

</body>
</html>
