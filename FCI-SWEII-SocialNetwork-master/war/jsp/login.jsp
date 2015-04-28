<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here herer</title>
<style>
#pagediv {
	background-color: green;
	height: 400px;
	width: 800px;
	margin-right: auto;
	margin-left: auto;
	background: url("home.PNG") repeat; //
	opacity: 0.7;
}

#logindiv {
	padding: 20px;
	margin-right: 300px;
	margin-left: 300px;
	border: 1px;
}
</style>
</head>
<body>
	<div id="pagediv">
		<div id="logindiv">
			<form action="/social/home" method="post">
				Name : <input type="text" name="uname"
					style="width: 300px; height: 35px; border: 1" /> <br> <br>
				Password:<input type="password" name="password"
					style="width: 300px; height: 35px; border: 1" /> <br> <br>
				<input type="submit" value="Login"
					style="color: red; width: 70px; height: 42px; length: 40px; border: 1">
			</form>
		</div>


	</div>


</body>
</html>