<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.User"%>
    
<% ArrayList<User> usersList = (ArrayList<User>) request.getAttribute("usersList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
</head>
<body>
<table border=1>
 <% for(int itUser=0; itUser < usersList.size(); itUser++) { %>
 	<tr>
		<% User user = (User) usersList.get(itUser); %>
		<td>
		<%=user.getId() %>
		</td>
		<td>
		<form action="UserServlet" method="post">
		<input type="hidden" name="action" value="showUser">
		<input type="submit" name="korisnickoIme" value="<%= user.getKorisnickoIme() %>"/> 
		</form>
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
	<% } %>
</table>
</body>
</html>