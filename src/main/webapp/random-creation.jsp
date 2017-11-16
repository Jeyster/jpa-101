<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Many</title>
</head>
<body>

<%
	int nbrProduit = 0;
	int nbrCategorie = 0;
	int nbrFabricant = 0;

	try{
		nbrProduit = (int) request.getAttribute("nbrProduit");
	} catch (NullPointerException e) {};
	try{
		nbrCategorie = (int) request.getAttribute("nbrCategorie");
	} catch (NullPointerException e) {};
	try{
		nbrFabricant = (int) request.getAttribute("nbrFabricant");
	} catch (NullPointerException e) {};
%>

	<h1> Création aléatoire </h1>

	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/random-creation">
			<label> Produits <input placeholder='Nombre à créer' type='text' name='nbrProduit' value='0'/> </label>
			<% if (nbrProduit != 0) {
				 %>
				 <span> <% out.println(nbrProduit); %> Produits ont été crées !</span>
				 <%
			} %>
			<br/>
			<label> Catégories <input placeholder='Nombre à créer' type='text' name='nbrCategorie' value='0'/> </label>
			<% if (nbrCategorie != 0) {
				 %>
				 <span> <% out.println(nbrCategorie); %> Categories ont été créées !</span>
				 <%
			} %>
			<br/>
			<label> Fabricants <input placeholder='Nombre à créer' type='text' name='nbrFabricant' value='0'/> </label>
			<% if (nbrFabricant != 0) {
				 %>
				 <span> <% out.println(nbrFabricant); %> Fabricants ont été crées !</span>
				 <%
			} %>
			<br/>
			<input type="submit" value="Créer">
	</form>
	<input type="button" value="Revenir au menu principal" name="Cr" onclick="openPage('main.jsp')" />
	
	<script type="text/javascript">
		 function openPage(pageURL){
		 	window.location.href = pageURL;
		 }
	</script>
	
	<br/>
	
	<form method='get' action="logout">
		<input type='hidden'/>
		<button>Se déconnecter</button>
	</form>	

</body>
</html>