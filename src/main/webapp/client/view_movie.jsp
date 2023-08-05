	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="java.io.ByteArrayOutputStream, java.io.IOException, java.io.InputStream" %>
<%@ page import="javax.imageio.ImageIO, java.awt.image.BufferedImage" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.io.InputStreamReader" %>
 <%@ page import="java.sql.Timestamp" %>
 <%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/view_movie_style.css">
<title>Insert title here</title>
</head>
<body>
<div id="colorFill"></div>

<script>
  function returnWithDelay(event,it) {
	event.preventDefault();
    var movieContainer = document.getElementById("d");
    movieContainer.style.transition = "opacity 1s";
    movieContainer.style.opacity = 0;

    setTimeout(function() {
    	console.log("Delayed message");
    	var destinationUrl = document.getElementById(it).href;
        window.location.href = destinationUrl;
    }, 1000);
    return false;
  }
  
  

</script>

<script>
  window.onload = function() {
	var theater = document.getElementById('colorFill');
	colorFill.style.opacity = 0;
	  colorFill.style.display = 'none'; 
  };
</script>

<script>

function goToMakeRes(event,formId) {
	event.preventDefault();
	var colorFill = document.getElementById('colorFill');
	  colorFill.style.display = 'block';
	  colorFill.style.transition = "opacity 1s";
	  setTimeout(function() {
		    colorFill.style.opacity = 1;
		  }, 10);

	 
	  setTimeout(function() {
     
	  document.getElementById(formId).submit(); 
		  }, 1500);
    return false;
  }
</script>
<div style="position:absolute;top:50px;left:0px;z-index:80;">
<div class="user-nav" style="display:flex;align-items:flex-start;flex-direction:column;" >

 
  <div style="display: flex;">
   <image src="../assets/icons/user.png" style="height:30px;width:30px;margin-top:10px;margin-left:10px;"></image>
  <p style="padding-top:8px;" class="log-out">
 <%
 out.println(String.valueOf(session.getAttribute("username"))); %>
 </p>
  </div>
  
  <div style="display: flex;">
  <a href="../logout" ><image src="../assets/icons/power-off.png" class="log-out"></a>
  <a id="m" href="main.jsp" onclick="returnWithDelay(event,'m')"><image src="../assets/icons/movies.png" class="log-out"></a>
  <a id="t"href="maint.jsp" onclick="returnWithDelay(event,'t')"><image src="../assets/icons/ticket.png" class="log-out"></a>  
  </div>

 </div>
</div>




<%

if(session.getAttribute("username")==null){
	response.sendRedirect("../log-in.jsp");
}else{
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	String typestring = session.getAttribute("type").toString();
	int type_int = Integer.valueOf(typestring);
	if( type_int!=2 ){
		response.sendRedirect("../log-in.jsp");
	}
}

int id = Integer.parseInt(request.getAttribute("movie_id").toString());
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
   	    String query = "select imageloc from cinema_last.films where ID='"+id+"'";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
       	
       	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		
    	}else {
    		String filename = rs.getString("imageloc");
    		out.println("<div id='d' class='page' style=\"	background-image:url('../assets/movie_backgrounds/"+filename+"');\">");
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

<div class="page2">

<div class="data-holder">


<%
System.out.println(id);
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
   	    String query = "select TITLE,DIRECTOR,DESCRIPTION,CATEGORY,POSTER,LENGTH from cinema_last.films where ID='"+id+"'";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
       	
       	if(rs.next() == false) {
       	    response.sendRedirect("main.jsp");
       	    return;
    		
    		
    		
    	}else {
    		String title = rs.getString("TITLE");
			String category = rs.getString("CATEGORY");
			int length = rs.getInt("LENGTH");
			String description = rs.getString("DESCRIPTION");		
			String director = rs.getString("DIRECTOR");	
			InputStream inputStream = rs.getBinaryStream("POSTER");
			if(inputStream==null){
				out.println("<img src='../assets/images/cmnsoon.jpg' style=\"height: 30vh;margin: 2vh 2vh 2vh 0;width:280px;\"><div class=\"information\"> ");
				
			}else{
				BufferedImage image = ImageIO.read(inputStream);	
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    ImageIO.write(image, "jpg", baos);
			    baos.flush();
			    byte[] imageBytes = baos.toByteArray();
			    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			    out.println("<img src='data:image/jpeg;base64," + base64Image + "' style=\"height: 30vh;margin: 2vh 2vh 2vh 0;width:280px;\"><div class=\"information\"> ");
			}	
			    		
            out.println("<h2 style=\"margin-bottom:0vh;\">"+title+"</h2>");
            out.println("<div style=\"display:flex;justify-content:space-between;\"><h3 class=\"margin-0\">Directed by:"+director+"</h3> <h3 class=\"margin-0\">Category:"+category+"</h3></div>");
            out.println("<p class=\"margin-0\">Length:"+length+"</p>");
            out.println("<p style=\"font-size:1.5vh;overflow: scroll;height:30vh; scrollbar-width: thin;  overflow-y: hidden; /* Hide vertical scrollbar */overflow-x: hidden;\">"+description+"</p>");
            out.println("</div></div><div class=\"res-holder\">");

    	}
   	    query = "select ID,CINEMAS_ID,DAY,NUMBER_OF_RESERVATIONS,SEAT_NUM from cinema_last.provoles where FILMS_ID='"+id+"'";
   	    stat = conn.createStatement();
    	rs = stat.executeQuery(query);
   	 if(rs.next() == false) {
    	    out.println("<h2>COMING SOON</h2>");
 		
 		
 		
 	}else {
 		int num=0;
 		do{ num++;
 			String cinema = rs.getString("CINEMAS_ID");
			String ID = rs.getString("ID");
			int numof = rs.getInt("NUMBER_OF_RESERVATIONS");		
			int total_seats = rs.getInt("SEAT_NUM");
            numof= total_seats-numof;
            Timestamp day = rs.getTimestamp("DAY");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEEE dd-MMMM");
			String formatted_day = sdf.format(day);
			out.println("<form id='form"+num+"' action='make_res.jsp' onclick=\"goToMakeRes(event,'form" + num + "')\">");
			out.println("<button type='submit' form='form"+num+"' value='Submit' class=\"hid_but\">");
			out.println("<div class=\"reserv\">");			
			out.println("<Input type='Hidden' name='id'  id='id' value='"+ID+"'>");	
			out.println("<h4>"+cinema+"</h4>");
			out.println("<h4>"+numof+"/"+total_seats+" SEATS LEFT</h4>");
			out.println("<h4>"+formatted_day+"</h4></div></button></form>");
 		}while(rs.next());
 		    
			
 		

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






</div>

<script>
  // Add animation class when the page loads
  window.addEventListener("DOMContentLoaded", function() {
    document.querySelector(".page").classList.add("page-transition");
    document.querySelector(".page2").classList.add("page-transition2");
    
    pageElement.classList.add("page-transition");

    pageElement.addEventListener("animationend", function() {
      
      bodyElement.style.backgroundImage = "none";
    });
  });
  
  <%
  id = Integer.parseInt(request.getAttribute("movie_id").toString());

 %>
</script>

</div>
</body>
</html>