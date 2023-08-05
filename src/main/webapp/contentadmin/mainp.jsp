<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page import="java.util.concurrent.TimeUnit" %>
    <%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/contentadmin_stylep.css">
<title>ContentAdminPage
</title>
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


<div class="wrapper">
<div class="user-buttons">
 
 <div style="display:flex;align-items:center ;">
 <image src="../assets/icons/user.png" style="height:30px;width:30px;">
 <p style="padding:8px;">
 <%
 out.println(String.valueOf(session.getAttribute("username"))); %>
 </p>
 </div>
  
 <div style="display:flex;flex-direction:row;">
 <form id="but1" method="post" action="main.jsp">
  <input type="hidden" name="but"  value="but1" /> 
  <button class="menu" type="submit" form="but1"><image src="../assets/icons/film.png" style="height:35px;width:35px;padding-bottom:7px;"></button>

 </form>
  
 <button class="menu"  type="submit" form="but2"> <image src="../assets/icons/booking.png" style="height:40px;width:40px;padding-top:2px;"></button>
 
 
 <a href="../logout"><image src="../assets/icons/power-off.png" style="height:40px;width:40px;padding-top:2px;"></a>
 
 </div>
 </div>


 
<div class="res-holder">
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
       
       	 
       	
       
     
       	    
       	 String query = "select FILMS_TITLE,CINEMAS_ID,DAY,ID from cinema_last.provoles order by DAY ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		request.setAttribute("result", "No provoles found.");
    	}else {
    		int num=1;
    		String title = rs.getString("FILMS_TITLE");
			String cinema = rs.getString("CINEMAS_ID");
			Timestamp DAY = rs.getTimestamp("DAY");
			int provoli_id = rs.getInt("ID");
			LocalDateTime premiereDateTime = DAY.toLocalDateTime();
		
			 out.println("<form id='form"+num+"' action='transfer_provoles_data'><Input type='Hidden' name='id'  id='id' value='"+provoli_id+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='res-wrapper'><H3>"+title+"</H3>	<div style='display:flex; justify-content:space-around;width: 100%;'><p>Cinema:<strong>"+cinema+"</strong></p><p>"+premiereDateTime+"</p> </div></div></button> </form>");
    		while(rs.next()){
    			 num++;
    			 title = rs.getString("FILMS_TITLE");   
    			 cinema = rs.getString("CINEMAS_ID");
    		     DAY = rs.getTimestamp("DAY");
    		     provoli_id = rs.getInt("ID");
    			 premiereDateTime = DAY.toLocalDateTime();
    			
    			 out.println("<form id='form"+num+"' action='transfer_provoles_data'><Input type='Hidden' name='id'  id='id' value='"+provoli_id+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='res-wrapper'><H3>"+title+"</H3>	<div style='display:flex; justify-content:space-around;width: 100%;'><p>Cinema:<strong>"+cinema+"</strong></p><p>"+premiereDateTime+"</p> </div></div></button> </form>");
    			
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
<form id="new_movie" action="transfer_provoles_data">      
  <Input type="Hidden" name="id"  id="name" value="-100">
   <button type="submit" form="new_movie" value="Submit" class="hid_but">
   <div  class="res-wrapper">
   <image src="../assets/icons/plus.png" style="height:50px;width:50px;">
   </div></button> 
    </form>



</div>

</div>



</body>
</html>