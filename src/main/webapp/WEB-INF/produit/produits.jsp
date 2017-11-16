<%@page import="fr.mathieu.produit.Produit"%>
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
	List<Produit> produits = (List<Produit>) request.getAttribute("produits");
%>

<h1><% out.println(produits.size()); %> Produits</h1>

<div>
	<input type="button" value="Revenir au menu principal" name="Cr" onclick="openPage('main.jsp')" />
	<script type="text/javascript">
		 function openPage(pageURL){
		 	window.location.href = pageURL;
		 }
	</script>
</div>

<!--
<div>
	<a href="${pageContext.request.contextPath}/produits/export" download="produits.xls">Exporter</a> -->
	<!-- ${pageContext.request.contextPath} = "http://localhost:8080/jpa-101-1.0-SNAPSHOT"  -->
<!-- </div> 

<div>
	<form method="post" enctype="multipart/form-data" action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits/import">
	   <input type="file" id="file" name="file" multiple>
	   <input type="hidden" value="D:/" name="destination">
	   <button>Envoyer</button>
	</form>
</div>
 -->

<div>
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits">
		<label> <input type='text' name='searchedName'/> </label>
		<input type="submit" value="Rechercher">
	</form>
</div>

<div>

<table style="border-collapse: collapse;">
	<tr> 
		<th class="bordure"> Id </th>
		<th class="bordure"> Nom </th>
		<th class="bordure"> Référence </th>
		<th class="bordure"> Id Catégorie </th>
		<th class="bordure"> Id Fabriquant </th>
		<td></td>
		<td></td>
	</tr>
	
	<% for (Produit current : produits) { %>
	<tr>
		<td class="bordure"> <% out.println(current.getId()); %> </td>
		<td class="bordure"> <% out.println(current.getNom()); %> </td>
		<td class="bordure"> <% out.println(current.getReference()); %> </td>
		<td class="bordure"> <% out.println(current.getCategorie().getId()); %> </td>
		<td class="bordure"> <% out.println(current.getFabricant().getId()); %> </td>
		<td>
			<!-- Le form permet d'associer sa soumission avec une 'method' renseignée 
				 d'une servlet d'URL 'action' renseigné.
				 Une valeur 'value' est envoyé à la servlet comme paramètre de requête
				 identifiée par 'name' -->
			<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits/edit">
				<input type='hidden' name='produitId' value='<%=current.getId()%>'/>
				<button>Editer</button>
			</form>		
		</td>
		<td>
			<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits/delete">
				<input type='hidden' name='produitId' value='<%=current.getId()%>'/>
				<button>X</button>
			</form>
		</td>
	</tr>
	<% } %>
</table>

</div>

<div>
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits/edit">
		<input type='hidden'/>
		<button>Nouveau Produit</button>
	</form>	
</div>

<br/>
	
<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/logout">
	<input type='hidden'/>
	<button>Se déconnecter</button>
</form>	

</body>
</html>