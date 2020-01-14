<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit film</title>
</head>
<body>
	<form action="FilmServlet" method="post">
	<input type="hidden" name="action" value="izmeni"/>
	filmID:<input type="text" name="id" value="<%= request.getParameter("id") %>"/><br>
	reziser:<input type="text" name="reziser"/><br>
	naziv:<input type="text" name="naziv"/><br>
	trajanje:<input type="text" name="trajanje"/><br>
	distributer:<input type="text" name="distributer"/><br>
	zemlja porekla:<input type="text" name="zemljaPorekla"/><br>
	godina proizvodnje:<input type="text" name="godinaProizvodnje"/><br>
	<input type="submit" value="Izmeni">
	</form>
</body>
</html>