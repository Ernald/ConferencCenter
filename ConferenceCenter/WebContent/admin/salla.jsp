<%@page import="com.conferencecenter.daos.AksesoreDAO"%>
<%@ page import="com.conferencecenter.daos.SallaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.conferencecenter.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Salla</title>
    <link rel="stylesheet" href="../css/admin.css">
     <link rel="stylesheet" href="../css/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<ul class="menu">
<li><a href="admin.jsp">Home</a></li>
<li><a href="evente.jsp">Evente</a></li>
<li><a class="active" href="salla.jsp">Salla</a></li>
<li><a href="aksesore.jsp">Aksesore</a></li>
<li class="active" style="float: right;"><a href="../Logout.jsp" >LogOut</a></li>

</ul>

<% ArrayList<SallaObj> lista = SallaDAO.getSalla(); %>


<table id="salla">
    <thead>
    <tr>
    	<th>Salla ID</th>
        <th>Emer Salle</th>
        <th>Kapaciteti</th>
        <th>Kosto ditore</th>
        <th>Aksesore</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for(int i = 0; i < lista.size(); i++) {
        SallaObj s = lista.get(i); %>

    <tr> 
  	    <td><%=s.getId()%></td>
        <td><%=s.getEmerSalle()%></td>
        <td><%=s.getNumerVendesh()%></td>
        <td><%=s.getKostoDitore()%></td>
        <td>
            <ol type="1">

                <%for (int j=0;j<s.getAksesoreList().size();j++){
                    AksesoreObj a = s.getAksesoreList().get(j); %>

                <li>
                    <ul>
                        <li>ID: <%=a.getID() %></li>
                        <li>Emer: <%=a.getEmerAksesori() %></li>
                        <li>Gjendja: <%=a.getGjendjeAksesori() %></li>

                    </ul>
                </li>
                <% }; %>
            </ol>

        </td>
        <td><button type="button" id="<%=s.getId()%>" class="myButton delete">Delete</button> </td>

        <td>
        <form action="editsalla.jsp" method="post">
            <input type="hidden" value="<%=s.getId()%>" name="sallaID">
            <input type="hidden" value="<%=s.getEmerSalle()%>" name="name">
            <input type="hidden" value="<%=s.getNumerVendesh()%>" name="kapacitet">
            <input type="hidden" value="<%=s.getKostoDitore()%>" name="kosto" >
            <input class="myButton update" type="submit" value="Update">
        </form> </td>

    </tr>  <% } %>
    </tbody>
</table>

    <br><br>
    <div class="container">
    <h2 style=" text-align: center;" >Shto salle</h2>
    <form  action="../addSalla" method="post">
   	 <label>Emer Salle:</label>
        <input type="text" required name="name">
      <label>Kapaciteti:</label> 
        <input type="number" min=0 required name="kapacitet">
       <label>Kosto ditore:</label>
        <input type="number" min=0 step="0.001" required name="kosto">
        
        <% ArrayList<AksesoreObj> listaA = AksesoreDAO.getFreeAksesore();
        
        if(listaA.size()>0)
        	out.print("<label>Zgjidh Aksesore:</label><br><br> ");

        for(AksesoreObj a:listaA) {%>
         <input type="checkbox" name="<%=a.getID()%>">
 		 <label>ID <%=a.getID()%> <%=a.getEmerAksesori()%></label>
        <%} %>
        <br><br>
        <input type="submit" value="Add Salla">

    </form>
    </div>


<script>
    $(document).ready(function() {
// crating new click event for delete button
        $(".delete").click(function() {
            var confirmed=confirm("Ju lutem konfirmoni veprimin");
            if(!confirmed)
                return;

            var id = this.id;
            $.ajax({
                url: "../DeleteSalla",
                type: "post",
                data: {
                    sallaID : id,
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