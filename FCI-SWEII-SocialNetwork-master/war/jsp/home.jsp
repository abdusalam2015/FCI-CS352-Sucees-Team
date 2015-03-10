<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>

	<p>Welcome ${it.name}</p>

	<p>Search here !</p>
	<form action="/social/userspage" method="post">
		Name : <input type="text" name="uname" /> <br> <input
			type="submit" value="Enter">
	</form>

	<form action="/social/response" method="post">
		<p>number of notification: ${it.uname}</p>
		<input type="submit" value="Accept">
	</form>

	<br>
	<a href="/social/login/">Log out </a>
	<br>
</body>
</html>
