<%@page import="fr.mathieu.user.User"%>
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
	boolean knownUser = true;
	
	try{
	knownUser = (boolean) request.getAttribute("knownUser");
	}
	catch(NullPointerException e){}

	
	
	String login = (String) request.getAttribute("login");
	String password = (String) request.getAttribute("password");
	%>

	<form method='get' action="login">
		<label> Login <input type='text' name='login' 
		<% if (login != null) { %>
		value=<%=login%>  
		<% } %>
		required> </label>
		<br/>
		<label> Password <input type='password' name='password' 
		<% if (password != null) { %>
		value=<%=password%>  
		<% } %> 
		required> </label>
		<br/>
		<input type="submit" value="Se connecter">
	</form>
	
	<input type="button" value="S'inscire" name="subscribe" onclick="openPage('subscribe.jsp')" />
	<script type="text/javascript">
		 function openPage(pageURL){
		 	window.location.href = pageURL;
		 }
	</script>
	
	<div>
	<% 
	if (knownUser == false) {
		%>
		Utilisateur inconnu, veuillez vous inscrire !
		<%
	}		
	%>
	</div>


</body>
</html>