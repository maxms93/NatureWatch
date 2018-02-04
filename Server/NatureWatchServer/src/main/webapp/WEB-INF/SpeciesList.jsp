<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="at.jku.se.session.*"%>
<%@ page import="at.jku.se.database.*"%>
<%@ page import="at.jku.se.model.*"%>
<% DatabaseConnector db = new DatabaseConnector();%>
<% List<Species> speciesList = (List<Species>)SpeciesFacade.getSpeciesForAdmin(db.getConnection(), "%");%>
<% db.close(); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tier- bzw. Pflanzenarten verwalten</title>

<style>
        	table, th, td
        	{
        		border: 1px solid black;
        		border-collapse: collaps;
        		opacity: 0.95;
        		
        	}
        	
        	th,td
        	{
        		padding: 10px;
        		text-align: center;
        	}
        	
        	th
        	{
        		background-color: #a70000; 
        		color: white;
        	}
        	
        	tr:nth-child(even)
        	{
        		background-color: #e8e8e8;
        	}
        	tr:nth-child(odd)
        	{
        		background-color: white;
        	}
        	
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	display: inline;
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: #111;
}
        </style>
</head>
<body  bgcolor="#FAFAFA">

	<ul>
		<li><a href="<%=request.getContextPath()%>/ViewSightingsAction">Beobachtungen</a></li>
		<li><a href="<%=request.getContextPath()%>/ViewAddAnimalAction">Tierarten einfügen</a></li>
		<li><a href="<%=request.getContextPath()%>/ViewSpeciesListAction">Tierarten verwalten</a></li>
	</ul>

<h1 style="font-size:200%;" style="font-family: Arial, Verdana"><u> Tier- und Pflanzenartenliste verwalten </u></h1>

<form name="form2" method="post" action="DelSpecAction">
<table>
	<tr>
		<th><h4>ID</h4></th>
		<th><h4>Tier bzw. Pflanzenart</h4></th>
		<th><h4>Höhere Kategorie</h4></th>
		<th><h4>Lateinischer Name</h4></th>
		<th><h4>Umgangssprachlicher Name</h4></th>
		<th><h4>Beschreibung</h4></th>
		<th><h4>Von</h4></th>
		<th><h4>Bis</h4></th>	
		<th><h4>Bild 1</h4></th>
		<th><h4>Bild 2</h4></th>
		<th><h4>Bild 3</h4></th>
		<th><h4>Bild 4</h4></th>
		<th><h4>Bild 5</h4></th>	
	</tr>	
		
<%for(int i=0; i < speciesList.size(); i++){%>
	<tr>
		<td><%= speciesList.get(i).getId()%></td>
		<td><%= speciesList.get(i).getSpecies()%></td>
		<td><%= speciesList.get(i).getCategory() %></td>
		<td><%= speciesList.get(i).getLatinName() %></td>
		<td><%= speciesList.get(i).getNormalName() %></td>
		<td><%= speciesList.get(i).getDescription() %></td>
		<td><%= speciesList.get(i).getValidFrom() %></td>
		<td><%= speciesList.get(i).getValidTo() %></td>
		<td><img src="../images/<%=speciesList.get(i).getImage1Name()%>" width="400" height="400"></td>
		<td><img src="../images/<%=speciesList.get(i).getImage2Name()%>" width="400" height="400"></td>
		<td><img src="../images/<%=speciesList.get(i).getImage3Name()%>" width="400" height="400"></td>
		<td><img src="../images/<%=speciesList.get(i).getImage4Name()%>" width="400" height="400"></td>
		<td><img src="../images/<%=speciesList.get(i).getImage5Name()%>" width="400" height="400"></td>
		<td><button name="delSpec" value="<%= speciesList.get(i).getId() %>" type="submit" onclick="return confirm('Wollen Sie es wirklich Löschen?')">Löschen</button></td>
	</tr>
	<%} %>
</table>
</form>
</body>
</html>