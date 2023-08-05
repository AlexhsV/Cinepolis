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
 <%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/reservation_style.css">
<title>Insert title here</title>
</head>
<body>

<script>
  function returnWithDelay(event,it) {
	event.preventDefault();
    var theater = document.getElementById("theater");
    theater.style.transition = "opacity 1s";
    theater.style.opacity = 0;

    setTimeout(function() {
    	console.log("Delayed message");
    	var destinationUrl = document.getElementById(it).href;
        window.location.href = destinationUrl;
    }, 1000);
    return false;
  }
  
  
  // Prevent the form from submitting immediately
</script>


<script>
    function toggleSeatImage(checkbox) {
        var seatImage = checkbox.nextElementSibling.querySelector("img");
        
        if (checkbox.checked) {
            // Set the image source for the selected seat
            seatImage.src = "../assets/icons/selected_seat.png";
            seatImage.classList.add("selected-seat");
        } else {
            // Set the image source back to the default seat image
            seatImage.src = "../assets/icons/seat.png";
            seatImage.classList.remove("selected-seat");
        }
    }
    	
</script>

<script>
  window.onload = function() {
	var theater = document.getElementById("theater");
    theater.style.opacity = 1;
    var wrapper = document.getElementById("tick");
    wrapper.style.transition = "opacity 2s";
    wrapper.style.opacity = 1;
    wrapper.classList.add('swing');
    
  };
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
} %>

<div style="position:absolute;top:50px;left:0px;z-index:80;">
<div class="user-nav" style="display:flex;align-items:flex-start;flex-direction:column;" >

 
  <div style="display: flex;">
   <image src="../assets/icons/user.png" style="height:30px;width:30px;margin-top:10px;margin-left:10px;"></image>
  <p style="padding-top:8px;color:black;" class="log-out">
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


<div class="theater" id="theater">

<%
    
    int id = Integer.parseInt(request.getParameter("id").toString());
    String redirectUrl = "make_res.jsp?id="+id; 
    int delayInSeconds = 3;
    response.setHeader("Refresh", delayInSeconds + "; URL=" + redirectUrl);
%>


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
       
       	 
       	
       
     
       	    
       	 String query = "select FILMS_TITLE,CINEMAS_ID,DAY,SEATS,SEAT_NUM from cinema_last.provoles where ID='"+id+"' ";
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
			String seats = rs.getString("SEATS");
			int seat_num = rs.getInt("SEAT_NUM");
			
			 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEEE dd-MMMM");
				String formatted_day = sdf.format(DAY);
			out.println("<h1>"+title+"</h1>");
			out.println("<h2>"+cinema+" "+formatted_day+"</h2>");
			out.println("<img src=\"../assets/icons/screen.png\" style=\"width:80vh;\"><div style=\"box-shadow: none;\" class=\"seats\" >");
            out.println("<div class=\"row\">");
			
    	
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
<div id="tick" style="opacity:0;background-color: rgba(255, 0, 0, 0.5);; height: 20vh; width: 20vh; border-radius: 20vh; margin-top: 30vh; border: 4px solid white; display: flex; justify-content: center; align-items: center;">
  <div id="innertick" class="x-symbol">X</div>
</div>


</div>
</form>
</div>


<p style="width: 20vh;height: 4vh;background: rgb(33, 16, 84);margin-top: 4vh;"></p>

</div>



</body>
</html>