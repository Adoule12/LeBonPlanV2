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
    <link href="css/views.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "profilFrame">
<c:set var="compteur" value="0" scope="page" />
<c:set var="compteurAd" value="0" scope="page" />
<c:set var="compteurOw" value="0" scope="page" />
<c:forEach var="info" items="${requestScope.listInfoAd}">
    <c:forEach var="ad" items="${info}">
        <c:if test="${compteur==0}">
            <c:if test="${compteurAd==0}">
                <p class = "title"> ${ad} | |</c:if>
            <c:if test="${compteurAd==1}">
                ${ad} €</p></c:if>
            <c:if test="${compteurAd==2}">
                <img class="image" src="${ad}"></c:if>
            <c:if test="${compteurAd==4}">
                <p class="profil">ville:${ad}</p></c:if>
            <c:if test="${compteurAd==5}">
                <p class="profil">description:</p><p>${ad}</p></c:if>
            <c:if test="${compteurAd==6}">
                <c:if test="${ad == 1}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
                    <p class = "MsgError">état : visible</p>
                </c:if>
                <c:if test="${ad == 0}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
                    <p class = "MsgError">état : non-visible</p>
                </c:if>
            </c:if>
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
</div>
</body>
</html>
