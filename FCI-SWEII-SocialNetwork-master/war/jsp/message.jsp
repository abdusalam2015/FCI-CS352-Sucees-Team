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
	<h1> messages</h1>
	</p>
    <c:forEach items="${it.messages}" var = "messages">
	   <p>   ${messages.pass} :<c:out  value="${messages.name}"> </c:out></p>
    </c:forEach>
    
	<form action="/social/message" method="post">
		  name : <input type="text" name="name" /> <br>
		  message : <input type="text" name="message" /> <br>
		   <input type="submit" value="send">
	</form>
	
 
	<FORM>
please click here to see your MSG :
<INPUT TYPE="button" onClick="history.go(0)" VALUE="Refresh">
</FORM>
	
</body>
</html>

