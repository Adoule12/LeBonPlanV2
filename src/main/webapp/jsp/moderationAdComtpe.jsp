<%--
  Created by IntelliJ IDEA.
  User: 33625
  Date: 14/03/2023
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Création d'un nouveau compte</title>
</head>
<body>
<div class = "frame" id = "SigninFrame">
  <h2 class = "title">LeBonPlan</h2>
  <form action="Controller?id=signin">
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
</div>
<a href="jsp/adminView.jsp" >Retour</a>

</body>
</html>
