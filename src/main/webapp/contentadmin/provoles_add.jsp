<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>

<!DOCTYPE html>
<html>
<head>
  
<meta charset="ISO-8859-1">
  <title>Add Movie to Database</title>
  <link rel="stylesheet" href="../css/addProvoli.css">
</head>
<body>
<%
if(session.getAttribute("username")==null){
	response.sendRedirect("../log-in.jsp");
}else{
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	String typestring = session.getAttribute("type").toString();
	int type_int = Integer.valueOf(typestring);
	if( type_int!=1 ){
		response.sendRedirect("../log-in.jsp");
	}
}




%>
  <div class="film-container">
	<form action="add_provoli" method="POST" class="form-film">
		<ul class="film-nav">
			
			<li class="film-nav__item">
				<a>Add a provoli to the database</a>
			</li>
		</ul>
		
		<label for="title" class="film__label">
			MOVIE
		</label>
		<select name="title" id="movieres" class="dropdown_main">
		<%

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
        try {
       	    
       	 String query = "select TITLE,ID from cinema_last.films ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		request.setAttribute("result", "No user found.");
    	}else {
    		int num=1;
    		String title = rs.getString("TITLE");
			
			int ID = rs.getInt("ID");
			
			 out.println(" <option value="+ID+">"+title+"</option>");
    		while(rs.next()){
    			 num++;
    			 title = rs.getString("TITLE");
    			 ID = rs.getInt("ID");
    			 out.println(" <option value="+ID+">"+title+"</option>");    			
    		}
    	
    	}
             	
       	   
       	} catch (Exception e) {
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
		
		
       
       </select>
       
       <label for="cinema" class="film__label">
			CINEMA
		</label>
		
		<select name="cinema" id="movieres" class="dropdown_main">
		<%

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
        try {
       	    
       	 String query = "select ID from cinema_last.cinemas ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		request.setAttribute("result", "No user found.");
    	}else {
    		
    	
			
			String ID = rs.getString("ID");
			
			 out.println(" <option value="+ID+">"+ID+"</option>");
    		while(rs.next()){
    			
    			
    			 ID = rs.getString("ID");
    			 out.println(" <option value="+ID+">"+ID+"</option>");    			
    		}
    	
    	}
             	
       	   
       	} catch (Exception e) {
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
		
		
       
       </select>
		
		
		<label for="film-input-password" class="film__label">
			PREMIER
		</label>
		<input id="premier" name="premier" required class="film__input" type="datetime-local"/>
		
	
	<p style="color:red">${result}</p>
	
		
		
		<button class="film__submit"  type='submit'>SUBMIT</button>
	</form>
</div>
</body>
</html>