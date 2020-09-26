<%@ page import="com.conferencecenter.*" %>
<%@page import="com.conferencecenter.daos.AksesoreDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>Edit Salla</title>
     <link rel="stylesheet" href="../css/form.css">
         <link rel="stylesheet" href="../css/admin.css">
     
</head>
<body>

<%
	int id=Integer.parseInt(request.getParameter("sallaID"));
	String emer=request.getParameter("name");
	int kapacitet=Integer.parseInt(request.getParameter("kapacitet"));
	float kosto=Float.parseFloat(request.getParameter("kosto"));
	ArrayList<AksesoreObj> listaA = AksesoreDAO.getAksesoreForSalla(id);
	ArrayList<AksesoreObj> listaB = AksesoreDAO.getFreeAksesore();
%>
<br><br>
<div class="container">
<form action="../UpdateSalla" method="post">
	<label>ID Salle</label> 
	<input type="number" required readonly value="<%=id%>" name="id" >
  	<label>Salle name:</label>
    <input type="text" required  value="<%=emer%>" name="name">
    <label>Capacity:</label> 
    <input type="number" required min="0" value="<%=kapacitet%>" name="kapacitet" >
    <label>Day costo</label>
    <input type="number" required min="0" step="0.001" value="<%=kosto%>" name="kosto">
     <% if(listaA.size()+listaB.size()>0)
        	out.print("<label>Choise Aksesore:</label><br><br> ");

        for(AksesoreObj a:listaA) {%>
         <input type="checkbox" name="<%=a.getID()%>" checked="checked">
 		 <label>ID <%=a.getID()%> <%=a.getEmerAksesori()%></label>
        <%} 
        for(AksesoreObj a:listaB) {%>
         <input type="checkbox" name="<%=a.getID()%>">
 		 <label>ID <%=a.getID()%> <%=a.getEmerAksesori()%></label>
        <%} %>
        <br><br>
    <input type="submit" value="Update">
</form>
</div>

</body>
</html>
