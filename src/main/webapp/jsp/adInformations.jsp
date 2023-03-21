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
                <c:set var="ville" value="${ad}" scope="page" />
            </c:if>
            <c:if test="${compteurAd==5}">
                <div class="description">
                <h3 class="title">description:</h3>
                <p class="info">${ad}</p>
                <section class="villestate">
                <p class="ville">ville : ${ville}</p>

            </c:if>
            <c:if test="${compteurAd==6}">
                <c:if test="${ad == 1}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
                    <p  id="notvillestate" class = "state">état : visible</p>
                </c:if>
                <c:if test="${ad == 0}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
                    <p id="hide" class = "state">état : non-visible</p>
                </c:if>
                </section>
            </div>
            </c:if>

            <c:set var="compteurAd" value="${compteurAd+1 }" scope="page" />
        </c:if>
    </c:forEach>

    <c:forEach var="owner" items="${info}">

        <c:if test="${compteur==1}">
            <c:if test="${compteurOw==0}">
            <div class="description villestate">
            <img class="imgP" src="${owner}">
            <section>
            </c:if>
            <c:if test="${compteurOw==1}">
                <p class="info">mail : ${owner}</p></c:if>
            <c:if test="${compteurOw==2}">
                <p class="info">nom : ${owner}</c:if>
            <c:if test="${compteurOw==3}">
                ${owner}</p></c:if>
            <c:if test="${compteurOw==4}">
                <p class="info">numero: ${owner}</p>
                </section>
                </div>
            </c:if>
        <c:set var="compteurOw" value="${compteurOw+1 }" scope="page" />
        </c:if>

    </c:forEach>

        <c:set var="compteur" value="${compteur+1 }" scope="page" />
</c:forEach>

<br><a class="button" href="Controller?id=goViews"> Retour</a>
</div>
</body>
</html>
