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
    <link href="css/views.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "SigninFrame">
<h2 class = "title">LeBonPlan</h2>
<form action="Controller?id=signin" method="post" enctype='multipart/form-data'>
    <input class = "button ButtonSignin" type="email" name="email" placeholder="email" maxlength="320" required><br><br>
    <input class = "button ButtonSignin" type="password" name="password" placeholder="mot de passe" maxlength="60" required><br><br>
    <input class = "button ButtonSignin" type="text" name="lastname" placeholder="nom" maxlength="40" required><br><br>
    <input class = "button ButtonSignin" type="text" name="firstname" placeholder="prénom" maxlength="20" required><br><br>
    <input class = "button ButtonSignin" type="date" name="birthday" placeholder="date de naissance" required><br><br>
    <input class = "button ButtonSignin" type="number" name="phone" placeholder="numéro de téléphone" maxlength="20" required><br><br>

    <p class = "title" id = "postImg">Photo de profil</p>
    <input class = "button" id = "buttonPostImg" type="file" id="image_drop" name="image_drop" accept="image/png,image/jpg" /><br><br>

    <input class = "button ButtonSignin" type="hidden" name="id" value="signin"><br><br>
    <input class = "button ButtonSignin" type="submit" value="Inscription"><br><br>
    <script src="js/test.js" type="text/javascript"></script>
</form>
    <p class = "MsgError">${info}</p>
<a id = "MenuHome" href="Controller?id=home" >Menu Home</a>
</div>

</body>
</html>
