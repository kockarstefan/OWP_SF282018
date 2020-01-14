<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>

<% User user = (User) request.getAttribute("user"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
</head>
<body>
<table border=1>
<tr>
		<td>
		<%= user.getId() %>
		</td>
		<td>
		 <%= user.getKorisnickoIme() %>
		</td>
		<td>
		 <%= user.getLozinka() %>
		</td>
		<td>
		 <%= user.getDatumRegistacije() %>
		</td>
		<td>
		 <%= user.getRole() %>
		</td>
	</tr>

</table>
</body>
</html>