<%--
  Created by IntelliJ IDEA.
  User: axoul
  Date: 02/03/2023
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
    <link href="css/views.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "editFrame">
    <h2 class ="title">Modification des informations personnelles</h2>
    <p class ="MsgError">Toucher que ce que vous voulez modifier</p>
    <form action="Controller?id=editUser" method="post" enctype='multipart/form-data'>
    <input class = "button" type="email" name="email" placeholder="mail"><br><br>
    <input class = "button" type="text" name="lastname" placeholder="lastname"><br><br>
    <input class = "button" type="text" name="firstname" placeholder="firstname"><br><br>
    <input class = "button" type="date" name="birthday" placeholder="birthday"><br><br>

    <p class = "title" id = "postImg">photo de profil</p>
    <input class = "button" id = "buttonPostImg" type="file" id="image_drop" name="image_drop" accept="image/png,image/jpg" /><br><br>

    <input class = "button" type="submit" value="modifier">
    <input type="hidden" name="id" value="editUser">
    <script src="js/test.js" type="text/javascript"></script>
</form>
    <p class = "MsgError">${info}</p>
<a href="Controller?id=goViews" >Menu Home</a>
</div>
</body>
</html>
