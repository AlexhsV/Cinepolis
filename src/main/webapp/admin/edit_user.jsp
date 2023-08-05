<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/addMovie.css">


<title>Insert title here</title>
</head>
<body>

<%
if(session.getAttribute("username")==null){
	response.sendRedirect("log-in.jsp");
}else{
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	String typestring = session.getAttribute("type").toString();
	int type_int = Integer.valueOf(typestring);
	if( type_int!=0 ){
		response.sendRedirect("log-in.jsp");
	}
}




%>

<%
String username = request.getAttribute("username").toString();

System.out.println("username is :"+username);
pageContext.setAttribute("usrname" ,username);

String password = request.getAttribute("password").toString();
pageContext.setAttribute("password" ,password);


try {

	String RESOURCE_NAME = "jdbc/cinema_last";
	 System.out.println("in");
   Class.forName("com.mysql.jdbc.Driver");  
   Context initContext = new InitialContext();
   Context envContext = (Context) initContext.lookup("java:/comp/env");
   
   DataSource ds = (DataSource) envContext.lookup(RESOURCE_NAME);
   Connection conn = ds.getConnection();
    if(conn!=null)
    {
        System.out.println("success");
     
       
        try {
       
       	 
       	
       
     
       	    
       	 String query = "select username,type,DATE_OF_BIRTH from cinema_last.user where username='"+username+"' ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		 response.sendRedirect("error.jsp");
    	
    	}else {
    		int num=1;
    		String type = rs.getString("type");
    		try{
    			Date birthday = rs.getDate("DATE_OF_BIRTH");
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    			
    			String formatted_premier = sdf.format(birthday);
    			pageContext.setAttribute("birthday",formatted_premier); 
    			boolean isClient=true;

    			 System.out.println(isClient);
    		}catch (Exception e){
    			
    			boolean isClient=false;

                System.out.println(isClient);
    		}
				
		
    		
			pageContext.setAttribute("type",type);
			
			
				 
    		}
    		
    	
    	
    	}
        catch (Exception e) {
     
       	    e.printStackTrace();
     
       	    response.sendRedirect("error.jsp");
       	}
    }
    
    conn.close();
    
}catch (Exception e){
    System.out.println("nope");
    System.out.println(e.toString());
}










%>

 <div class="film-container">
	<form  id ="edit_form" action="edit_user" method="POST" class="form-film">
	
	
		<ul class="film-nav">
			
			<li class="film-nav__item">
				<a>Edit User </a>
			</li>
		</ul>
		<input name="username"  value="${username}" type="hidden"/>
		<label for="film-input-username" class="film__label">
    Username
</label>
<input id="film-input-username" name="usernamen" required class="film__input" type="text" value="${username}" />

<label for="film-input-password" class="film__label">
    Password
</label>
<input id="film-input-password" name="password" required class="film__input" type="text" value="${password}" />

<label for="film-input-rpassword" class="film__label">
    Re Enter Password
</label>
<input id="film-input-rpassword" name="rpassword" required class="film__input" type="text" value="${password}" />

<p style="color:red">${result}</p>

<button class="film__submit" type="submit" form="edit_form" >SUBMIT</button>
<button class="film__submit" type="submit" form="delete_form" >DELETE</button>

		
	</form>
			<input name="create" type="hidden" value="e">
	
<form id="delete_form" action="delete_user" method="POST" class="form-film">
<input name="idnumber"  value="${username}" type="hidden"/>
</form>
</body>
</html>