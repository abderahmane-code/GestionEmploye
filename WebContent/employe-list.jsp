<%@ page import="java.util.*,model.Employe" %>

<h2>Liste des employés</h2>

<a href="employe-form.jsp">Ajouter Employé</a>

<table border="1">

<tr>
<th>ID</th>
<th>Nom</th>
<th>Prénom</th>
<th>Poste</th>
<th>Salaire</th>
</tr>

<%

List<Employe> list=(List<Employe>)request.getAttribute("listeEmployes");

for(Employe e:list){

%>

<tr>

<td><%=e.getId()%></td>

<td><%=e.getNom()%></td>

<td><%=e.getPrenom()%></td>

<td><%=e.getPoste()%></td>

<td><%=e.getSalaire()%></td>

</tr>

<%
}
%>

</table>