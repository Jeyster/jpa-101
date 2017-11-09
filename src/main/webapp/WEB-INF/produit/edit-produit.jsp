<%@page import="fr.mathieu.fabricant.Fabricant"%>
<%@page import="java.util.List"%>
<%@page import="fr.mathieu.categorie.Categorie"%>
<%@page import="fr.mathieu.produit.Produit"%>
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
	Produit produit = (Produit) request.getAttribute("produit"); 
	if (produit.getNom() == null) {
		produit.setNom("Nouveau Produit");
	}
	if (produit.getReference() == null) {
		produit.setReference("Nouvelle Reference");
	}
	List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
	List<Fabricant> fabricants = (List<Fabricant>) request.getAttribute("fabricants");
%>


<div>
	<h1><% out.println(produit.getNom()); %></h1>
</div>

<div>
	<form id="form_id" method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits/edit">
		<label> Nom <input type='text' name='name' value='<%=produit.getNom()%>'/> </label>
		<br/>
		<label> Référence <input type='text' name='ref' value='<%=produit.getReference()%>'/> </label>
		<br/>
		<label> Catégorie
			<select name="categorieId" form="form_id">
				<%
					for (Categorie current : categories) {
						%>
						<option value='<%=current.getId()%>'><% out.println(current.getNom()); %></option>
						<%
					}
				%>
				<option value>
			</select>
		</label>
		<br/>
		<label> Fabricant
			<select name="fabricantId" form="form_id">
				<%
					for (Fabricant current : fabricants) {
						%>
						<option value='<%=current.getId()%>'><% out.println(current.getNom()); %></option>
						<%
					}
				%>
				<option value>
			</select>
		</label>
		<br/>
		<input type="submit" value="Valider">
	</form>
</div>


</body>
</html>