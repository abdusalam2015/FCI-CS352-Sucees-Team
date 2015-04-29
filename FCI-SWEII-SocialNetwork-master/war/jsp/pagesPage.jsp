<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>

</head>

<body>
	<h1>${pageName.name}</h1>
	<form action="/social/pagesPage" method="post">

		<input type="submit" value="like"
			style="color: blue; width: 50px; height: 40px; border: 1" />
	</form>



</body>
</html>