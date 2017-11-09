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
		<label> Nom <input type='text' name='name' value='<%=categorie.getNom()%>'/> </label>
		<input type="submit" value="Valider">
	</form>
</div>

</body>
</html>