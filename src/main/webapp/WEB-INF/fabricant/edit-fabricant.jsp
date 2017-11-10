<%@page import="fr.mathieu.fabricant.Fabricant"%>
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
	int produitId = (int) request.getAttribute("produitId");

	Fabricant fabricant = (Fabricant) request.getAttribute("fabricant"); 
	if (fabricant.getNom() == null) {
		fabricant.setNom("Nouveau Fabricant");
	}
	if (fabricant.getAdresse() == null) {
		fabricant.setAdresse("Nouvelle Adresse");
	}
%>


<div>
	<h1><% out.println(fabricant.getNom()); %></h1>
</div>

<div>
	<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/fabricants/edit">
		<label> Nom <input type='text' name='name' value='<%=fabricant.getNom()%>' required> </label>
		<br/>
		<label> Adresse <input type='text' name='address' value='<%=fabricant.getAdresse()%>'/> </label>
		<br/>
		<input type='hidden' name='produitId' value='<%=produitId%>'/>
		<input type="submit" value="Valider">
	</form>
</div>

	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/fabricants">
		<input type='hidden'>
		<button>Revenir aux Fabricants</button>
	</form>	


</body>
</html>