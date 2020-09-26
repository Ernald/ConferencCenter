<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.conferencecenter.daos.SallaDAO" %>
<html>
<head>
    <title>Edit Aksesor</title>
     <link rel="stylesheet" href="../css/form.css">
</head>
<body>

<%
int id =Integer.parseInt(request.getParameter("id"));
String emer=request.getParameter("name");
int gjendja=Integer.parseInt(request.getParameter("gjendja"));
int sallaID=Integer.parseInt(request.getParameter("sallaID"));
%>

<div class="container">

<form action="../UpdateAks" method="get">
	Aksesor ID:
    <input type="number" readonly value="<%=id%>" name="id" >
    Aksesori name:
    <input type="text" required value="<%=emer%>" name="name">
    State :
        <select  name="gjendja">
        <option value="1" <%if(gjendja==1) out.print("selected"); %> >1 : Work</option>
        <option value="0" <%if(gjendja==0) out.print("selected"); %> >0 : With problem</option>
        </select>
    
    <%
    	ArrayList<Integer> sallat= SallaDAO.getIDsOfSalla();
    %>
    Salla :<select name="sallaID">
    <option value="0">Magazine</option>
    <% for (int s:sallat) {
    	String selected=null;
    	if(s==sallaID)
    		selected="selected";
    	out.print("<option " +selected +" value=\""+s+"\">"+SallaDAO.gjejEmerSalle(s)+"</option>");  
    	}%>
</select>
    <input type="submit" value="Update">
</form>
</div>

</body>
</html>
