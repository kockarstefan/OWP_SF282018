<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Film" %>
    
<%Film film = (Film) request.getAttribute("film"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film</title>
</head>
<body>
<table border=1>
<tr>
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

</table>
</body>
</html>