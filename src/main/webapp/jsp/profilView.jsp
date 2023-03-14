<%--
  Created by IntelliJ IDEA.
  User: 33625
  Date: 08/03/2023
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Votre Profil</title>
</head>
<body>
<p>Votre Profil :</p>
<c:forEach var="info" items="${requestScope.info}">
    ${info}
</c:forEach>
<a href="Controller?id=logout">Déconnexion</a>
<a href="Controller?id=deleteUser">Supprimer son compte</a>
</body>
</html>
