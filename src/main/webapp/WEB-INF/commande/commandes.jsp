<%@page import="fr.mathieu.commande.Commande"%>
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
	//on récupère les Commandes envoyées comme attribut de la requète
	List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");
%>

<h1><% out.println(commandes.size()); %> Commandes </h1>

	<input type="button" value="Revenir au menu principal" name="Cr" onclick="openPage('main.jsp')" />
	<script type="text/javascript">
		 function openPage(pageURL){
		 	window.location.href = pageURL;
		 }
	</script>

<div>

<table style="border-collapse: collapse;">
	<tr> 
		<th class="bordure"> Id </th>
		<th class="bordure"> Utilisateur </th>
		<th class="bordure"> Produit </th>
		<th class="bordure"> Quantité </th>
		<td></td>
		<td></td>
	</tr>
	
	<% for (Commande current : commandes) { %>
	<tr>
		<td class="bordure"> <% out.println(current.getId()); %> </td>
		<td class="bordure"> <% out.println(current.getUser()); %> </td>
		<td class="bordure"> <% out.println(current.getProduit().getNom()); %></td>
		<td class="bordure"> <% out.println(current.getQuantity()); %></td>
		<td>
			<!-- Le form permet d'associer sa soumission avec une 'method' renseignée 
				 d'une servlet d'URL 'action' renseigné.
				 Une valeur 'value' est envoyé à la servlet comme paramètre de requête
				 identifiée par 'name' -->
			<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/commandes/edit">
				<input type='hidden' name='commandeId' value='<%=current.getId()%>'/>
				<button>Editer</button>
			</form>		
		</td>
		<td>
			<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/commandes/delete">
				<input type='hidden' name='commandeId' value='<%=current.getId()%>'/>
				<button>X</button>
			</form>
		</td>
	</tr>
	<% } %>
</table>

</div>

<div>
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/commandes/edit">
		<input type='hidden'>
		<button>Nouvelle Commande</button>
	</form>	
</div>

<br/>
	
<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/logout">
	<input type='hidden'/>
	<button>Se déconnecter</button>
</form>	

</body>
</html>