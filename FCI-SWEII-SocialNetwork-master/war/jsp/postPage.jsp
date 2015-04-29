<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Social Network</title>
</head>
<body>
 
			
				<form action="/social/postPage" method="post">
			<input type="text" name="name" size="55"
				placeholder="Write something... " /> <input type="submit" value="post">
		</form>
		
			<form action="/social/postPage" method="post">
			<input type="text" name="privacy" size="55"
				placeholder="Write something... " /> <input type="submit" value="privacyPost">
		</form>
		
		<form action="/social/postPage" method="post">
			<input type="text" name="share" size="55"
				placeholder="copy Post ... " /> <input type="submit" value="share">
		</form>
	
		
		
		
		<c:forEach items="${it.hashTag}" var="hashtag">
			<p>
				<a id="a2" href="/social/postPage">${hashtag.email} </a> <br>
				<c:out value="${hashtag.name}"></c:out>
			</p>
			</c:forEach>
		
		<c:forEach items="${it.post}" var="p">
		<p>
				<c:out value="${p.name}"></c:out>
			</p> 
		</c:forEach>
		
		
</body>
</html>