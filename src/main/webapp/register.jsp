<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
    <%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>
    
<!DOCTYPE html>
<html>
<head>
  
<meta charset="ISO-8859-1">
  <title>Register</title>
  <link rel="stylesheet" href="css/addMovie.css">
</head>
<body>	
<%
if(session.getAttribute("username")!=null){
	response.sendRedirect("main.jsp");
}




%>
  <div class="film-container">
	<form action="register" method="POST" class="form-film">
		<ul class="film-nav">
			
			<li class="film-nav__item">
				<a href="log-in.jsp">Sign In</a>
				<a >Register</a>
			</li>
		</ul>
		
		<label for="film-input-username" class="film__label">
			USERNAME
		</label>
		<input id="film-input-username" name="username" required class="film__input" type="text" value="${username}"  />
		
		<label for="film-input-password" class="film__label">
			PASSWORD
		</label>
		<input id="film-input-password" name="password" required class="film__input" type="text" value="${password}" />
		
		<label for="film-input-rpassword" class="film__label">
			RETYPE PASSWORD
		</label>
		<input id="film-input-rpassword" name="rpassword" required class="film__input" type="text"  />
		
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
   	    String query = "select count(username) as C from cinema_last.user ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
       	
       	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		
    		
    	}else {
    		if(rs.getInt("C")==0){
    			out.println("<input name=\"type\" value=\"0\" type=\"hidden\">");
    		}else{
    			out.println("<input name=\"type\" value=\"2\" type=\"hidden\">");
    			out.println("<label for=\"film-input-date\" class=\"film__label\">BIRTHDAY</label><input name=\"date\" type=\"date\" class=\"film__input\" required>");
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

		
		
	
		
		<p style="color:red">${result}</p>
		<button class="film__submit"  type='submit'>SUBMIT</button>
		
	</form>
</div>
</body>
</html>