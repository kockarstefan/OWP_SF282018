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
		<form action="FilmServlet" method="post">
		<input type="hidden" name="action" value="showFilm">
		<input type="submit" name="naziv" value="<%= film.getNaziv() %>"/> 
		</form>
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
		<td>
		<form action="EditFilm.jsp" method="post">
		<input type="hidden" name="id" value="<%= films.indexOf(film)+1 %>"/>
		<input type="submit" name="action" value="Izmeni"/>
 		</form>
		</td>
	</tr>
 <% } %>
</table>
<br>
<form action="AddFilm.html">
	<input type="submit" value="Dodaj film"/>
</form>
</body>
</html>