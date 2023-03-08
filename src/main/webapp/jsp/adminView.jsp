<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 28/02/2023
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link href="css/test.css" type="text/css" rel="stylesheet"/>
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
                            <input type="submit" name="test" value="Reset">
                            <input type="hidden" name="id" value="showAd">
                        </form>
                    </ul>
                </li>
            </ul>
        </li>
        <li class="menu"><a>Annonce</a>
            <ul class="sousMenu">
                <li><a href="Controller?id=postAd">Déposer une annonc</a></li>
                <li><a href="#">Modifier une annonce</a></li>
            </ul>
        </li>
        <li class="menu"><a>Mon Compte</a>
            <ul class="sousMenu">
                <li><a href="#">Profil</a></li>
                <li><a href="Controller?id=editUser">Modifier mon profil</a></li>
            </ul>
        </li>
    </ul>
</nav>

<c:forEach var="ad" items="${requestScope.listAd}">
    <c:forEach var="info" items="${ad}">
        ${info}<br>
    </c:forEach>
</c:forEach>

<a href="Controller?id=logout">quitter le jeu</a>
<a href="Controller?id=deleteUser">rage quitte</a>

<p>Vous êtes Admin !</p>
<a href="Controller?id=adAdmin" >Donner droit admin</a>
<a href="Controller?id=goHome" >Menu Home</a>

</body>
</html>
