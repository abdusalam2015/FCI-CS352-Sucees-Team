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
	<p>
	<h1>Group Messages Page</h1>

	</p>

	<c:forEach items="${it.GMSG}" var="GMSG">
		<p>
			${GMSG.email} :
			<c:out value="${GMSG.name}">
			</c:out>
		</p>

	</c:forEach>
	<br>
	<p>Send Message</p>
	
	<form action="/social/groupMessage" method="post">
		GroupName : <input type="text" name="groupName" /> <br> Message
		: <input type="text" name="message" /> <br> <input type="submit"
			value="send">
	</form>
	<p>Add Memmber</p>
	<form action="/social/groupMessage" method="post">
		Group Name : <input type="text" name="groupName" /> <br> 
		 Member : <input type="text" name="memmber" /> <br> 
		 <input type="submit" value="add">
		
	</form>

</body>
</html>

