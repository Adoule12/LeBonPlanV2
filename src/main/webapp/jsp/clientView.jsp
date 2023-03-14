<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 28/02/2023
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Client</title>
    <link href="css/ClientView.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<nav class = "all">
    <ul>
        <li class="menu"><a href="#">Filtre</a>
            <ul class="sousMenu">
                <li><a class = "title" href="#">Catégorie</a>
                    <ul class="sousFiltre">
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "vehicule" type="submit" name="test" value="Véhicule"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="1">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "mobilier" type="submit" name="test" value=" Mobilier"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="2">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "jardin" type="submit" name="test" value="Jardin"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="3">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "electronique" type="submit" name="test" value="Électronique"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="4">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "autre" type="submit" name="test" value="Autre"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="categorie" value="5">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a class = "title" href="#">État</a>
                    <ul class="sousFiltre">
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id ="neuf" type="submit" name="test" value="Neuf"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="1">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "quasi" type="submit" name="test" value="Quasi-neuf"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="2">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "UnPeuAbimé" type="submit" name="test" value="Un peu abimé"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="3">
                            </form>
                        </li>
                        <li>
                            <form action="Controller?id=login">
                                <input class = "button" id = "trèsAbimé" type="submit" name="test" value="Très abimé"><br>
                                <input type="hidden" name="id" value="showAd">
                                <input type="hidden" name="conditions" value="4">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a class = "title" href="#">Prix maximum</a>
                    <ul class="sousFiltre">
                        <form action="Controller?id=login">
                            <input class = "button" id = "prixMax" type="number" name="price" placeholder="Prix maximum"><br>
                            <input class = "button" id = "ok" type="submit" name="test" value="Appliqué">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                    </ul>
                </li>
                <li><a class = "title" href="#">Rafraichir filtres</a>
                    <ul class="sousFiltre">
                        <form action="Controller?id=login">
                            <input class = "button" id = "reset" type="submit" name="resetButton" value="Rafraichir"><br>
                            <input type="hidden" name="id" value="showAd">
                        </form>
                        <form action="Controller?id=login">
                            <input class = "button" id = "croissant" type="submit" name="resetButton" value="Tri Croissant"><br>
                            <input type="hidden" name="tris" value="croissant">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                        <form action="Controller?id=login">
                            <input class = "button" type="submit" id = "decroissant" name="resetButton" value="Tri Décroissant"><br>
                            <input type="hidden" name="tris" value="des_croissant">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                    </ul>
                </li>
            </ul>
        </li>

        <li class="menu"><a>Annonce</a>
            <ul class="sousMenu">
                <li><a href="Controller?id=postAd">Déposer une annonce</a></li>
                <li><a href="Controller?id=editAd">Modifier une annonce</a></li>
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
            <img src="${info}"><br>
        </c:if>
        <c:if test="${compteur == 3}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
            <c:set var="idAD" value="${info}" scope="page" />
        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
    </c:forEach>
    <form action="Controller?id=showDetailsAD">

        <input class = "buttonAnnouncement" type = "image" src="./img/question-mark.png" value="${idAD}"> <br><br>
        <input type="hidden" name="idAD" value="${idAD}">
        <input type="hidden" name="id" value="showDetailsAD">
    </form>
    </div>
</c:forEach>


<footer>

</footer>

</body>
</html>
