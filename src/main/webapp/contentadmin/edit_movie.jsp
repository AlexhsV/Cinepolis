<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.Statement" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="javax.servlet.RequestDispatcher, javax.servlet.ServletException, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession" %>
<%@ page import="javax.naming.Context, javax.naming.InitialContext, javax.sql.DataSource" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/addMovie.css">
<title>Insert title here</title>
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

<%
int id = Integer.parseInt(request.getAttribute("movie_id").toString());


pageContext.setAttribute("id1" ,id);

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
        System.out.println("success");
     
       
        try {
       
       	 
       	
       
     
       	    
       	 String query = "select TITLE,LENGTH,CATEGORY,DESCRIPTION,DIRECTOR,PREMIERE from cinema_last.films where ID='"+id+"' ";
       	 Statement stat = conn.createStatement();
       	ResultSet rs = stat.executeQuery(query);
    	if(rs.next() == false) {
    		System.out.println("ResultSet in empty in Java");
    		 response.sendRedirect("error.jsp");
    	
    	}else {
    		int num=1;
    		String title = rs.getString("TITLE");
			String category = rs.getString("CATEGORY");			
			int length = rs.getInt("LENGTH");
			String description = rs.getString("DESCRIPTION");		
			String director = rs.getString("DIRECTOR");	
			Date premiere = rs.getDate("PREMIERE");	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
			String formatted_premier = sdf.format(premiere);
    		
			pageContext.setAttribute("titles",title);			
			pageContext.setAttribute("length",length);
			pageContext.setAttribute("category",category);
			pageContext.setAttribute("description",description);
			pageContext.setAttribute("director",director);
			pageContext.setAttribute("formatted_premier",formatted_premier);
				 
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
	<form  id ="edit_form" action="edit_movie" method="POST" class="form-film">
	
	
		<ul class="film-nav">
			
			<li class="film-nav__item">
				<a>Edit film </a>
			</li>
		</ul>
		<input name="idnumber"  value="${movie_id}" type="hidden"/>
		<label for="film-input-password" class="film__label">
    TITLE
</label>
<input id="title" name="title" required class="film__input" type="text" value="${titles}" />

<label for="film-input-password" class="film__label">
    LENGTH (minutes)
</label>
<input id="length" name="length" required class="film__input" type="number" value="${length}" />

<label for="film-input-password" class="film__label">
    CATEGORY
</label>
<input id="category" name="category" required class="film__input" type="text" value="${category}" />

<label for="film-input-password" class="film__label">
    DESCRIPTION
</label>
<textarea id="description" name="description" required class="film__input">${description}</textarea>

<label for="film-input-password" class="film__label">
    DIRECTOR
</label>
<input id="director" name="director" required class="film__input" type="text" value="${director}" />

<label for="film-input-password" class="film__label">
    PREMIER
</label>
<input id="premier" name="premier" required class="film__input" type="date" value="${formatted_premier}" />




<button class="film__submit" type="submit" form="edit_form" >SUBMIT</button>
<button class="film__submit" type="submit" form="add_image" >ADD IMAGE</button>
<button class="film__submit" type="submit" form="delete_form" >DELETE</button>






		
	</form>
<form style="display:none;" id="delete_form" action="delete_movie" method="POST" class="form-film">
<input name="idnumber"  value="${movie_id}" type="hidden"/>
</form>
<form id="add_image" action="add_image" method="POST" class="form-film" enctype="multipart/form-data">
<input name="idnumber"  value="${movie_id}" type="hidden"/>
<label for="film-input-password" class="film__label">
    FILM POSTER
</label>
<input required class="film__input" type="file" name="photo" accept="image/*">
<label for="film-input-password" class="film__label">
    FILM BACKGROUND
</label>
<input required class="film__input" type="file" name="photo2" accept="image/*">

</form>
</body>
</html>