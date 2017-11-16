<%@page import="fr.mathieu.categorie.Categorie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
	int produitId = (int) request.getAttribute("produitId");

	Categorie categorie = (Categorie) request.getAttribute("categorie"); 
	if (categorie.getNom() == null) {
		categorie.setNom("Nouvelle Categorie");
	}
%>


<div>
	<h1><% out.println(categorie.getNom()); %></h1>
</div>

<div>
	<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories/edit">
		<label> Nom <input type='text' name='name' value='<%=categorie.getNom()%>' required> </label>
		<input type='hidden' name='produitId' value='<%=produitId%>'/>
		<input type="submit" value="Valider">
	</form>
</div>

<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories">
	<input type='hidden'>
	<button>Revenir aux Catégories</button>
</form>	

<br/>
	
<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/logout">
	<input type='hidden'/>
	<button>Se déconnecter</button>
</form>	

</body>
</html>