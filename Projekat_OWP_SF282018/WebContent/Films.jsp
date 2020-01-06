<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList"%>  
<%@ page import="model.Films" %>  
    
<% ArrayList<Films> films = (ArrayList) request.getAttribute("films"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border=1>
 <% for(int itFilm=0; itFilm < films.size(); itFilm++) { %>
 	<tr>
		<% Films film = (Films) films.get(itFilm); %>
		<td>
		 <%= film.getNaziv() %>
		</td>
		<td>
		 <%= film.getTrajanjeFilma() %>
		</td>
		<td>
		 <%= film.getDistributer() %>
		</td>
		<td>
		 <%= film.getZemljaPorekla() %>
		</td>
		<td>
		 <%= film.getGodinaProizvodnje() %>
		</td>
	</tr>
 <% } %>
</table>
</body>
</html>