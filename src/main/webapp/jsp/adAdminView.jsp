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
</head>
<body>
<c:forEach var="mail" items="${requestScope.list}">
  ${mail}<br>
</c:forEach>
<form action="Controller?id=adAdmin">
    <input type="email" name="email" placeholder="email">
    <input type="submit" value="Connexion">
    <input type="hidden" name="id" value="adAdmin">
</form>
<a href="jsp/adminView.jsp" >Retour marche pas</a>

</body>
</html>
