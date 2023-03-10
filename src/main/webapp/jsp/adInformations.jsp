<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 07/03/2023
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Informarions de l'annonce</title>
</head>
<body>
<p>lol</p>
<c:set var="compteur" value="0" scope="page" />
<c:forEach var="info" items="${requestScope.listInfoAd}">
        <c:if test="${compteur<2}">
            ${info}<br>
        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
</c:forEach>

<form action="Controller?id=login">
    <input type="submit" name="infosButton" value="Retour">
    <input type="hidden" name="id" value="goViews">
    </form>

</body>
</html>
