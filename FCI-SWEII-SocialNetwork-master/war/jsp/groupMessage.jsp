<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
<style>
#maindiv {
	height: 600px;
	width: 800px;
	margin-right: auto;
	margin-left: auto;
	background: url("chat.jpg") repeat;
	opacity: 0.7;
}

#sendmsgdiv {
	height: 200px;
	width: 400px;
	float: left;
	background: url("chat1.jpg") repeat;
}

#addmembrdiv {
	height: 400px;
	width: 400px;
	float: right; //
	margin-left: 10px;
	background: url("chat1.jpg") repeat;
}
</style>
</head>
<body>

	<div id="maindiv">
		<p>
		<h1>Group Messages Page</h1>

		</p>
		<div id="sendmsgdiv">
			<c:forEach items="${it.GMSG}" var="GMSG">
				<p>
					${GMSG.email} :
					<c:out value="${GMSG.name}">
					</c:out>
				</p>

			</c:forEach>
			<br>

			<fieldset>
				<legend>Sending Message</legend>

				<form action="/social/groupMessage" method="post">
					GroupName : <input type="text" name="groupName" /> <br> <br>
					Message :
					<textarea rows="20" cols="50" name="message">
           </textarea>
					<input type="submit" value="Send"
						style="color: blue; width: 50px; height: 40px; border: 1" />

				</form>
			</fieldset>
		</div>

		<div id="addmembrdiv">
			<fieldset>
				<legend>Add New Member</legend>
				<form action="/social/groupMessage" method="post">
					Group Name : <input type="text" name="groupName" /> <br> <br>
					<br> Member : <input type="text" name="memmber" /> <br>
					<br> <input type="submit" value="Add">

				</form>
			</fieldset>
		</div>

	</div>
</body>
</html>