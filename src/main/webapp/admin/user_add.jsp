<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  
<meta charset="ISO-8859-1">
  <title>Add Movie to Database</title>
  <link rel="stylesheet" href="../css/addMovie.css">
    <link rel="stylesheet" href="css/addMovie.css">
  
</head>
<body>
<%
if(session.getAttribute("username")==null){
	response.sendRedirect("log-in.jsp");
}else{
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	String typestring = session.getAttribute("type").toString();
	int type_int = Integer.valueOf(typestring);
	if( type_int!=0 ){
		response.sendRedirect("log-in.jsp");
	}
}




%>
  <div class="film-container">
	<form action="add_user" method="POST" class="form-film">
		<ul class="film-nav">
			
			<li class="film-nav__item">
				<a>Add a user to the database</a>
			</li>
		</ul>
		
		<label for="film-input-username" class="film__label">
			USERNAME
		</label>
		<input id="film-input-username" name="username" required class="film__input" type="text"  value="${username}"/>
		
		<label for="film-input-password" class="film__label">
			PASSWORD
		</label>
		<input id="film-input-password" name="password" required class="film__input" type="text"  value="${password}"/>
		
		<label for="film-input-rpassword" class="film__label">
			RETYPE PASSWORD
		</label>
		<input id="film-input-rpassword" name="rpassword" required class="film__input" type="text" value="${password}" />
		
		<label for="film-input-type" class="film__label">
			TYPE
		</label>
	
	    <select name="type" id="type" class="film__input">
         <option value="0">Admin</option>
         <option value="1">Content Admin</option>
         <option value="2">Client</option>
        </select>
		<label for="film-input-date" class="film__label">
			BIRTHDAY
		</label>
		<input name="date" type="date" class="film__input" required>
		<p style="color:red">${result}</p>
		<input name="create" type="hidden" value="c">
		<button class="film__submit"  type='submit'>SUBMIT</button>
		
	</form>
</div>
</body>
</html>