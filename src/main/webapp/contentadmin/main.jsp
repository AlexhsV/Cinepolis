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
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>



<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/contentadmin_style.css">
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
 

  <button class="menu" type="submit" form="but1"><image src="../assets/icons/film.png" style="height:35px;width:35px;padding-bottom:7px;"></button>


 <form id="but2" method="post" action="mainp.jsp">
  <input type="hidden" name="but"  value="but2" /> 
 <button class="menu"  type="submit" form="but2"> <image src="../assets/icons/booking.png" style="height:40px;width:40px;padding-top:2px;"></button>
 
 </form>
 <a href="../logout"><image src="../assets/icons/power-off.png" style="height:40px;width:40px;padding-top:2px;"></a>
 
 </div>
 </div>


 
<div class="movie-holder">
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
       
       	 
       	
       
     
       	    
       	 String query = "select TITLE,LENGTH,CATEGORY,ID,POSTER from cinema_last.films ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		
    	}else {
    		int num=1;
    		String title = rs.getString("TITLE");
			String category = rs.getString("CATEGORY");
			int ID = rs.getInt("ID");
			int length = rs.getInt("LENGTH");
			InputStream inputStream = rs.getBinaryStream("POSTER");
			
			BufferedImage image = null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] imageBytes = null;
			String base64Image = null;
			
            if(inputStream == null || inputStream.available()==0){
       	     out.println("<form id='form"+num+"' action='transfer_movie_data'><Input type='Hidden' name='id'  id='id' value='"+ID+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='movie-wrapper'><image src='../assets/images/cmnsoon.jpg' style='height:300px;width:250px;'><H3>"+title+"</H3>	<div style='display:flex; justify-content:space-around;width: 100%;'><p>"+length+" MINS</p><p>"+category+"</p> </div></div></button> </form>");

			 }else{
			image = ImageIO.read(inputStream);	
			baos = new ByteArrayOutputStream();
		    ImageIO.write(image, "jpg", baos);
		    baos.flush();
		    imageBytes = baos.toByteArray();
		    base64Image = Base64.getEncoder().encodeToString(imageBytes);
       	     out.println("<form id='form"+num+"' action='transfer_movie_data'><Input type='Hidden' name='id'  id='id' value='"+ID+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='movie-wrapper'><img src='data:image/jpeg;base64," + base64Image + "' style='height:300px;width:250px;'><H3>"+title+"</H3>	<div style='display:flex; justify-content:space-around;width: 100%;'><p>"+length+" MINS</p><p>"+category+"</p> </div></div></button> </form>");
 
			 }    		while(rs.next()){
    			 num++;
    			 title = rs.getString("TITLE");
    			 category = rs.getString("CATEGORY");
    			 length = rs.getInt("LENGTH");
    			 ID = rs.getInt("ID");
    			 inputStream = rs.getBinaryStream("POSTER");
    			
    			 if(inputStream == null){
            	     out.println("<form id='form"+num+"' action='transfer_movie_data'><Input type='Hidden' name='id'  id='id' value='"+ID+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='movie-wrapper'><image src='../assets/images/cmnsoon.jpg' style='height:300px;width:250px;'><H3>"+title+"</H3>	<div style='display:flex; justify-content:space-around;width: 100%;'><p>"+length+" MINS</p><p>"+category+"</p> </div></div></button> </form>");

    			 }else{
    				
        			 image = ImageIO.read(inputStream);
        			 baos = new ByteArrayOutputStream();
        	         ImageIO.write(image, "jpg", baos);
        	         baos.flush();
        	         imageBytes = baos.toByteArray();
        	         base64Image = Base64.getEncoder().encodeToString(imageBytes);
            	     out.println("<form id='form"+num+"' action='transfer_movie_data'><Input type='Hidden' name='id'  id='id' value='"+ID+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='movie-wrapper'><img src='data:image/jpeg;base64," + base64Image + "' style='height:300px;width:250px;'><H3>"+title+"</H3>	<div style='display:flex; justify-content:space-around;width: 100%;'><p>"+length+" MINS</p><p>"+category+"</p> </div></div></button> </form>");
      
    			 }

    			
    			
    		}
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
<form id="new_movie" action="transfer_movie_data">      
  <Input type="Hidden" name="id"  id="name" value="-100">
   <button type="submit" form="new_movie" value="Submit" class="hid_but">
   <div  class="movie-wrapper">
   <image src="../assets/icons/plus.png" style="height:100px;width:100px;">
   </div></button> 
    </form>



</div>

</div>



</body>
</html>