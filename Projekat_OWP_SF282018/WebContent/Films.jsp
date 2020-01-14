<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>  
<%@ page import="model.Films" %>  
    
<% ArrayList<Films> films = (ArrayList) request.getAttribute("films"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Films</title>
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
		<td>
		<form action="FilmServlet" method="post">
		<input type="hidden" name="id" value="<%= films.indexOf(film) %>"/>
		<input type="submit" name="action" value="Ukloni"/>
		</form>
		</td>
	</tr>
 <% } %>
</table>
<br>
<form action="AddFilm.html">
	<input type="submit" value="Dodaj film"/>
</form>
<p>
<a href="UserServlet">Prikazi sve korisnike</a>
</p>
</body>
</html>