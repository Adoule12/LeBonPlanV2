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
  <link href="css/views.css" type="text/css" rel="stylesheet"/>

</head>
<body>
<div class = "frame" id = "SigninFrame">
  <h2 class = "title">Création d'un Compte</h2>
  <form action="Controller?id=adCompte" method="post">
    <input class = "button ButtonSignin" type="email" name="email" placeholder="email" maxlength="320" required><br><br>
    <input class = "button ButtonSignin" type="password" name="password" placeholder="mot de passe" maxlength="60" required><br><br>
    <input class = "button ButtonSignin" type="text" name="lastname" placeholder="nom" maxlength="40" required><br><br>
    <input class = "button ButtonSignin" type="text" name="firstname" placeholder="prénom" maxlength="20" required><br><br>
    <input class = "button ButtonSignin" type="date" name="birthday" placeholder="date de naissance" required><br><br>
    <input class = "button ButtonSignin" type="number" name="phone" placeholder="numéro de téléphone" maxlength="20" required><br><br>
    <input class = "button ButtonSignin" type="hidden" name="id" value="adCompte"><br><br>
    <input class = "button ButtonSignin" type="submit" value="Création"><br><br>
  </form>
  <p class = "MsgError">${info}</p>
  <a href="Controller?id=goViews" >Retour</a>

</div>




</body>
</html>
