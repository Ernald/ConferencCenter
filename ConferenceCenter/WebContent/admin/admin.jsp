<%@ page import="com.conferencecenter.UserObj" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.conferencecenter.daos.UserDAO" %>
<%@ page import="com.conferencecenter.*" %>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="../css/admin.css">
    <link rel="stylesheet" href="../css/form.css">
    
</head>
<body>
<ul class="menu">
    <li><a class="active" href="admin.jsp">Home</a></li>
    <li><a href="evente.jsp">Evente</a></li>
    <li><a href="salla.jsp">Salla</a></li>
    <li><a href="aksesore.jsp">Aksesore</a></li>
    <li  style="background-color: #4CAF50;border-color: black;float: right;"><a href="../Logout.jsp" >LogOut</a></li>

</ul>
<%
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  String date = sdf.format(new Date());
  UserObj user = (UserObj) session.getAttribute("currentSessionUser");
  ArrayList<UserObj> list = UserDAO.getUsers();
%>

<div style=" text-align: center;">
<h2>Welcome admin <%=user.getUsername()%></h2>
<p>Time: <%=date%></p>
</div>
<table id="users">
    <thead>
    <tr>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Status</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <% for (UserObj u:list) {%>

    <tr>
        <td><%=u.getUsername()%></td>
        <td><%=u.getFirstName()%></td>
        <td><%=u.getLastName()%></td>
        <td><%=u.getEmail()%></td>
        <td><%if (u.getRolID()==1)
                out.print("Admin");
              else
                 out.print("User");%></td>
         <td><%if (u.getStatus()==1)
                out.print("Active");
              else
                 out.print("Disabled");%></td>
         <%if(!((UserObj)session.getAttribute("currentSessionUser")).getUsername().equals(u.getUsername())){%>
        <td><button type="button" id="<%=u.getUsername()%>" class="myButton delete status">Change Status</button> </td>
        <td><button type="button" id="<%=u.getUsername()%>" class="myButton ok rol">Change rol</button></td>
		<% }
		
		else out.print("<td></td> <td></td>"); %>
    </tr>
    <%
    }
    %>
    </tbody>
</table>



<script>
    $(document).ready(function() {
// crating new click event for Ndrysho Status button
        $(".status").click(function() {
            var confirmed=confirm("Please confirm your choise");
            if(!confirmed)
                return;

            var id = this.id;
            $.ajax({
                url: "../NdryshoStatus",
                type: "post",
                data: {
                    usern : id,
                },
                success : function(data){
                    alert(data); // alerts the response from jsp
                    location.reload();
                }
            });
        });
        //event to edit role user
        $(".rol").click(function() {
            var confirmed=confirm("Please confirm your choise");
            if(!confirmed)
                return;

            var id = this.id;
            $.ajax({
                url: "../NdryshoRol",
                type: "post",
                data: {
                    usern : id,
                },
                success : function(data){
                    alert(data); // alerts the response from jsp
                    location.reload();
                }
            });
        });
    });

</script>
<script src="js/admin.js"></script>
</body>
</html>