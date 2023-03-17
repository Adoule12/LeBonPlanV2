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
  <link href="css/views.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class = "frame" id = "editFrame">
  <h2 class ="title">Information de l'annonce actuelle</h2>
  <div class = "annonce">
    <c:set var="compteur" value="0" scope="page" />
    <c:set var="compteurAd" value="0" scope="page" />
    <c:set var="compteurOw" value="0" scope="page" />
    <c:forEach var="info" items="${requestScope.listInfoAd}">
      <c:forEach var="ad" items="${info}">
        <c:if test="${compteur==0}">
          <c:if test="${compteurAd==0}">
            <p>titre:${ad}</p></c:if>
          <c:if test="${compteurAd==1}">
            <p>prix:${ad}</p></c:if>
          <c:if test="${compteurAd==2}">
            <img class="image" src="${ad}"></c:if>
          <c:if test="${compteur == 3}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
            <c:set var="idAD" value="${ad}" scope="page" />
          </c:if>
          <c:if test="${compteurAd==4}">
            <p>ville:${ad}</p></c:if>
          <c:if test="${compteurAd==5}">
            <p>description:${ad}</p></c:if>
          <c:if test="${compteurAd==6}">
            <p>state:${ad}</p>
            <c:set var="stateAd" value="${ad}" scope="page" />
        </c:if><br>
          <c:set var="compteurAd" value="${compteurAd+1 }" scope="page" />
        </c:if>
      </c:forEach>

      <c:forEach var="owner" items="${info}">

        <c:if test="${compteur==1}">
          <c:if test="${compteurOw==0}">
            <h1>Vendeur</h1>
            <p>mail : ${owner}</p></c:if>
          <c:if test="${compteurOw==1}">
            <p>nom : ${owner}</c:if>
          <c:if test="${compteurOw==2}">
            prenom: ${owner}</p></c:if>
          <c:if test="${compteurOw==3}">
            <p>numero: ${owner}</p></c:if>
          <c:if test="${compteur == 4}"><%-- ID de l'annonce en 3eme place dans la liste des infosAd récupérer  --%>
            <c:set var="idAD" value="${infoAd}" scope="page" />
          </c:if>

          <c:set var="compteurOw" value="${compteurOw+1 }" scope="page" />
        </c:if>
      </c:forEach>

      <c:set var="compteur" value="${compteur+1 }" scope="page" />
    </c:forEach>

  </div>



    <h2 class ="title">Modification d'une annonce</h2>
    <p class = "MsgError">toucher que ce que vous voulez modifier</p>
    <form action="Controller?id=editAd" method="post" enctype="multipart/form-data">
      <p class = "subtitle">Etat actuel de visibilité de l'annonce sur le site :</p>
      <c:if test="${stateAd == 1}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
        <p class = "MsgError">L'annonce est actuellement Visible sur le site</p>
      </c:if>
      <c:if test="${stateAd == 0}"><%-- state de l'annonce en 4eme place dans la liste des infosAd récupérer  --%>
        <p class = "MsgError">L'annonce n'est actuellement pas Visible sur le site</p>
      </c:if>
      <p class = "subtitle">Etat futur de visibilité de l'annonce sur le site :</p>
      <input type="radio" id="state0" name="state" value="0" ><%-- state = 0 ==== Annonce PAS-visible --%>
      <label class = "radio" for="state0">NON-Visible</label>
      <input type="radio" id="state1" name="state" value="1" > <%-- state = 1 ==== Annonce visible --%>
      <label class = "radio" for="state1">Visible</label>
      <br><br><br>
    <input class = "button" type="text" name="title" placeholder="titre"><br><br>
    <input class = "button" type="number" name="price" placeholder="prix"><br><br>

    <p>Déposez votre image</p>
    <input type="file" id="image_drop" name="image_drop" /><br><br>

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
    <label class = "radio" for="catego5">Supprimer l'annonce?</label>

    <input type="hidden" name="id" value="editAd">
    <input type="hidden" name="annonce" value="${idAD}">
      <br><br><br>
    <input  class = "button" type="submit" value="Soumettre">
    <script src="js/test.js" type="text/javascript"></script>
  </form>
  <a href="Controller?id=goViews" >Menu Home</a>
</div>
</body>
</html>
