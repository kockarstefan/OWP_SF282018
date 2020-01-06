<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Film" %>
    
<%Film film = (Film) request.getAttribute("film"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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