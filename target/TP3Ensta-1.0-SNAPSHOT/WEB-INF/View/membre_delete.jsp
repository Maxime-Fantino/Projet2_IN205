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
        <h1 class="page-announce-text valign">Fiche membre</h1>
      </div>
      <div class="row">
      <div class="container">
      <h5>Suppression du membre n°${membre.primaryKey}</h5> <!-- TODO : remplacer 312 par l'id du membre -->
        <div class="row">
          <p>Etes-vous sûr de vouloir supprimer la fiche de ${membre.prenom} ${membre.nom} ?</p> <!-- TODO : remplacer prenomDuMembre et nomDuMembre par les valeurs correspondantes -->
	      <form action="/TP3Ensta/membre_delete" method="post" class="col s12">
            <input type="hidden" value="${membre.primaryKey}" name="id"> <!-- TODO : remplacer idDuMembre par l'id du membre -->
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit" name="action">Supprimer
	            <i class="material-icons right">delete</i>
	          </button>
	          <a class="btn waves-effect waves-light orange" href="/TP3Ensta/membre_details?id=${membre.primaryKey}">Annuler</a> <!-- TODO : remplacer idDuMembre par l'id du membre -->
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
