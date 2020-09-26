<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.conferencecenter.daos.AksesoreDAO" %>
<%@ page import="com.conferencecenter.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.conferencecenter.daos.SallaDAO" %>

<html>
<head>
    <title>Aksesore</title>
    <link rel="stylesheet" href="../css/admin.css">
     <link rel="stylesheet" href="../css/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<ul class="menu">
    <li><a href="admin.jsp">Home</a></li>
    <li><a href="evente.jsp">Evente</a></li>
    <li><a href="salla.jsp">Salla</a></li>
    <li><a class="active" href="aksesore.jsp">Aksesore</a></li>
    <li class="active" style="float: right;"><a href="../Logout.jsp" >LogOut</a></li>

</ul>

<%
	ArrayList<AksesoreObj> lista = AksesoreDAO.getAksesore();
%>

<table id="aksesore">
    <thead>
    <tr>
        <th>Aksesore ID</th>
        <th>Aksesor name</th>
        <th>State</th>
        <th>Salle name</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <%
    	for(AksesoreObj a:lista){
    %>

    <tr>
        <td><%=a.getID()%></td>
        <td><%=a.getEmerAksesori()%></td>
        <td><%=a.getGjendjeAksesori()%></td>
        <td>
        <%if ( a.getSallaID()==0 )
        out.print("Magazine");
        else out.print(SallaDAO.gjejEmerSalle(a.getSallaID()));
  		%>
        </td>
        <td><button type="button" id="<%=a.getID()%>" class="myButton delete">Delete</button> </td>

        <td><form action="editaksesor.jsp" method="get">
            <input type="hidden" name="id" value="<%=a.getID()%>">
            <input type="hidden" value="<%=a.getEmerAksesori()%>" name="name">
            <input type="hidden" value="<%=a.getGjendjeAksesori()%>" name="gjendja">
            <input type="hidden" value="<%=a.getSallaID()%>" name="sallaID" >
            <input class="myButton update" type="submit" value="Update">
        </form> </td>

    </tr>
    <%
    	}
    %>
    </tbody>
</table>

<br><br>
<div class="container">
<h2 style=" text-align: center;" >Plus aksesore</h2>
<form  class="form-inline"method="post" action="../AddAks">
    Aksesori name:<input type="text" name="name" required />
    State:<select  name="gjendja">
        <option value="1">1 : Work</option>
        <option value="0">0 : With problem</option>
            </select>
    <%
    	ArrayList<Integer> sallat= SallaDAO.getIDsOfSalla();
    %>
    Salla:<select  name="sallaID">
        <option value="0">Magazine</option>
        <% for (int s:sallat) {
            out.print("<option value=\""+s+"\">"+SallaDAO.gjejEmerSalle(s)+"</option>");
        }%>
    </select>
    <br>

    <input type="submit" value="Add Aksesore"/>
</form>
</div>

<script>
    $(document).ready(function() {
// crating new click event for delete button
        $(".delete").click(function() {
            var confirmed=confirm("Please confirm your choise");
            if(!confirmed)
                return;

            var id = this.id;
            $.ajax({
                url: "../DeleteAks",
                type: "post",
                data: {
                    aksID : id,
                },
                success : function(data){
                    alert(data); // alerts the response from jsp
                    location.reload();
                }
            });
        });
    });

</script>

</body>
</html>
