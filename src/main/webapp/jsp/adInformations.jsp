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
    <title>Informations d'annonce</title>
</head>
<body>
<p>lol</p>
<c:set var="compteur" value="0" scope="page" />
<c:set var="compteurAd" value="0" scope="page" />
<c:set var="compteurOw" value="0" scope="page" />
<c:forEach var="info" items="${requestScope.listInfoAd}">
    <c:forEach var="ad" items="${info}">
        <c:if test="${compteur==0}">
            <c:if test="${compteurAd==0}">
                <p>titre:${ad}</p></c:if>
            <c:if test="${compteurAd==1}">
                <p>prix:${ad}</p></c:if>
            <c:if test="${compteurAd==2}">
                <img class="image" src="${ad}"></c:if>
            <c:if test="${compteurAd==4}">
                <p>ville:${ad}</p></c:if>
            <c:if test="${compteurAd==5}">
                <p>description:${ad}</p></c:if>
            <c:if test="${compteurAd==6}">
                <p>state:${ad}</p></c:if><br>

            <c:set var="compteurAd" value="${compteurAd+1 }" scope="page" />
        </c:if>
    </c:forEach>

    <c:forEach var="owner" items="${info}">

        <c:if test="${compteur==1}">
            <c:if test="${compteurOw==0}">
                <h1>Vendeur</h1>
                <p>mail : ${owner}</p></c:if>
            <c:if test="${compteurOw==1}">
                <p>nom : ${owner}</c:if>
            <c:if test="${compteurOw==2}">
                prenom: ${owner}</p></c:if>
            <c:if test="${compteurOw==3}">
                <p>numero: ${owner}</p></c:if>

        <c:set var="compteurOw" value="${compteurOw+1 }" scope="page" />
        </c:if>
    </c:forEach>

        <c:set var="compteur" value="${compteur+1 }" scope="page" />
</c:forEach>

<a href="Controller?id=goViews"> Retour</a>

</body>
</html>
