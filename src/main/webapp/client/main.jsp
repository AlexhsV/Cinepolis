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
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/client_stylee.css">
<title>ClientPage
</title>
</head>
<body>

<script>
  function returnWithDelay(event,it) {
	event.preventDefault();
    var movieContainer = document.getElementById("movie-container");
    movieContainer.style.transition = "opacity 1s";
    movieContainer.style.opacity = 0;

    setTimeout(function() {
    	console.log("Delayed message");
    	var destinationUrl = document.getElementById(it).href;
        window.location.href = destinationUrl;
    }, 1000);
    return false;
  }
  
  
  // Prevent the form from submitting immediately
</script>

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



%>

<script>

   
  
  function submitFormWithDelay(event,formId) {
	event.preventDefault();
    var movieContainer = document.getElementById("movie-container");
    movieContainer.style.transition = "opacity 0.5s";
    movieContainer.style.opacity = 0;

    setTimeout(function() {
    	console.log("Delayed message");
      var form = document.getElementById(formId);
      form.submit();
    }, 1000);
    return false;
  }
  
  
</script>

<script>
  window.onload = function() {
    var wrapper = document.getElementById("wrapper");
    wrapper.style.transition = "opacity 2s";
    wrapper.style.opacity = 1;
  };
</script>


<div class="wrapper" id="wrapper">
<div class="user-buttons">

 
 

 
 </div>
<div class="data-holder">

<div class="movie-container" id="movie-container">


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
       
       	 
       	
       
     
       	    
       	 String query = "select TITLE,DESCRIPTION,ID,POSTER from cinema_last.films ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		
    	}else {
    		int num=0;
    		String title = rs.getString("TITLE");
			String desc = rs.getString("DESCRIPTION");
			int ID = rs.getInt("ID");
			
			InputStream inputStream = rs.getBinaryStream("POSTER");
			
			BufferedImage image = null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] imageBytes = null;
			String base64Image = null;
			
               		
            do{
             num++;
   			 title = rs.getString("TITLE");
   			 desc = rs.getString("DESCRIPTION");
   			 ID = rs.getInt("ID");
   			 inputStream = rs.getBinaryStream("POSTER");
   			
   			 
   			 if(inputStream == null){
   				 out.println("<div class=\"movie\">"
   	            		    + "<form id='form" + num + "' action='show_movie'>"
   						    + "<input type='Hidden' name='id'  id='id' value='" + ID + "'>"
   						    + "<div class=\"columns\">"
   						    + "<div class=\"poster\"><img src=\"../assets/images/cmnsoon.jpg\" height=\"350\"  width=\"280px\"></div>"
   						    + "<div class=\"information\">"
   						    + "<h2 class=\"title\">"+title+"</h2>"
   						    + "<p class=\"para\">"+desc+"</p>"
   			                + "<button class='book' onclick=\"submitFormWithDelay(event,'form" + num + "')\"><img src='../assets/icons/booki.png' style='height:40px;width:40px;'></img></button>"
   						    + "</div></div></div></form>");

   			 }else{
   				
       			 image = ImageIO.read(inputStream);
       			 baos = new ByteArrayOutputStream();
       	         ImageIO.write(image, "jpg", baos);
       	         baos.flush();
       	         imageBytes = baos.toByteArray();
       	         base64Image = Base64.getEncoder().encodeToString(imageBytes);
       	         out.println("<div class=\"movie\">"
                		    + "<form id='form" + num + "' action='show_movie'>"
       	        		    + "<input type='Hidden' name='id'  id='id' value='" + ID + "'>"
       	        		    + "<div class=\"columns\">"
       	        		    + "<div class=\"poster\"><img src='data:image/jpeg;base64," + base64Image + "' style='height:350px;width:280px;'></div>"
       	        		    + "<div class=\"information\">"
       	        		    + "<h2 class=\"title\">"+title+"</h2>"
       	        		    + "<p class=\"para\">"+desc+"</p>"
       	            	    + "<button class='book' onclick=\"submitFormWithDelay(event,'form" + num + "')\"><img src='../assets/icons/booki.png' style='height:40px;width:40px;'></img></button>"
       	        		    + "</div></div></div></form>");      
   			 }

            }while(rs.next());
            
    		baos.close();
    		
    	
    	
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

</div>

</div>

<div style="position:fixed;	top:50px;left:0px;">
	
<div class="user-nav" style="display:flex;align-items:flex-start;flex-direction:column;" >
  <div style="display: flex;">
   <image src="../assets/icons/user.png" style="height:30px;width:30px;margin-top:10px;margin-left:10px;"></image>
  <p style="padding:8px;" class="log-out">
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



</body>
</html>