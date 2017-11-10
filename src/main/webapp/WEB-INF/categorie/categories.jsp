<%@page import="fr.mathieu.categorie.Categorie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
.bordure{
	border: 1px solid black;
}
</style>

</head>
<body>

<%
	//on récupère les Categorie envoyées comme attribut de la requète
	List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
%>

<h1><% out.println(categories.size()); %> Categories </h1>

	<input type="button" value="Revenir au menu principal" name="Cr" onclick="openPage('main.jsp')" />
	
	<script type="text/javascript">
		 function openPage(pageURL){
		 	window.location.href = pageURL;
		 }
	</script>
	
<div>
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories">
		<label> <input type='text' name='searchedName'/> </label>
		<input type="submit" value="Rechercher">
	</form>
</div>

<div>

<table style="border-collapse: collapse;">
	<tr> 
		<th class="bordure"> Id </th>
		<th class="bordure"> Nom </th>
		<th class="bordure"> Nombre de Produit </th>
		<td></td>
		<td></td>
	</tr>
	
	<% for (Categorie current : categories) { %>
	<tr>
		<td class="bordure"> <% out.println(current.getId()); %> </td>
		<td class="bordure"> <% out.println(current.getNom()); %> </td>
		<td class="bordure"> <% out.println(current.getProduits().size()); %>
			<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits">
				<input type='hidden' name='categorieId' value='<%=current.getId()%>'/>
				<button>Voir</button>
			</form>	
		</td>
		<td>
			<!-- Le form permet d'associer sa soumission avec une 'method' renseignée 
				 d'une servlet d'URL 'action' renseigné.
				 Une valeur 'value' est envoyé à la servlet comme paramètre de requête
				 identifiée par 'name' -->
			<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories/edit">
				<input type='hidden' name='categorieId' value='<%=current.getId()%>'/>
				<button>Editer</button>
			</form>		
		</td>
		<td>
			<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories/delete">
				<input type='hidden' name='categorieId' value='<%=current.getId()%>'/>
				<button>X</button>
			</form>
		</td>
	</tr>
	<% } %>
</table>

</div>

<div>
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories/edit">
		<input type='hidden' name='produitId' value='-1'/>
		<button>Nouvelle Catégorie</button>
	</form>	
</div>

</body>
</html>