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
    <link href="css/commune.css" type="text/css" rel="stylesheet"/>
</head>
<body>
  <div class = "frame" id = "editFrame">
    <form action="Controller?id=editAd" method="post">
    <c:forEach var="ad" items="${requestScope.listAd}">
      <c:set var="compteur" value="0" scope="page" />
      <c:forEach var="info" items="${ad}">
        <c:if test="${compteur<1}"><br>
          ${info}
        </c:if>
        <c:if test="${compteur == 1}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
          <c:set var="idAD" value="${info}" scope="page" />

        </c:if>
        <c:set var="compteur" value="${compteur+1 }" scope="page" />
      </c:forEach>

        <input type="radio" name="annonce" value="${idAD}" required>
      <label class = "radio" for="condi1">${idAD}</label>

    </c:forEach>

    <h2 class ="title">Modification d'une annonce</h2>
    <p class = "MsgError">toucher que ce que vous voulez modifier</p>

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
      <br><br>
      <input type="checkbox" id="check" name="delete">
      <label class = "radio" for="catego5">Suprimer l'annonce?</label>
      <input type="hidden" name="id" value="editAd">
      <br><br><br>
      <input  class = "button" type="submit" value="Soumettre">
    </form>
    <a href="Controller?id=goViews" >Menu Home</a>
  </div>
</body>
</html>
