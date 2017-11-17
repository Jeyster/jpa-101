<%@page import="fr.mathieu.produit.Produit"%>
<%@page import="fr.mathieu.user.User"%>
<%@page import="java.util.List"%>
<%@page import="fr.mathieu.commande.Commande"%>
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
	Integer commandeId = (Integer) request.getAttribute("commandeId");
	Commande commande = (Commande) request.getAttribute("commande"); 
	List<User> users = (List<User>) request.getAttribute("users");
	List<Produit> produits = (List<Produit>) request.getAttribute("produits");
%>


<div>
	<h1>Commande n° <% out.println(commande.getId()); %></h1>
</div>

<div>
	<form id="form_id" method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/commandes/edit">
		<label> Produit
			<select name="produitId" form="form_id" required>
				<%
					for (Produit current : produits) {
						%>
						<option value='<%=current.getId()%>'><% out.println(current.getNom()); %></option>
						<%
					}
				%>
			</select>
		</label>
		
		<br/>
		
		<label> Quantité
			<input type="number" name="quantity">
		</label>
		
		<br/>
		
		<input type="hidden" name="commandeId" value=<%=commandeId%>>
		
		<input type="submit" value="Commander">
		
	</form>
</div>

<br/>

<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/commandes">
	<input type='hidden'>
	<button>Revenir aux Commandes</button>
</form>	

<br/>
	
<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/logout">
	<input type='hidden'/>
	<button>Se déconnecter</button>
</form>	

</body>
</html>