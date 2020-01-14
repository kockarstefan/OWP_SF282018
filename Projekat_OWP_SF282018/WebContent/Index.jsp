<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<form action="LoginServlet" method="post">
	Korisnicko ime:<input type="text" name="korisnickoIme"/><br>
	Lozinka:<input type="password" name="lozinka"/><br>
	<input type="submit" value="Login"/> 
	</form>
	<br>
	<a href="Register.html">Registracija</a>
</body>
</html>