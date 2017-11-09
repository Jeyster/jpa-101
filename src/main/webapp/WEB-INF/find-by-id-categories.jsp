<%@page import="fr.mathieu.Produit"%>
<%@page import="fr.mathieu.Categorie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<%
	Categorie categorie = (Categorie) request.getAttribute("categorie");
	out.println("Nom catégorie : " + categorie.getNom());	
	
	for (Produit current : categorie.getProduits()){%>
	<div>
		<% 
		out.println("Nom produit : " + current.getNom()); 
		%>
	</div>
	<%}
	
%>
</div>
</body>
</html>