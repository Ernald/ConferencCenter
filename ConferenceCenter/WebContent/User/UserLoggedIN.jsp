
<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="com.conferencecenter.SallaObj"
         import="com.conferencecenter.EventObj"
         import="com.conferencecenter.UserObj"
         import="com.conferencecenter.daos.*"
         import=" java.util.ArrayList"
         import=" java.util.Date"
         import ="java.text.DateFormat"
         import= "java.text.SimpleDateFormat"
   %>
 

   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">

   <html>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
            <link rel="stylesheet" type="text/css" href="../css/util.css">
			<link rel="stylesheet" type="text/css" href="../css/main.css">
            <link rel="stylesheet" type="text/css" href="../css/LogedIN.css">
       		<link rel="stylesheet" type="text/css" href="../css/Dropdown.css">
       		<link rel="stylesheet" type="text/css" href="../css/tab.css">
       		
         <title>   User  </title>
      </head>
	
      <body>
      
      <ul class="bar">
  <li class="bar"><a class="active" href="UserLoggedIN.jsp">Hall</a></li>
  <li class="bar"><a href="EventeUseri.jsp">Eventet</a></li>
  
  <li class="bar" style="float: right;"><a class="active" href="../Logout.jsp" >LogOut</a></li>
</ul>

         <center>
            <%
            	UserObj currentUser = (UserObj) session.getAttribute("currentSessionUser");
            %>
			
           HELLO <b><%=currentUser.getFirstName() + " " + currentUser.getLastName()%></b>
            
            
            
            
            
            
         </center>
  <div class="row">
	  <div class="column" style="background-color:	#DCDCDC;">
	  
	   
	     
	    
	    <form id="formaa" action="../servletRezervimEventi.java" id="form1" method="POST">
	    
	    
	
	     <label for="datesalle">The date the hall is needed</label>
	     
	     
    <input type="date" id="data" name="data" required  >
    
    
    <br>
       <label for="titull">Title</label>
    <input type="text"  name="emerEventi" required placeholder="Name of Event">
    <br>
       <label for="pershkrim">Short summary for the hall</label>
    <input type="text"  name="pershkrim" required placeholder="Summary">
    <br>
   
    
    <h5>Choose the Hall</h5>
    
    <div  style="width:610px;">
     <select name="salla" class="sl">
    <option  value="0">Choose one</option>
      
             
   
 
    
    
    
    <%
            	ArrayList<SallaObj> arrli0 = new ArrayList<SallaObj>();

            arrli0 = SallaDAO.merrSalla(arrli0);

              
               
               for(SallaObj b : arrli0)
            	   
               {
               
               
               
               
            	   
            	 String em = b.getEmerSalle();
            %>
      
      
      
      
      <option name="salla" value="<%out.print(em);  %>"><%out.print(em);  %></option>  
      
      
      
      <% 

          
          
		}
	               
	%> 

</select>
</div>
	
	 

<center> <button style="width:610px;" type="submit"  class="button" >Reserve Event</button></center>

    </form>
   
   
	  </div>
	  <div class="column" style="background-color:	#DCDCDC;">
	    
	    

 
	   
<table class="table-fill">
<thead>
<tr>
<th class="text-left">Hall</th>
<th class="text-left">Place</th>
<th class="text-left">Cost</th>
<th class="text-left">Accessory</th>
</tr>
</thead>


<%
	ArrayList<SallaObj> arrli1 = new ArrayList<SallaObj>();

arrli1 = SallaDAO.merrSalla(arrli1);

   
   for(SallaObj c : arrli1)
	   
   {
	   
	   String em= c.getEmerSalle();
   
	 %>
	   
	<tbody class="table-hover">
	
	<tr>
<td class="text-left"><%out.print(em); %></td>
<td><%=c.getNumerVendesh() %></td>
<td class="text-left"><%=c.getKostoDitore() %> $</td>
<td class="text-left">



  <div class="dropdown">
	  <button class="dropbtn">Accessory</button>
	  <div class="dropdown-content">
	   
	   <% 
	   ArrayList<String> arrli2 = new ArrayList<String>();
	   for(String c2 : AksesoreDAO.merrAksesore(arrli2,em) )
	   { 
		   
		%>
		
		<a href="#">  <% out.print(c2);%></a>
		<%    
		   
	   }
	   arrli2.clear();
	   %>
	   </div>
</div>
	  

</td>
</tr>
<% 	   
   }
%>

</tbody>
</table>

		<br>

	  </div> 
	  </div>
	
	
<script type="text/javascript">
				$(document).ready(function () {
					
					
					
					$("#formaa").submit(function (ev) {
						var salla = $(".sl").val(); 
						
						 if(salla == "0")
			            	{
			            	ev.preventDefault();
			            	alert("Ju lutem zgjidhni sallen");
			            	
			            	}
						
						 else
						
						alert("Rezervimi do te shqyrtohet nga nje Admin");
					});
					
				});
				
				
			</script>
	   </body>
		
	   </html>
