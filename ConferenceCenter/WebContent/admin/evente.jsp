<%@page import="com.conferencecenter.daos.SallaDAO"%>
<%@ page import="com.conferencecenter.*" %>
<%@ page import="com.conferencecenter.daos.EventeDAO" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Evente</title>
    <link rel="stylesheet" href="../css/admin.css">
     <link rel="stylesheet" href="../css/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<ul class="menu">
<li><a href="admin.jsp">Home</a ></li>
<li><a class="active"href="evente.jsp">Evente</a></li>
<li><a href="salla.jsp">Salla</a></li>
<li><a href="aksesore.jsp">Aksesore</a></li>
<li class="active" style="float: right;"><a href="../Logout.jsp" >LogOut</a></li>

</ul>

<%
	ArrayList<EventObj> lista = EventeDAO.getEvente();
%>

<select id="mySelect" onchange="changeTable()">
  <option value=4 >All
  <option value= 0>Pending
  <option value=1>Confirmed
  <option value=-1>Unconfirmed
</select>

<table id="events">
    <thead>
    <tr>
        <th>Event ID</th>
        <th>Title</th>
        <th>Username</th>
        <th>Summary</th>
        <th>Data</th>
        <th>Salle name</th>
        <th>Status</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for(int i = 0; i < lista.size(); i++) {
        EventObj e = lista.get(i); %>

    <tr>
        <td><%=e.getEventID()%></td>
        <td><%=e.getEventname()%></td>
        <td><%=e.getUsername()%></td>
        <td><%=e.getPershkrim()%></td>
        <td><%=e.getData()%></td>
        <td><%=SallaDAO.gjejEmerSalle(e.getSallaID())%></td>
        <td><% if (e.getStatusi()==0)
            out.print("Pending");
        else if(e.getStatusi()==1)
            out.print("Confirm");
        else
            out.print("Unconfirmed");%></td>

        <% if (e.getStatusi()==0){ %>
        <td><button type="button" id="<%=e.getEventID()%>" value="1" class="myButton ok check">Confirm</button> </td>
        <td><button type="button" id="<%=e.getEventID()%>" value="-1" class="myButton delete check">Unconfirm</button> </td>
        <%  }
        else out.print("<td></td> <td></td>");%>

    </tr>
    <%
        }
    %>
    </tbody>
</table>


<table id="events_pending">
    <thead>
    <tr>
        <th>Event ID</th>
        <th>Titulli</th>
        <th>Username</th>
        <th>Pershkrim</th>
        <th>Data</th>
        <th>Emer Salle</th>
        <th>Statusi</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for(int i = 0; i < lista.size(); i++) {
        EventObj e = lista.get(i);
        if(e.getStatusi()!=0)
        	continue; %>

    <tr>
        <td><%=e.getEventID()%></td>
        <td><%=e.getEventname()%></td>
        <td><%=e.getUsername()%></td>
        <td><%=e.getPershkrim()%></td>
        <td><%=e.getData()%></td>
 		<td><%=SallaDAO.gjejEmerSalle(e.getSallaID())%></td>  
        <td><% out.print("Pending"); %></td>
        <td><button type="button" id="<%=e.getEventID()%>" value="1" class="myButton ok check">Konfirmo</button> </td>
        <td><button type="button" id="<%=e.getEventID()%>" value="-1" class="myButton delete check">Refuzo</button> </td>

    </tr>
    <%
        }
    %>
    </tbody>
</table>


<table id="events_confirmed">
    <thead>
    <tr>
        <th>Event ID</th>
        <th>Titulli</th>
        <th>Username</th>
        <th>Pershkrim</th>
        <th>Data</th>
        <th>Emer Salle</th>
        <th>Statusi</th>
      
    </tr>
    </thead>
    <tbody>
    <% for(int i = 0; i < lista.size(); i++) {
        EventObj e = lista.get(i);
        if(e.getStatusi()!=1)
        	continue; %>

    <tr>
        <td><%=e.getEventID()%></td>
        <td><%=e.getEventname()%></td>
        <td><%=e.getUsername()%></td>
        <td><%=e.getPershkrim()%></td>
        <td><%=e.getData()%></td>
        <td><%=SallaDAO.gjejEmerSalle(e.getSallaID())%></td>
        <td><% out.print("Konfirmuar");%></td>

    </tr>
    <%
        }
    %>
    </tbody>
</table>


<table id="events_refused">
    <thead>
    <tr>
        <th>Event ID</th>
        <th>Titulli</th>
        <th>Username</th>
        <th>Pershkrim</th>
        <th>Data</th>
        <th>Emer Salle</th>
        <th>Statusi</th>
    </tr>
    </thead>
    <tbody>
    <% for(int i = 0; i < lista.size(); i++) {
        EventObj e = lista.get(i);
        if(e.getStatusi()!=-1)
        	continue; %>

    <tr>
        <td><%=e.getEventID()%></td>
        <td><%=e.getEventname()%></td>
        <td><%=e.getUsername()%></td>
        <td><%=e.getPershkrim()%></td>
        <td><%=e.getData()%></td>
        <td><%=SallaDAO.gjejEmerSalle(e.getSallaID())%></td>
        <td><% out.print("Refuzuar");%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>



<script>
    $(document).ready(function() {
    	
    	//event to edit role user
        $(".check").click(function() {
            var confirmed=confirm("Ju lutem konfirmoni veprimin");
            if(!confirmed)
                return;

            var id = this.id;
            var value=this.value;

            $.ajax({
                url: "../Shqyrto",
                type: "post",
                data: {
                    eventID : id,
                    status: value,
                },
                success : function(data){
                    alert(data); // alerts the response from servlet
                    location.reload();
                }
            });
        });
        
    });

</script>
<script>
var all = document.getElementById("events");
var pending = document.getElementById("events_pending");
var confirmed = document.getElementById("events_confirmed");
var refused = document.getElementById("events_refused");

pending.style.display='none'; 
confirmed.style.display='none'; 
refused.style.display='none'; 

function changeTable() {
  var x = document.getElementById("mySelect").value;
  if(x==0){
	 pending.style.display='table'; 
	 all.style.display='none'; 
	 confirmed.style.display='none'; 
	 refused.style.display='none'; 

  }	 
	else if(x==1){
	 pending.style.display='none'; 
	 all.style.display='none'; 
	 confirmed.style.display='table'; 
	 refused.style.display='none'; }
  	else if(x==-1){
		 pending.style.display='none'; 
		 all.style.display='none'; 
		 confirmed.style.display='none'; 
		 refused.style.display='table';
		 }
	else{
		 pending.style.display='none'; 
		 all.style.display='table'; 
		 confirmed.style.display='none'; 
		 refused.style.display='none'; }
}
	
</script>

</body>
</html>