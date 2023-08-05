<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Succesfull Login</title>
</head>
<body>

<%

response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

if(session.getAttribute("username")==null){
	response.sendRedirect("main.jsp");
}else{
	if(Integer.valueOf(session.getAttribute("type").toString())==0){
		response.sendRedirect("admin/main.jsp");
	}else if(Integer.valueOf(session.getAttribute("type").toString())==1){
		response.sendRedirect("contentadmin/main.jsp");
	}else if(Integer.valueOf(session.getAttribute("type").toString())==2){
		response.sendRedirect("client/main.jsp");
	}
}

%>

</body>
</html>