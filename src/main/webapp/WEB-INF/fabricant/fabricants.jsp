<%@page import="fr.mathieu.fabricant.Fabricant"%>
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
	List<Fabricant> fabricants = (List<Fabricant>) request.getAttribute("fabricants");
%>

<h1>Fabricants</h1>

<div>

<table style="border-collapse: collapse;">
	<tr> 
		<th class="bordure"> Id </th>
		<th class="bordure"> Nom </th>
		<th class="bordure"> Adresse </th>
		<td></td>
		<td></td>
	</tr>
	
	<% for (Fabricant current : fabricants) { %>
	<tr>
		<td class="bordure"> <% out.println(current.getId()); %> </td>
		<td class="bordure"> <% out.println(current.getNom()); %> </td>
		<td class="bordure"> <% out.println(current.getAdresse()); %> </td>
		<td>
			<!-- Le form permet d'associer sa soumission avec une 'method' renseignée 
				 d'une servlet d'URL 'action' renseigné.
				 Une valeur 'value' est envoyé à la servlet comme paramètre de requête
				 identifiée par 'name' -->
			<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/fabricants/edit">
				<input type='hidden' name='clickedId' value='<%=current.getId()%>'/>
				<button>Editer</button>
			</form>		
		</td>
		<td>
			<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/fabricants/delete">
				<input type='hidden' name='clickedId' value='<%=current.getId()%>'/>
				<button>X</button>
			</form>
		</td>
	</tr>
	<% } %>
</table>

</div>

<div>
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/fabricants/edit">
		<input type='hidden' name='clickedId' value='0'/>
		<button>Nouveau Fabricant</button>
	</form>	</div>

</body>
</html>