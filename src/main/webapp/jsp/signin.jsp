<%--
  Created by IntelliJ IDEA.
  User: Tanguy Suteau
  Date: 27/02/2023
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription</title>
    <link href="css/commune.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "SigninFrame">
<h2 class = "title">LeBonPlan</h2>
<form action="Controller?id=signin" method="post">
    <input class = "button ButtonSignin" type="email" name="email" placeholder="email"><br><br>
    <input class = "button ButtonSignin" type="password" name="password" placeholder="password"><br><br>
    <input class = "button ButtonSignin" type="text" name="lastname" placeholder="lastname"><br><br>
    <input class = "button ButtonSignin" type="text" name="firstname" placeholder="firstname"><br><br>
    <input class = "button ButtonSignin" type="date" name="birthday" placeholder="birthday"><br><br>
    <input class = "button ButtonSignin" type="number" name="phone" placeholder="phone"><br><br>
    <input class = "button ButtonSignin" type="hidden" name="id" value="signin"><br><br>
    <input class = "button ButtonSignin" type="submit" value="Inscription"><br><br>
</form>
    <p class = "MsgError">${info}</p>
<a id = "MenuHome" href="Controller?id=home" >Menu Home</a>
</div>

</body>
</html>
