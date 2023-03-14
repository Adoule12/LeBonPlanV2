
<%--
  Created by IntelliJ IDEA.
  User: axoul
  Date: 11/03/2023
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modération</title>
    <link href="css/commune.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<form action="Controller?id=moderationUser" method="post">
<h3>utilisateurs existants</h3>
<c:forEach var="user" items="${requestScope.listuser}">
    <c:set var="compteur" value="0" scope="page" />
    <c:forEach var="info" items="${user}">
        <c:if test="${compteur<1}"><br>
            ${info}
        </c:if>
        <c:if test="${compteur == 1}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
            <c:set var="idUser" value="${info}" scope="page" />

        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
    </c:forEach>

    <input type="submit" name="bannir" value="${idUser}" >
</c:forEach>
    <input type="hidden" name="id" value="moderationUser">
</form>


<form action="Controller?id=moderationUser" method="post">
<h3>utilisateurs banis</h3>
<c:forEach var="user" items="${requestScope.listblacklisted}">
    <c:set var="compteur" value="0" scope="page" />
    <c:forEach var="info" items="${user}">
        <c:if test="${compteur<1}"><br>
            ${info}
        </c:if>
        <c:if test="${compteur == 1}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
            <c:set var="idUser" value="${info}" scope="page" />

        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
    </c:forEach>

    <input type="submit" name="debannir" value="${idUser}">
    <input class="delete" type="submit" name="deleteUser" value="${idUser}" >

</c:forEach>
    <input type="hidden" name="id" value="moderationUser">
</form>

</body>
</html>
