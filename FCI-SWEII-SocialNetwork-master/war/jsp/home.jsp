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
#div1 {
	background-color: cyan;
	height: 1080px;
	width: 1000px;
	margin-right: auto;
	margin-left: auto;
}

#a1 {
	position: relative;
	margin-right: auto;
	margin-left: 900px;
	background-color: red;
}

#a2 {
	margin-left: 9px; //
	background-color: red;
	margin-top: 1cm;
}

#input1 {
	margin-left: 110px; //
	margin-top: 0.5cm;
}

#searchform {
	position: relative;
	margin-right: auto;
	margin-left: 160px;
	color: red;
}

#msgdiv {
	position: relative;
	margin-right: auto;
	background-color: #F000;
	float: right;
	margin-top: 20px;
	height: auto;
	width: auto;
}

#groudiv {
	position: relative;
	margin-right: auto;
	background-color: #F000;
	float: left;
	margin-top: 20px;
	height: auto;
	width: auto;
}
</style>
<body>

	<a id="a2" href="/social/postPage">writePost</a>
	<div id="div1">
		<a id="a1" href="/social/login/">Log out </a> <br>

		<form id="searchform" action="/social/userspage" method="post">
			Name : <input type="text" name="uname" size="55"
				placeholder="Search User " /> <input type="submit" value="Search">

		</form>


		<form id="searchform" action="/social/pagesPage" method="post">
			PageName : <input type="text" name="pName" size="55"
				placeholder="Search User " /> <input type="submit" value="Search">

		</form>
		<c:forEach items="${it.friends}" var="user">

			<a id="a2" href="/social/userspage">${user.name}</a>

			<form action="/social/response" method="post">
				<input id="input1" type="submit" value="Accept">

			</form>


		</c:forEach>
		<br>



		<c:forEach items="${it.post}" var="post">
			<p>
				<c:out value="${post.name} "></c:out>
			</p>
		</c:forEach>


		<div id="msgdiv">
			<c:forEach items="${it.messages}" var="MSG">
				<p>
					<c:out value="${MSG.name} send to you ${MSG.email} messages "></c:out>
				</p>
			</c:forEach>


			<form action="/social/message" method="post">
				Name : <input type="text" name="name" /> <input type="submit"
					value="see messages">
			</form>

			<form action="/social/message" method="post">
				<input type="submit" value="send Message">
			</form>
			<br>


			<c:forEach items="${it.GMSG}" var="GMSG">
				<p>
					<c:out value="${GMSG.email} message in  ${GMSG.name} group "></c:out>
				</p>
			</c:forEach>
		</div>
		<br>
		<div id="groudiv">

			Go to Group Page click
			<form action="/social/groupMessage" method="post">
				<input type="submit" value="here">
			</form>
			<br>

			<p>Create page</p>
			<form action="/social/pagesPage" method="post">
				page Name :<input type="text" name="pageName" /> <input
					type="submit" value="Create">
			</form>

			<br>

			<p>Create Group</p>
			<form action="/social/groupMessage" method="post">
				Group Name :<input type="text" name="groupName" /> <input
					type="submit" value="Create">
			</form>
		</div>






	</div>
</body>
</html>
