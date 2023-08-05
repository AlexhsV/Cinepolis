<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="java.sql.Timestamp" %>
 <%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/client_stylee.css">
 
<title>Insert title here</title>
</head>
<body>

<script>
  function returnWithDelay(event,it) {
	event.preventDefault();
    var dataHolder = document.getElementById("data-holder");
    dataHolder.style.transition = "opacity 1s";
    dataHolder.style.opacity = 0;

    setTimeout(function() {
    	console.log("Delayed message");
    	var destinationUrl = document.getElementById(it).href;
        window.location.href = destinationUrl;
    }, 1000);
    return false;
  }
  
  
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
  
  
  // Prevent the form from submitting immediately
</script>

<script>
  window.onload = function() {
    var wrapper = document.getElementById("wrapper");
    wrapper.style.opacity = 1;
  };
</script>


<div class="wrapper" id="wrapper">
<div class="user-buttons">

 
 

 
 </div>
<div class="data-holder" id="data-holder">

<div style="margin:0;" class="movie-container" id="movie-container">



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
       
       	 
       	
       
     
       	    
       	 String query = "SELECT R.username, "
       		 + "R.PROVOLES_ID, "
       		 + "R.SEAT, "
       		 + "R.resrvationid, "
       		 + "C.FILMS_TITLE, "
       		 + "C.CINEMAS_ID, "
             + "C.DAY "
           	 + " FROM cinema_last.reservations AS R "
             + " INNER JOIN cinema_last.provoles AS C ON C.ID = R.PROVOLES_ID "
             + " WHERE R.username = '"+session.getAttribute("username")+"' " 
             + " ORDER  BY C.DAY ASC ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		
    	}else {
    		int num=0;
    		String name = rs.getString("R.username");
			int seatn = rs.getInt("R.SEAT");
			int ticketnum = rs.getInt("R.resrvationid");			
			String title = rs.getString("C.FILMS_TITLE");
			String cinema = rs.getString("C.CINEMAS_ID");
			Timestamp day = rs.getTimestamp("C.DAY");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String formatted_day = sdf.format(day);
			String numberString = String.valueOf(ticketnum);
			
			 out.println("<div class=\"tickets\">");
            do{
             num++;
             name = rs.getString("R.username");
 			 seatn = rs.getInt("R.SEAT");
 			 ticketnum = rs.getInt("R.resrvationid");			
 		 	 title = rs.getString("C.FILMS_TITLE");
 		 	 cinema = rs.getString("C.CINEMAS_ID");
 		 	 day = rs.getTimestamp("C.DAY");
             sdf = new SimpleDateFormat("HH:mm EEEE-dd-MMMM");
			 formatted_day = sdf.format(day);
			 numberString = String.valueOf(ticketnum);
			 
			 while(numberString.length()<6){
				 numberString="0"+numberString;
			 }
			 
			 numberString="#00"+numberString;
   		 	
   			
   			 out.println("<div class=\"ticket\">"
   					     +"<div class=\"left\">"
   					     +"<h4>"+name+" - "+numberString+ "</h4>"
   				         +"<p>"+title+"</p>"
   				         +"<p>"+cinema+"</p>"
   				         +"<p>"+formatted_day+"</p>"
   				         +"</div>"
   				         +"<div class=\"right\">"
   				         +"<p style=\"margin-top:2vh;font-size:5vh;\">"+seatn+"</p>"
   				         +"</div>"
   				         +"</div>");
   				        						
   			
   			
   			
   			
   			
   			

   		

   		
   			

            }while(rs.next());
           
  			 out.println("</div>");

    		
    	
    	
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
