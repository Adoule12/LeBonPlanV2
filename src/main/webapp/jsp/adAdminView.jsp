<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 28/02/2023
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Donner le grade Admin</title>
    <link href="css/views.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "frameAdAdmin">
<p class = "title">Donner le grade Administrateur</p><br>
    <div id = "ListMail">
    <c:forEach var="mail" items="${requestScope.list}">
        liste des addresses mails :<br>
         ${mail}<br><br>
    </c:forEach>
    </div>
<form action="Controller?id=adAdmin" method="post">
    <input class = "button" type="email" name="email" placeholder="email">
    <input class = "button" type="submit" value="Ajouter">
    <input type="hidden" name="id" value="adAdmin">
</form>
<a id = "MenuHome" href="Controller?id=goViews" >Retour</a> <br><br>
</div>
</body>
</html>
