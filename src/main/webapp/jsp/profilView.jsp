
<%--
  Created by axoul.
  User: 33625
  Date: 08/03/2023
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Votre Profil</title>
    <link href="css/views2.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "profilFrame">
    <p class="title">Votre Profil</p> <br>
    <c:set var="compteur" value="0" scope="page" />
    <c:forEach var="info" items="${requestScope.info}">
        <c:if test="${compteur==0}">
            <div class="description villestate">
            <img class="img" src="img/profil.jpg">
            <section>
            <p class="info">Adresse mail: ${info} </p>
        </c:if>
        <c:if test="${compteur==1}">
            <p class="info">Nom : ${info}
        </c:if>
        <c:if test="${compteur==2}">
             ${info} </p>
        </c:if>
        <c:if test="${compteur==3}">
            <p class="info">Numéro de téléphone : ${info} </p>
            <p class="info"><a href="Controller?id=editUser">Modifier son compte</a></p>
            </section>
            </div>
        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
    </c:forEach>
    <br>
    <a class = "button" href="Controller?id=goViews" >Retour</a>
    <a class = "button" href="Controller?id=logout">Déconnexion</a>
    <a class="button" href="Controller?id=deleteUser">Supprimer son compte</a>
     <p class="title"> Mes Annonces</p>
    <c:set var="compteurAd" value="0" scope="page" />

    <c:forEach var="ad" items="${requestScope.listAd}">
        <c:if test="${compteurAd mod 2==0}">
            <section class="ligne">
        </c:if>
        <div class = "annonce carre">
        <c:set var="compteur" value="0" scope="page" />
        <c:forEach var="info" items="${ad}">
            <c:if test="${compteur==0}"><br>
                <p class = "title"> ${info} | |
            </c:if>

            <c:if test="${compteur == 2}">
               ${info} €</p> <br>
            </c:if>
            <c:if test="${compteur==3}">
                <img class="image" src="${info}">
            </c:if>
            <c:if test="${compteur == 1}">
                <c:set var="idAD" value="${info}" scope="page" />
            </c:if>
            <c:if test="${compteur == 4}">
            <c:if test="${info == 1}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
                <p class = "MsgError">état : visible</p>
            </c:if>
            <c:if test="${info == 0}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
                <p class = "MsgError">état : non-visible</p>
            </c:if>
            </c:if>
            <c:set var="compteur" value="${compteur+1 }" scope="page" />
        </c:forEach>
            <form action="Controller?id=editAd" method="post">
                    <input type="submit" name="infosButton" value="modifier mon annonce">
                    <input type="hidden" name="idAD" value="${idAD}">
                    <input type="hidden" name="id" value="editAd">
            </form>
        </div>
            <c:if test="${compteurAd mod 2==1}">
                </section>
            </c:if>
        <c:set var="compteurAd" value="${compteurAd+1 }" scope="page" />

    </c:forEach>
    </section>
    <br><br>
    <a class = "button" href="Controller?id=goViews" >retour</a>

</div>
</body>
</html>
