<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/index_style.css">
<title>CinemaPolis</title>
</head>
	

<body>



<div class="login-container">
	<form action="login" class="form-login" method="post" id="loginform">
		<ul class="login-nav">
			<li class="login-nav__item active">
				<a >Sign In</a>
				<a href="register.jsp">Register</a>
			</li>
		</ul>
		<label for="login-input-user" class="login__label">
			Username
		</label>
		<input id="login-input-user" class="login__input" type="text" name="username" value="${usern}"/>
		<label for="login-input-password" class="login__label">
			Password
		</label>
		<input id="login-input-password" class="login__input" type="password" name="password"/>

		<p style="color:red">${result}</p>


		<button type="submit" class="login__submit" form="loginform">Sign in</button>
	</form>
</div>






<% 
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if ( !( session.getAttribute("username") == null) ){
   
	response.sendRedirect("succlogin.jsp");
 }  %>

	
</body>


</html>