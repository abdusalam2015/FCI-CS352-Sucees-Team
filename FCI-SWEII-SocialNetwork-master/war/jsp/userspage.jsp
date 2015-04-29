<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

			<form action="/social/home" method="post">
				<input type="submit" value="Add freind"
					style="color: red; width: 90px; height: 42px; length: 40px; border: 1" />


			</form>
		</div>

		<c:forEach items="${it.timeline}" var="TL">
			<p>
				Name :
				<c:out value="${TL.name}"></c:out>
				<br> Email :
				<c:out value="${TL.email}"></c:out>
				<br> Live :
				<c:out value="${TL.live}">
				</c:out>
				<br> Student :
				<c:out value="${TL.student}">
				</c:out>
				<br> From :
				<c:out value="${TL.nationality}">
				</c:out>
				<br> BirthDate :
				<c:out value="${TL.birthdate}">
				</c:out>
			</p>

		</c:forEach>


		<br>


	</div>
</body>
</html>