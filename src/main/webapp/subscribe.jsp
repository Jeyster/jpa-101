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
	boolean exist;
	try{
	exist = (boolean) request.getAttribute("exist"); 
	}
	catch(NullPointerException e){
		exist = false;
	}
	%>

	<div><h1> Création du compte utilisateur </h1></div>

	<form method='post' action="http://localhost:8080/jpa-101-1.0-SNAPSHOT/subscribe">
		<label> Nom <input type='text' name='lastname' required> </label>
		<br/>
		<label> Prénom <input type='text' name='firstname' required> </label>
		<br/>
		<label> Login <input type='text' name='login' required> </label>
		<br/>
		<label> Password <input type='password' name='password' required> </label>
		<br/>
		<input type="submit" value="S'inscire">
	</form>
	
	<div>
	<% 
	if (exist) {
		%>
		Login déjà utilisé, veuillez en créer un autre !
		<%
	}		
	%>
	</div>

</body>
</html>