<%@page import="at.jku.se.web.DetailController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="at.jku.se.session.*"%>
<%@ page import="at.jku.se.database.*"%>
<%@ page import="at.jku.se.model.*"%>
<% 
String userName;
String speciesId;
String cityName;
String countryName;
String itemEnabled;
Date dateFrom;
Date dateTo;
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");String strDateFrom;
String strDateTo;
userName = DetailController.getUserName(request, response);
speciesId = DetailController.getSpeciesId(request, response);
cityName = DetailController.getGemeindeName(request, response);
countryName = DetailController.getCountryName(request, response);
itemEnabled = DetailController.getEnabled(request, response);
dateFrom = DetailController.getDateFrom(request, response);
dateTo = DetailController.getDateTo(request, response);
strDateFrom = formatter.format(dateFrom);
strDateTo = formatter.format(dateTo);
%>
<% DatabaseConnector db = new DatabaseConnector();%>
<% List<Species> speciesList = (List<Species>) SpeciesFacade.getSpeciesForAdmin(db.getConnection(), "%"); %>
<% List<Sighting> eList = (List<Sighting>)SightingFacade.getSightingFilterForAdminDate(db.getConnection(), dateFrom, dateTo, "%"+userName+"%", speciesId, "%"+cityName+"%", "%"+countryName+"%", itemEnabled);%>
<% if(userName.equals("%")) userName=""; %>
<% if(cityName.equals("%")) cityName=""; %>
<% if(countryName.equals("%")) countryName=""; %>
<% db.close();%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beobachtungseintrag verwalten</title>
 <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
            	$("#dateF").datepicker({ dateFormat: 'dd/mm/yy' });
            });
        </script>
        <script>
            $(function () {
            	$("#dateT").datepicker({ dateFormat: 'dd/mm/yy' });
            });
        </script>
        
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


<h1 style="font-size:200%;" style="font-family: Arial, Verdana"><u> Beobachtungseintrag freigeben oder löschen: </u></h1>
<form name="form1" method="post" action="DetailAction">
<table>
	<tr>
		<td><b>Erstellt von: </b></td>
		<td><input type="text" id="userName" name="userName" value="<%= userName%>"/></td>
		
		<td><b>Tier-bzw. Pflanzenart: </b></td>
		<td> <select name="speciesItem" >
		  <option value="-"><c:out value="-"/></option>
			<%for(int i=0; i < speciesList.size(); i++){%>
   			 <option value="<%= speciesList.get(i).getSpecies()%>"><c:out value="<%= speciesList.get(i).getSpecies()%>"/></option>
   			 <%} %>
    		 </select></td>
	</tr>
	<tr>
		<td><b>Gemeinde: </b></td>
		<td><input type="text" id="cityName" name="cityName" value="<%= cityName%>"/></td>
		
		<td><b>Staat: </b></td>
		<td><input type="text" id="countryName" name="countryName" value="<%= countryName%>" /></td>
	</tr>
	<tr>
		<td><b>Freigabe: </b></td>
		<td> <select name="itemEnabled">
			 <option value="B">Alle</option>
   			 <option value="Y">Freigegeben</option>
    		 <option value="N">Nicht Freigegeben</option>
    		 </select></td>
    		 
    	<td><label for="dateFrom"></label><b>Datum von: </b></td>
		<td><input type="text" name="datefrom" id="dateF" value="<%= strDateFrom%>"></td>	</tr>

	<tr>
		<td><label for="dateTo"><b>Datum bis: </b></label></td>
		<td><input type="text" name="dateto" id="dateT" value="<%= strDateTo%>"></td>		
		<td> </td>
		<td><button name="filter" value="filter" type="submit">Filtern</button></td>
	</tr>
</table>

<table>
	<tr>
		<th>Select</th>
		<th><h4>ID</h4></th>
		<th><h4>SpeciesId</h4></th>
		<th><h4>Description</h4></th>
		<th><h4>Longitude</h4></th>
		<th><h4>Latitude</h4></th>
		<th><h4>SeaLevel</h4></th>
		<th><h4>State</h4></th>
		<th><h4>Country</h4></th>
		<th><h4>City</h4></th>
		<th><h4>User</h4></th>
		<th><h4>DateTime</h4></th>
		<th><h4>Enabled</h4></th>
		<th><h4>Image1</h4></th>
		<th><h4>Image2</h4></th>
		<th><h4>Image3</h4></th>
		
	</tr>	
		
<%for(int i=0; i < eList.size(); i++){%>
	<tr>
		<td><input type="checkbox" name="selectedItemId" value="<%=eList.get(i).getId()%>"></td>
		<td><%= eList.get(i).getId()%></td>
		<td><%= eList.get(i).getSpeciesId() %></td>
		<td><%= eList.get(i).getDescription() %></td>
		<td><%= eList.get(i).getLongitude() %></td>
		<td><%= eList.get(i).getLatitude() %></td>
		<td><%= eList.get(i).getSeaLevel() %></td>
		<td><%= eList.get(i).getState() %></td>
		<td><%= eList.get(i).getCountry() %></td>
		<td><%= eList.get(i).getCity()  %></td>
		<td><%= eList.get(i).getUser() %></td>
		<td><%= eList.get(i).getDateTime() %></td>
		<td><%= eList.get(i).isEnabled() %></td>
		<td><img src="../images/<%=eList.get(i).getImage1Name()%>" width="40" height="40"></td>
		<td><img src="../images/<%=eList.get(i).getImage2Name()%>" width="40" height="40"></td>
		<td><img src="../images/<%=eList.get(i).getImage3Name()%>" width="40" height="40"></td>
		<td><button name="name" value="<%= eList.get(i).getId() %>" type="submit">Details</button></td>
	</tr>
	<%} %>
	<tr>
		<th><button name="checkEna" value="Freigeben" type="submit" onclick="return confirm('Wollen Sie es wirklich Freigeben?')">Freigeben</button></th>
		<th><button name="checkDel" value="Loeschen" type="submit" onclick="return confirm('Wollen Sie wirklich Löschen?')">Löschen</button></th>
	</tr>
	
</table>
</form>
</body>
</html>