<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "com.ensta.librarymanager.services.implementation.LivreService" %>
<%@ page import = "com.ensta.librarymanager.services.implementation.MembreService" %>
<%@ page import = "com.ensta.librarymanager.modele.Emprunt" %>
<%@ page import = "com.ensta.librarymanager.modele.Livre" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Fiche livre</h1>
      </div>
      <div class="row">
      <div class="container">
      <h5>Suppression du livre n°${livre.primaryKey}</h5> <!-- TODO : afficher l'id du livre au lieu de 42 -->
        <div class="row">
          <p>Êtes-vous sûr de vouloir supprimer le livre ${livre.titre} de ${livre.auteur} (code ${livre.primaryKey}) ?</p> <!-- TODO : compléter les trois informations ci-contre -->
	      <form action="/TP3Ensta/livre_delete" method="post" class="col s12">
            <input type="hidden" value="${livre.primaryKey}" name="id"> <!-- TODO : remplacer idDuLivre par l'id du livre -->
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer
	            <i class="material-icons right">delete</i>
	          </button>
	          <a class="btn waves-effect waves-light orange" href="/TP3Ensta/livre_details?id=${livre.primaryKey}">Annuler</a> <!-- TODO : remplacer idDuLivre par l'id du livre -->
	        </div>
	      </form>
	    </div>	    
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
