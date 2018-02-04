<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tier/Pflanzenart einfügen</title>



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
		<li><a href="<%=request.getContextPath()%>/ViewAddAnimalAction">Tierarten einfÃ¼gen</a></li>
		<li><a href="<%=request.getContextPath()%>/ViewSpeciesListAction">Tierarten verwalten</a></li>
	</ul>



<h1>Eingabe von Daten:</h1>
<form action="saveAction" method="POST" enctype="multipart/form-data">
<table>
  <tr><td>ID:</td>  <td> <input type="text" size=50 name="id" value="<%if(request.getParameter("id")!=null){out.println(request.getParameter("id"));}%>" > <span> ${errmsgID} </span></td> </tr>
  <tr><td>Gattung: </td> <td><input type="text" size=50 name="species" value="<%if(request.getParameter("species")!=null){out.println(request.getParameter("species"));}%>"><span> ${errmsgSpecies} </span></td></tr>
  <tr><td>Art:  </td> <td> <input type="text" size=50 name="category" value="<%if(request.getParameter("category")!=null){out.println(request.getParameter("category"));}%>"><span> ${errmsgCategory} </span></td></tr>
  <tr><td>lateinischer Name:</td> <td><input type="text" size=50 name="latinname" value="<%if(request.getParameter("latinname")!=null){out.println(request.getParameter("latinname"));}%>"><span> ${errmsgLatinname} </span></td></tr>
  <tr><td> umgangsspr. Name:</td> <td><input type="text" size=50 name="normalname" value="<%if(request.getParameter("normalname")!=null){out.println(request.getParameter("normalname"));}%>"><span> ${errmsgNormalname} </span></td></tr>
  <tr><td>Beschreibung: </td> <td><textarea name="description" rows="4" cols="50" ><%if(request.getParameter("description")!=null){out.println(request.getParameter("description"));}%></textarea><span> ${errmsgDescription} </span></td></tr>
  <tr><td> von Monat:</td> <td><input type="text" size=50 name="validfrom" value="<%if(request.getParameter("validfrom")!=null){out.println(request.getParameter("validfrom"));}%>"><span> ${errmsgValidfrom} </span></td></tr>
  <tr><td> bis Monat:</td> <td><input type="text" size=50 name="validto" value="<%if(request.getParameter("validto")!=null){out.println(request.getParameter("validto"));}%>"><span> ${errmsgValidto} </span></td></tr>
  <tr><td>Bild 1:</td> <td><input type="file"  name="bild1"  size="50" /></td></tr>
  <tr><td>Bild 2:</td> <td><input type="file"  name="bild2"  size="50" /></td></tr>
  <tr><td>Bild 3:</td> <td><input type="file"  name="bild3"  size="50" /></td></tr>
  <tr><td>Bild 4:</td> <td><input type="file"  name="bild4"  size="50" /></td></tr>
  <tr><td>Bild 5:</td> <td><input type="file"  name="bild5"  size="50" /></td></tr>
  <tr><td></td> <td><input type="submit" value="Speichern"></td></tr>
</table>
</form>
</body>
</html>