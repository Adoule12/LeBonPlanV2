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
    <link href="css/camenerve.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<nav class = "all">
    <ul>
        <li class="menu"><a href="#">Filtre</a>
            <ul class="sousMenu">
                <li><a class = "title" href="#">Catégorie</a>
                    <ul class="sousFiltre">
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "vehicule" type="submit" name="test" value="Véhicule">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="1">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "mobilier" type="submit" name="test" value="Mobilier">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="2">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "jardin" type="submit" name="test" value="Jardin">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="3">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "electronique" type="submit" name="test" value="Électronique">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="4">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "autre" type="submit" name="test" value="Autre"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="5">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a class = "title" href="#">Etat</a>
                    <ul class="sousFiltre">
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id ="neuf" type="submit" name="test" value="Neuf">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="1">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "quasi" id = "quasi" type="submit" name="test" value="Quasi-neuf">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="2">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "UnPeuAbimé"  type="submit" name="test" value="Un peu abimé">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="3">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=showAd" method="post">
                                <input class = "button" id = "trèsAbimé" type="submit" name="test" value="Très abimé">
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="4">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a class = "title" href="#">Prix maximum</a>
                    <ul class="sousFiltre">
                        <form action="Controller?id=showAd" method="post">
                            <input class = "button" id = "prixMax" type="number" name="price" placeholder="Prix max"><br>
                            <input class = "button" id = "ok" type="submit" name="test" value="OK">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                    </ul>
                </li>
                <li><a class = "title" href=#">Reset filtres</a>
                    <ul class="sousFiltre">
                        <form action="Controller?id=showAd" method="post">
                            <input class = "button" id = "reset" type="submit" name="resetButton" value="Reset">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                        <form action="Controller?id=showAd" method="post">
                            <input class = "button" id = "croissant" type="submit" name="resetButton" value="Tri Croissant"><br>
                            <input type="hidden" name="tris" value="croissant">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                        <form action="Controller?id=showAd" method="post">
                            <input class = "button" type="submit" id = "decroissant" name="resetButton" value="Tri Décroissant"><br>
                            <input type="hidden" name="tris" value="des_croissant">
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
                <li><a href="Controller?id=moderationUser">Modération des comptes</a></li>
                <li><a href="Controller?id=adAdmin" >Donner les droits administrateur</a></li>
                <li><a href="Controller?id=delAdmin" >Supprimer les droits administrateurs</a></li>
            </ul>
        </li>
        <li class="menu">
            <a>Mon Compte</a>
            <ul class="sousMenu">
                <li><a href="Controller?id=goProfil">Profil</a></li>
                <li><a href="Controller?id=editUser">Modifier mon profil</a></li>
            </ul>
        </li>
    </ul>
</nav>
<p class = "title" id = "txtAdmi" >Vous êtes administrateur !</p>
<c:forEach var="ad" items="${requestScope.listAd}">
    <div class = "annonce">
    <c:set var="compteur" value="0" scope="page" />
    <c:forEach var="info" items="${ad}">
        <c:if test="${compteur==0}"><br>
            <p class = "title"> ${info} </p><br>
        </c:if>
        <c:if test="${compteur == 1}">
            <p class = "price">${info} €</p> <br>
        </c:if>
        <c:if test="${compteur==2}">
            <img class="image" src="${info}"><br>
        </c:if>
        <c:if test="${compteur == 3}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
            <c:set var="idAD" value="${info}" scope="page" />
        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
    </c:forEach>
    <form action="Controller?id=showDetailsAD" method="post">
        <input class = "buttonAnnouncement" type = "image" src="./img/question-mark.png" value="${idAD}"> <br><br>
        <input type="hidden" name="idAD" value="${idAD}">
        <input type="hidden" name="id" value="showDetailsAD">
    </form>
    </div>
    <p>oui</p>
</c:forEach>
</body>
</html>
