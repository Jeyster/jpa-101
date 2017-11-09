<%@page import="fr.mathieu.Produit"%>
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
	Produit produit = (Produit) request.getAttribute("produit");
	out.println("Le produit suivant a été effacé de la base de données :");
	out.println(produit.getId() + " ; " + produit.getNom() + " ; " + produit.getReference());
%>
</div>

</body>
</html>