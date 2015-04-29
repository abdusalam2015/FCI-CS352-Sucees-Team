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
<style>
#maindiv {
	height: 600px;
	width: 800px;
	margin-right: auto;
	margin-left: auto;
	background-image: url("download.jpg"); //
	background-repeat: no-repeat; //
	background-repeat: repeat-x; //
	background: url("download.jpg") repeat;
	opacity: 0.7;
}

#sendmsgdiv {
	height: auto;
	width: 400px;
	float: left;
	background: url("chat1.jpg") repeat;
}

#vewurmssgs {
	height: auto;
	width: 400px;
	float: right;
	background: url("chat1.jpg") repeat;
}
</style>
<body>
	<div id="maindiv">
		<p>
		<h1>Messages</h1>

		</p>
		<c:forEach items="${it.messages}" var="messages">
			<p>
				${messages.pass} :
				<c:out value="${messages.name}">
				</c:out>
			</p>
		</c:forEach>

		<div id="sendmsgdiv">
			<fieldset>
				<legend>Sending Message</legend>
				<form action="/social/message" method="post">
					Name : <input type="text" name="name" /> <br> Message :
					<textarea rows="20" cols="50" name="message"> </textarea>
					<input type="submit" value="Send"
						style="color: blue; width: 50px; height: 40px; border: 1" />
				</form>
			</fieldset>
		</div>
		<div id="vewurmssgs">
			<a href="/social/message/"
				style="color: red; height: 35px; border: 1">Refresh</a> <br>
		</div>

	</div>
</body>
</html>