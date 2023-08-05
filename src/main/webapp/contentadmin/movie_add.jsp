<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  
<meta charset="ISO-8859-1">
  <title>Add Movie to Database</title>
  <link rel="stylesheet" href="../css/addMovie.css">
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
  <div class="film-container">
	<form action="add_movie" method="POST" class="form-film"  >
		<ul class="film-nav">
			
			<li class="film-nav__item">
				<a>Add a film to the database</a>
			</li>
		</ul>
		
		<label for="film-input-password" class="film__label">
			TITLE
		</label>
		<input id="title" name="title" required class="film__input" type="text" />
		
		<label for="film-input-password" class="film__label">
			LENGTH (minutes)
		</label>
		<input id="length" name="length" required class="film__input" type="number" />
		
		<label for="film-input-password" class="film__label">
			CATEGORY
		</label>
		<input id="category" name="category" required class="film__input" type="text" />
		
		<label for="film-input-password" class="film__label">
			DESCRIPTION
		</label>
		<textarea id="description" name="description" required class="film__input"></textarea>
		
		<label for="film-input-password" class="film__label">
			DIRECTOR
		</label>
		<input id="director" name="director" required class="film__input" type="text"/>
		
		<label for="film-input-password" class="film__label">
			PREMIER
		</label>
		<input id="premier" name="premier" required class="film__input" type="date"/>
			
		
		<button class="film__submit"  type='submit'>SUBMIT</button>
		
	</form>
</div>
</body>
</html>