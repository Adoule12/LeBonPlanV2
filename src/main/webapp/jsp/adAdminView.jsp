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
    <title>grade Admin</title>
    <link href="css/views.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "frameAdAdmin">
<p class = "title">Donner le grade d'Administrateur</p><br>
    <div id = "ListMail">
        liste des adresses mails :<br><br>
    <c:forEach var="mail" items="${requestScope.list}">

         ${mail}<br>
    </c:forEach>
        <br>
    </div>
<form action="Controller?id=adAdmin" method="post">
    <input class = "button" type="email" name="email" placeholder="email" maxlength="320" required>
    <input class = "button" type="submit" value=" Ajouter ">
    <input type="hidden" name="id" value="adAdmin">
</form>
<a id = "MenuHome" href="Controller?id=goViews" >Retour</a> <br><br>
</div>
</body>
</html>
