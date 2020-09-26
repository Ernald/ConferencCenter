
    
    
    
 <%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="com.conferencecenter.SallaObj"
         import="com.conferencecenter.EventObj"
         import="com.conferencecenter.UserObj"
          import="com.conferencecenter.daos.*"
         import=" java.util.ArrayList"
         import="java.util.Date"
         import ="java.text.DateFormat"
         import= "java.text.SimpleDateFormat"
         import= "static javax.swing.JOptionPane.showMessageDialog"
   %>   
    
  
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="../css/LogedIN.css">
  <link rel="stylesheet" type="text/css" href="../css/util.css">
			<link rel="stylesheet" type="text/css" href="../css/main.css">
			<link rel="stylesheet" type="text/css" href="../css/tab.css">
<meta charset="ISO-8859-1">
<title>Evente Useri</title>
</head>
<body style="background-color: #DCDCDC;">

      <ul class="bar">
  <li class="bar"><a  href="UserLoggedIN.jsp">Salla</a></li>
  <li class="bar"><a class="active" href="EventeUseri.jsp">Eventet</a></li>
  
  <li class="bar" style="float: right;"><a class="active"  href="../Logout.jsp" >LogOut</a></li>
  
</ul>
<div class="sticky">
<%  SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
Date date = new Date();  
out.print("Data e sotme : "+formatter.format(date));  
%>
 </div>
 
 <%
 
 String dtVendosurFillestare="2020-01-01";
 String dtVendosur=request.getParameter("data");
 if(dtVendosur!=null)
 {
	 dtVendosurFillestare=dtVendosur;
	 
 }
 
 %>
 
 
  
<center>
  <form action="EventeUseri.jsp" method="POST">
  <div    style="width:610px;">
  <label for="data">Data fillestare e eventeve</label>
   <input type="date" id="data" name="data" required  value="2020-01-01">
   </div>
   <button class="button1" type="submit">Shiko</button>
   </form>
 </center>
 
 <div class="table-title">
	 <h2 align="center">Tabela e eventeve</h2>
	  </div>
	  
	  
	  
	  

<table class="table-fill">
<thead>
<tr>
<th class="text-left">Emer eventi</th>
<th class="text-left">Data</th>
<th class="text-left">Emer Salle</th>
<th class="text-left">Statusi</th>

</tr>
</thead>
	    



<%
	ArrayList<EventObj> arrli2 = new ArrayList<EventObj>();

arrli2 = EventeDAO.merrEventeMeDate(arrli2, request, dtVendosurFillestare );

   
   for(EventObj c : arrli2)
	   
   {
   
   
   
   
	   
	 String en = c.getEventname();
	 
	 
	
	Integer sid = c.getSallaID();
	
	String es = SallaDAO.gjejEmerSalle(sid);
	
	
	String fjala= "pending";
	Integer st = c.getStatusi();
	
	
   if(st==1)
	{
		fjala="konfirmuar";
	}
   else if(st==-1)
   {
	   
	   fjala="refuzuar";
   }
	
%>
	<tbody class="table-hover">
	
	<tr>
<td class="text-left"><% out.print(en); %></td>
<td class="text-left"><%=c.getData() %></td>
<td><% out.print(es); %></td>
<td class="text-left"><% 

if(fjala.equalsIgnoreCase("refuzuar"))
{
	out.print("<b><font color= \"red\" >"+fjala+"</font></b>");	
}

else if(fjala.equalsIgnoreCase("konfirmuar"))
{
	
	out.print("<b><font color= \"green\" >"+fjala+"</font></b>");	
}

else{
	
	out.print("<b><font color= \"blue\" >"+fjala+"</font></b>");	
}





%></td>
	  
	  
  
	  
</tr>	  
	  
	  
<% 	   
   }
%>

</tbody>
</table>












</body>
</html>