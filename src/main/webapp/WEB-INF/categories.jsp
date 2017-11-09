<%@page import="fr.mathieu.Categorie"%>
<%@page import="java.util.List"%>
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
	List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
%>

<h1>Categories</h1>

<div>

<table style="border-collapse: collapse;">
	<tr> 
		<th style="border: 1px solid black"> Id </th>
		<th style="border: 1px solid black"> Nom </th>
		<td></td>
		<td></td>
	</tr>
	
	<% for (Categorie current : categories) { %>
	<tr>
		<td style="border: 1px solid black;"> <% out.println(current.getId()); %> </td>
		<td style="border: 1px solid black;"> <% out.println(current.getNom()); %> </td>
		<td>
			<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories/edition">
				<input type='hidden' name='clickedId' value='<%=current.getId()%>'/>
				<button>Editer</button>
			</form>		
		</td>
		<td>
			<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories/supprimer">
				<input type='hidden' name='clickedId' value='<%=current.getId()%>'/>
				<button>X</button>
			</form>
		</td>
	</tr>
	<% } %>
</table>

</div>

<div>
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories/edition">
		<input type='hidden' name='clickedId' value='0'/>
		<button>Nouvelle Catégorie</button>
	</form>	</div>

</body>
</html>