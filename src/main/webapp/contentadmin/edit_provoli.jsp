<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.concurrent.TimeUnit" %>
    <%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/addProvoli.css">
<title>Insert title here</title>
</head>
<body>

<%
if(session.getAttribute("username")==null){
	response.sendRedirect("main.jsp");
}else{
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	String typestring = session.getAttribute("type").toString();
	int type_int = Integer.valueOf(typestring);
	if( type_int!=1 ){
		response.sendRedirect("main.jsp");
	}
}




%>

<%
int id = Integer.parseInt(request.getAttribute("provoles_id").toString());



pageContext.setAttribute("id" ,id);

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
       
        	String query = "select FILMS_ID,CINEMAS_ID,DAY from cinema_last.provoles where ID='"+id+"' ";
         	Statement stat = conn.createStatement();
         	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		 response.sendRedirect("error.jsp");
    	
    	}else {
    		
    		int film_id = rs.getInt("FILMS_ID");
    		System.out.println(film_id);
			String cinema_id = rs.getString("CINEMAS_ID");			
			System.out.println(cinema_id);
			Timestamp day = rs.getTimestamp("DAY");
			System.out.println(day);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String formatted_day = sdf.format(day);
			  
			
            pageContext.setAttribute("film_id", film_id);
            pageContext.setAttribute("cinema_id", cinema_id);
            pageContext.setAttribute("formatted_day", formatted_day);
			
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
	<form action="edit_provoli" id="edit_form" method="POST" class="form-film">
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
		int film_id = (int) pageContext.getAttribute("film_id");
				
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
			
			 if (ID == film_id) {
                 out.println(" <option value="+ID+" selected>"+title+"</option>");
             } else {
                 out.println(" <option value="+ID+">"+title+"</option>");
             }
    		while(rs.next()){
    			 num++;
    			 title = rs.getString("TITLE");
    			 ID = rs.getInt("ID");
    			 if (ID == film_id) {
                     out.println(" <option value="+ID+" selected>"+title+"</option>");
                 } else {
                     out.println(" <option value="+ID+">"+title+"</option>");
                 } 			
    		}
    	
    	}
             	
       	   
       	} catch (Exception e) {
            	    e.printStackTrace();
            	    response.sendRedirect("error.jsp");
       	}
    }
        conn.close();
    }catch (Exception e){
    System.out.println("nope2");
   
}

%>
		
		
       
       </select>
       
       <label for="cinema" class="film__label">
			CINEMA
		</label>
		
		<select name="cinema" id="movieres" class="dropdown_main">
		<%

		String cinema_id = (String) pageContext.getAttribute("cinema_id");
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
			System.out.println("STRING IS"+cinema_id);
			 if (ID.equals(cinema_id)) {
                 out.println(" <option value="+ID+" selected>"+ID+"</option>");
             } else {
                 out.println(" <option value="+ID+">"+ID+"</option>");
             }
    		while(rs.next()){
    			
    			
    			 ID = rs.getString("ID");
    			 if (ID.equals(cinema_id)) {
                     out.println(" <option value="+ID+" selected>"+ID+"</option>");
                 } else {
                     out.println(" <option value="+ID+">"+ID+"</option>");
                 }   			
    		}
    	
    	}
             	
       	   
       	} catch (Exception e) {
            	    e.printStackTrace();
            	    response.sendRedirect("error.jsp");
       	}
    }
        conn.close();
    }catch (Exception e){
    System.out.println("nope3");
  
}

%>
		
		
       
       </select>
		
		
		<label for="film-input-password" class="film__label">
			PREMIER
		</label>
		<input id="premier" name="premier" required class="film__input" type="datetime-local" value="${formatted_day}"/>
		
<div style="margin-top:70px;">
<p style="color:red">${result}</p>
<button class="film__submit" type="submit" form="edit_form" >SUBMIT</button>
<button class="film__submit" type="submit" form="delete_form" >DELETE</button>
</div>	


<input name="idnumber"  value="${provoles_id}" type="hidden"/>

		
	</form>
	
<form id="delete_form" action="delete_provoli" method="POST" >
<input name="idnumber"  value="${provoles_id}" type="hidden"/>
</form>
</body>
</html>