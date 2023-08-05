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
  
  
</script>


<script>
    function toggleSeatImage(checkbox) {
        var seatImage = checkbox.nextElementSibling.querySelector("img");
        
        if (checkbox.checked) {
            seatImage.src = "../assets/icons/selected_seat.png";
            seatImage.classList.add("selected-seat");
        } else {
            seatImage.src = "../assets/icons/seat.png";
            seatImage.classList.remove("selected-seat");
        }
    }
    	
</script>

<script>
  window.onload = function() {
    var wrapper = document.getElementById("theater");
    wrapper.style.transition = "opacity 5s";
    wrapper.style.opacity = 1;
    
  };
</script>


<script>

   
  
  function pressWithDelay(event) {
	event.preventDefault();
    var movieContainer = document.getElementById("seats");
    movieContainer.style.transition = "opacity 1s";
    var movieContainer2 = document.getElementById("bookn");
    movieContainer2.style.transition = "opacity 1s";
    movieContainer.style.opacity = 0;
    movieContainer2.style.opacity = 0;


    setTimeout(function() {
    	console.log("Delayed message");
      var form = document.getElementById('makeres');
      form.submit();
    }, 2000);
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

<div style="background-color:rgb(33, 16, 84);">
<div class="theater" id="theater">




<%
int id = Integer.parseInt(request.getParameter("id").toString());
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
			out.println("<img src=\"../assets/icons/screen.png\" style=\"width:80vh;\"><div class=\"seats\" id=\"seats\">");
            out.println("<form action=\"add_res\" id=\"makeres\" method=\"POST\"><input type=\"hidden\" name=\"provoli_id\" value="+id+"><div class=\"row\">");
			while(num<=seat_num){
				if(seats.charAt(num-1)=='0'){
					
					out.println(" <input type=\"checkbox\" id=\"seat"+num+"Checkbox\" name=\"selectedSeats\" value=\""+num+"\" style=\"display:none;\"onchange=\"toggleSeatImage(this);\">"
					+"<label for=\"seat"+num+"Checkbox\"> <img style=\"height:4vh;\" src=\"../assets/icons/seat.png\"><center>"+num+"</center></label>");
				}else{
					
					out.println("<label for=\"seat"+num+"Checkbox\"> <img style=\"height:4vh;\" src=\"../assets/icons/seat_occ.png\"><center>"+num+"</center></label>");
				}
				
				if(num%10==0){
					out.println("</div><div class=\"row\">");
				}
				num++;
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
</form>
</div>




</div>
<button form="makeres" onclick="pressWithDelay(event)" class="glow-on-hover" type="submit" id="bookn">BOOK NOW</button>
</div>
</body>
</html>