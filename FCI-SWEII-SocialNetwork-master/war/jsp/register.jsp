<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome!</title>
</head>
<style>
#maindiv
{
height:400px;
width:800px;
margin-right:auto;
margin-left:auto;
background:url("home.PNG") repeat;
}
#rigstrdiv
{
padding:20px;
margin-right:300px;
margin-left:300px;
border:1px;

}
</style>
<body>
	<div id="maindiv">
	 
	  <div id="rigstrdiv">
	 
		<form action="/social/response" method="post">
			Name : <input type="text" name="uname" style="width:300px;height:35px;border:1 "/> <br> 
			Email : <input type="text" name="email" style="width:300px;height:35px;border:1"/> <br> 
			Password : <input type="password" name="password" style="width:300px;height:35px;border:1"/> <br> 
			<input type="submit" value="Register" style="color:red;width:70px;height:42px;length:40px;border:1"/>
		</form>
	 </div>
	 
	</div>
</body>
</html>