<%--
  Created by IntelliJ IDEA.
  User: axoul
  Date: 08/03/2023
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>modifier annonce</title>
    <link href="css/home.css" type="text/css" rel="stylesheet"/>
</head>
<body>
  <div class = "frame" id = "editFrame">
    <c:forEach var="mesAnnonces" items="${requestScope.list}">
      ${mesAnnonces}<br>
    </c:forEach>
    <h2 class ="title">Modification d'une annonce</h2>
    <h1 class ="title">toucher que ce que vous voulez modifier</h1>
    <form action="Controller?id=editAd">
      <input class = "button" type="text" name="title" placeholder="titre"><br><br>
      <input class = "button" type="number" name="price" placeholder="prix"><br><br>
      <input class = "button" type="text" name="picture" placeholder="adresse image"><br><br>
      <input class = "button" type="text" name="description" placeholder="description de l'annonce"><br><br>
      <input class = "button" type="text" name="city" placeholder="ville annonce"><br><br>
      <br>
      <p class = "subtitle">Dans quel etat est votre objet ?</p>
      <input type="radio" id="condi1" name="conditions" value="1" >
      <label class = "radio" for="condi1">Neuf</label>
      <input type="radio" id="condi2" name="conditions" value="2" >
      <label class = "radio" for="condi2">Quasi Neuf</label>
      <input type="radio" id="condi3" name="conditions" value="3" >
      <label class = "radio" for="condi3">Un Peu Abime</label>
      <input type="radio" id="condi4" name="conditions" value="4" >
      <label class = "radio" for="condi4">Tres Abime</label>

      <br>
      <p class = "subtitle">A quelle categorie appartient votre objet ?</p>
      <input type="radio" id="catego1" name="category" value="1" >
      <label class = "radio" for="catego1">Vehicule</label>
      <input type="radio" id="catego2" name="category" value="2" >
      <label class = "radio" for="catego2">Mobilier</label>
      <input type="radio" id="catego3" name="category" value="3" >
      <label class = "radio" for="catego3">Jardin</label>
      <input type="radio" id="catego4" name="category" value="4" >
      <label class = "radio" for="catego4">Electronique</label>
      <input type="radio" id="catego5" name="category" value="5" >
      <label class = "radio" for="catego5">Autre</label>

      <input type="hidden" name="id" value="postAd">
      <br><br><br>
      <input  class = "button" type="submit" value="Soumettre">
    </form>
    <p class = "MsgError">${info}</p>
    <a href="Controller?id=goViews" >Menu Home</a>
  </div>
</body>
</html>
