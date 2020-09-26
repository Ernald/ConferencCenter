<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>

<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">


</head>
<body>
<h1 align="center">Logout success, press button to return back to Welcome Page</h1>

<% 



 session.invalidate();








%>



					

<div class="container-login100-form-btn">
<button onclick="window.location.href = 'WelcomPage.jsp';" class="login100-form-btn"> welcome page </button>

</div>
</body>
</html>