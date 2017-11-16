<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="main.css">
<title>Main Page</title>
</head>
<body>
	<div id="conteneur">
		<div class="firstchild">
			<div>
				<h1> Gestion de la base de données Magasin </h1>
			</div>
		</div>
	
		<div class="conteneur1 firstchild">
			<div>
				<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/produits">
					<input type='hidden'>
					<button>Produits</button>
				</form>	
			</div>
			
			<div>
				<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/categories">
					<input type='hidden'/>
					<button>Catégorie</button>
				</form>	
			</div>
			
			<div>
				<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/fabricants">
					<input type='hidden'/>
					<button>Fabricants</button>
				</form>	
			</div>
		</div>
		<div class="conteneur2 firstchild">	
			<div>
				<input type="button" value="Creation aleatoire" name="Cr" onclick="openPage('random-creation.jsp')" />
				<script type="text/javascript">
					 function openPage(pageURL){
					 	window.location.href = pageURL;
					 }
				</script>
			</div>
			
			<div>
				<form method='get' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/delete">
					<input type='hidden'/>
					<button>Tout Supprimer</button>
				</form>	
			</div>
		</div>
		<div class="conteneur1 firstchild">
			<form method='get' action="commandes">
				<input type='hidden'/>
				<button>Passer une commande</button>
			</form>	
			<form method='get' action="logout">
				<input type='hidden'/>
				<button>Se déconnecter</button>
			</form>	
		</div>
	</div>

</body>
</html>
