<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
</head>
<body>

	<h1> Gestion de la base de données Magasin </h1>

	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits">
		<input type='hidden'%>
		<button>Produits</button>
	</form>	
	
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories">
		<input type='hidden'/>
		<button>Catégorie</button>
	</form>	
	
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/fabricants">
		<input type='hidden'/>
		<button>Fabricants</button>
	</form>	
	
	<input type="button" value="Creation aleatoire" name="Cr" onclick="openPage('random-creation.jsp')" />
	<script type="text/javascript">
		 function openPage(pageURL){
		 	window.location.href = pageURL;
		 }
	</script>
	
	<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/delete">
		<input type='hidden'/>
		<button>Tout Supprimer</button>
	</form>	

</body>
</html>