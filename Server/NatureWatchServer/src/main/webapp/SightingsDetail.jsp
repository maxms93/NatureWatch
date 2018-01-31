<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="at.jku.se.session.*"%>
<%@ page import="at.jku.se.database.*"%>
<%@ page import="at.jku.se.model.*"%>
<% DatabaseConnector db = new DatabaseConnector();%>
<% Sighting sighting = SightingFacade.getSigthingAdmin(db.getConnection(), Long.parseLong(request.getParameter("name")));
   db.close();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beobachtungseintrag Detail</title>
<style>
        	table, th, td
        	{
        		border: 1px solid black;
        		border-collapse: collaps;
        		opacity: 0.95;
        		
        	}
        	
        	th,td,button
        	{
        		padding: 10px;
        		text-align: center;
        	}
        	
        	th,button
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
        	table
        	{
        		width: 50%;
        	}
        </style>
</head>
<body>
<h1 style="font-size:200%;" style="font-family: Arial, Verdana"><u> Detail: </u></h1>

<form name="enableForm" action="EnableAction" method="Post">
    <button name="enableSub" value="<%=sighting.getId()%>" type="submit" onclick="return confirm('Wollen Sie es wirklich Freigeben?')">Freigeben</button>
</form>
<form name="deleteForm" action="DeleteSightingAction" method="Post">
  	<button name="deleteSub" value="<%=sighting.getId()%>" type="submit" onclick="return confirm('Wollen Sie wirklich Löschen?')">Löschen</button>
</form>
<table>
	<tr>
		<td><b>Id: </b></td>
		<td><%=sighting.getId()%></td>
	</tr>
	<tr>
		<td><b>SpeciesId: </b></td>
		<td><%=sighting.getSpeciesId()%></td>
	</tr>
	<tr>
		<td><b>Description: </b></td>
		<td><%=sighting.getDescription()%></td>
	</tr>
	<tr>
		<td><b>Longitude: </b></td>
		<td><%=sighting.getLongitude()%></td>
	</tr>
	<tr>
		<td><b>Latitude: </b></td>
		<td><%=sighting.getLatitude()%></td>
	</tr>
	<tr>
		<td><b>SeaLevel: </b></td>
		<td><%=sighting.getSeaLevel()%></td>
	</tr>
	<tr>
		<td><b>State: </b></td>
		<td><%=sighting.getState()%></td>
	</tr>
	<tr>
		<td><b>Country: </b></td>
		<td><%=sighting.getCountry()%></td>
	</tr>
	<tr>
		<td><b>City: </b></td>
		<td><%=sighting.getCity()%></td>
	</tr>
	<tr>
		<td><b>User: </b></td>
		<td><%=sighting.getUser()%></td>
	</tr>
	<tr>
		<td><b>DateTime: </b></td>
		<td><%=sighting.getDateTime()%></td>
	</tr>
	<tr>
		<td><b>Enabled: </b></td>
		<td><%=sighting.isEnabled()%></td>
	</tr>
	<tr>
		<td><b>Image1: </b></td>
		<td><img src="../images/<%=sighting.getImage1Name()%>" width="400" height="400"></td>
	</tr>
	<tr>
		<td><b>Image2: </b></td>
		<td><img src="../images/<%=sighting.getImage2Name()%>" width="400" height="400"></td>
	</tr>
	<tr>
		<td><b>Image3: </b></td>
		<td><img src="../images/<%=sighting.getImage3Name()%>" width="400" height="400"></td>
	</tr>
	</table>

</body>
</html>