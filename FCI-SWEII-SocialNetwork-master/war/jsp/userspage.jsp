<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here mmm</title>
</head>
<style>
#maindiv {
	height: 400px;
	width: 800px;
	margin-right: auto;
	margin-left: auto;
	background: url("home.PNG") repeat;
}

#subdiv {
	padding: 20px;
	margin-right: 300px;
	margin-left: 300px;
	border: 1px;
}
</style>
<body>
	<div id="maindiv">
		<p>
		<h1>${it.name}</h1>
		</p>

		<div id="subdiv">

			<p>Email : ${it.email}</p>
			<form action="/social/home" method="post">
				<input type="submit" value="Add freind"
					style="color: red; width: 90px; height: 42px; length: 40px; border: 1" />
			</form>
		</div>
	</div>
</body>
</html>