<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 28/02/2023
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin</title>
    <link href="css/ClientView.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<nav>
    <ul>
        <li>
            <a href="#">
                <img class="imgFloat"  src="img/drapeau-ville-angers-logo-flagsonline.jpg">
            </a>
        </li>
        <li class="menu"><a href="#">Filtre</a>
            <ul class="sousMenu">
                <li><a href="#">categorie</a>
                    <ul class="sousFiltre">
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Véhicule">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="1">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Mobilier">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="2">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Jardin">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="3">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Electronique">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="4">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#">état</a>
                    <ul class="sousFiltre">
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Neuf">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="1">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Quasi-neuf">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="2">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Un peu abimé">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="3">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input type="submit" name="test" value="Très abimé">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="4">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#">prix maximum</a>
                    <ul class="sousFiltre">
                        <form action="Controller?id=login">
                            <input type="number" name="price" placeholder="Prix max">
                            <input type="submit" name="test" value="OK">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                    </ul>
                </li>
                <li><a href="#">Reset filtres</a>
                    <ul class="sousFiltre">
                        <form action="Controller?id=login">
                            <input type="submit" name="resetButton" value="Reset">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                    </ul>
                </li>
            </ul>
        </li>
        <li class="menu"><a>Modération</a>
            <ul class="sousMenu">
                <li><a href="Controller?id=postAd">Déposer une annonce</a></li>
                <li><a href="Controller?id=moderationAD">Modération des annonces</a></li>
                <li><a href="Controller?id=adCompte" >Créer un compte</a></li>
                <li><a href="Controller?id=moderationUser">Modération des Comptes</a></li>
                <li><a href="Controller?id=adAdmin" >Donner les droits admin</a></li>
                <li><a href="Controller?id=delAdmin" >Supprimer les droits d'admin</a></li>
            </ul>
        </li>
        <li class="menu"><a>Mon Compte</a>
            <ul class="sousMenu">
                <li><a href="Controller?id=goProfil">Profil</a></li>
                <li><a href="Controller?id=editUser">Modifier mon profil</a></li>
            </ul>
        </li>
    </ul>
</nav>

<c:forEach var="ad" items="${requestScope.listAd}">
    <c:set var="compteur" value="0" scope="page" />
    <c:forEach var="info" items="${ad}">
        <c:if test="${compteur==0}"><br>
            <p class = "title"> ${info} </p><br>
        </c:if>
        <c:if test="${compteur == 1}">
            <p class = "price">${info} €</p> <br>
        </c:if>
        <c:if test="${compteur==2}">
            <img src="${info}"><br>
        </c:if>
        <c:if test="${compteur == 3}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
            <c:set var="idAD" value="${info}" scope="page" />
        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
    </c:forEach>
    <form action="Controller?id=showDetailsAD">
        <input type="submit" name="infosButton" value="Informations annonce">
        <input type="hidden" name="idAD" value="${idAD}">
        <input type="hidden" name="id" value="showDetailsAD">
    </form>
</c:forEach>



<p>Vous êtes Admin !</p>

<a href="Controller?id=goHome" >Menu Home</a>

</body>
</html>
