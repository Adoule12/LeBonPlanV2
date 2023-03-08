<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 27/02/2023
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion au compte</title>
    <link href="css/homeer.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "LoginFrame">
<h2 class ="title">LeBonPlan</h2>
<form action="Controller?id=login">
    <input class = "button ButtonLogin" type="email" name="email" placeholder="email"><br><br>
    <input class = "button ButtonLogin" type="password" name="password" placeholder="password"><br><br>
    <input class = "button ButtonLogin" type="submit" value="Connexion"><br><br>
    <input class = "button" type="hidden" name="id" value="login"><br>
</form>
    <p>${info}</p>
<a  id = "inscriptionLogin" href="Controller?id=signin" >Inscription</a>
</div>
</body>
</html>
