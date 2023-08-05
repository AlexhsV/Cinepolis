<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/admin_style.css">
<title>AdminPage
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
	if( type_int!=0 ){
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

 <a href="../logout"><image src="../assets/icons/power-off.png" style="height:30px;width:30px;padding-top:2px;"></a>
 </div>




<%
out.println("<div style='background-color:rgb(240, 240, 240);' ><center ><form id='form0' action='transfer_user_data' class='user-wrapper'><Input type='Hidden' name='id'  id='id' value='create_new_user'><button type='submit' form='form0' value='Submit' class='hid_but'> <H3>CREATE USER</H3></button> </form> </center></div>");
out.println("<div class=\"user-holder\" >");
out.println("<div class='admins'>");
out.println("<center><h2>ADMINS</h2></center>");
int num=1;
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
     	    String query = "select username,type from cinema_last.user where type=0";
       	    Statement stat = conn.createStatement();
          	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		request.setAttribute("result", "No user found.");
    	}else {
    		
    		String username = rs.getString("username");
			out.println("<form id='form"+num+"' action='transfer_user_data' class='user-wrapper'><Input type='Hidden' name='id'  id='id' value='"+username+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='user-wrapper'><H3>"+username+"</H3>	</div></button> </form>");
    		while(rs.next()){
    			 num++;
    			 username = rs.getString("username");
    			 out.println("<form id='form"+num+"' action='transfer_user_data' class='user-wrapper'><Input type='Hidden' name='id'  id='id' value='"+username+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='user-wrapper'><H3>"+username+"</H3>	</div></button> </form>");
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
out.println("</div>");
num++;

out.println("<div class='admins'>");
out.println("<center><h2>CONTENT ADMINS</h2></center>");
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
     	    String query = "select username,type from cinema_last.user where type=1";
       	    Statement stat = conn.createStatement();
          	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		request.setAttribute("result", "No user found.");
    	}else {
    		
    		String username = rs.getString("username");
			out.println("<form id='form"+num+"' action='transfer_user_data' class='user-wrapper'><Input type='Hidden' name='id'  id='id' value='"+username+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='user-wrapper'><H3>"+username+"</H3></div></button> </form>");
    		while(rs.next()){
    			 num++;
    			 username = rs.getString("username");
    			 out.println("<form id='form"+num+"' action='transfer_user_data' class='user-wrapper'><Input type='Hidden' name='id'  id='id' value='"+username+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='user-wrapper'><H3>"+username+"</H3>	</div></button> </form>");
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
out.println("</div>");
num++;

out.println("<div class='admins'>");
out.println("<center><h2>USERS</h2></center>");


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
     	    String query = "select username,type from cinema_last.user where type=2";
       	    Statement stat = conn.createStatement();
          	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		request.setAttribute("result", "No user found.");
    	}else {
    		
    		String username = rs.getString("username");
			out.println("<form id='form"+num+"' action='transfer_user_data' class='user-wrapper'><Input type='Hidden' name='id'  id='id' value='"+username+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='user-wrapper'><H3>"+username+"</H3></div></button> </form>");
    		while(rs.next()){
    			 num++;
    			 username = rs.getString("username");
    			 out.println("<form id='form"+num+"' action='transfer_user_data' class='user-wrapper'><Input type='Hidden' name='id'  id='id' value='"+username+"'><button type='submit' form='form"+num+"' value='Submit' class='hid_but'> <div  class='user-wrapper'><H3>"+username+"</H3>	</div></button> </form>");
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
out.println("</div>");






%>

</div>
</div>



</body>
</html>