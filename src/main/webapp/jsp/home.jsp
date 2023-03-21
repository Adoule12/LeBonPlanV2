<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 27/02/2023
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
    <link href="css/views.css" type="text/css" rel="stylesheet"/>
    <script src="js/view.js"></script>
</head>
<body onload="count()">
<div class="logoFrame">
    <img class="logoHOME" id = "logoLBP" src="img/logo_LBP_remove_background.png" onmouseenter="rotate()" onmouseleave="rotateback()">
    <p >Annonces disponible: <p id="nbAd">${requestScope.nbAd}</p></p>
</div>

<div class = "frame" id = "HomeFrame">
    <h2 class = "title">LeBonPlan</h2>
    <section><a class = "button" id = "connexion" href="Controller?id=login" >Connexion</a></section> <br>
    <section><a class = "button" id = "inscription" href="Controller?id=signin">Inscription</a></section> <br>
    <p class = "MsgError">${content}</p>
</div>
</body>
</html>
