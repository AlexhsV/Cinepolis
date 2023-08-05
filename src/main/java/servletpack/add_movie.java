package servletpack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * Servlet implementation class add_movie
 */
@WebServlet("/contentadmin/add_movie")

public class add_movie extends HttpServlet {
	private static final long serialVersionUID = 12L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_movie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 
		 
         
         String TITLE = request.getParameter("title");     
         int LENGTH = Integer.parseInt(request.getParameter("length").toString());
         String CATEGORY = request.getParameter("category");
         String DESCRIPTION = request.getParameter("description");
         String DIRECTOR = request.getParameter("director");
         String PREMIERE = request.getParameter("premier").toString();
    
         
         
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
                	 
                	
                   	 
                    
                	 
                	 PreparedStatement statement = null;
                	 HttpSession session = request.getSession();
                	 
                	 
                	 
                	    // Execute the query using your database connection
                	    // Assuming you have a connection object named "connection"
                	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjusted date format pattern
                	    LocalDate premiereDateTime = LocalDate.parse(PREMIERE, formatter);
                	    Timestamp premiereTimestamp = Timestamp.valueOf(premiereDateTime.atStartOfDay());
                	   
                	 String query = "INSERT INTO cinema_last.films (  TITLE, LENGTH, CATEGORY, DESCRIPTION, DIRECTOR, PREMIERE) VALUES (?, ?, ?, ?, ?, ?)";
                	    statement = conn.prepareStatement(query);
                	  
                	
                	    statement.setString(1, TITLE);
                	    statement.setInt(2, LENGTH);
                	    statement.setString(3, CATEGORY);
                	    statement.setString(4, DESCRIPTION);
                	    statement.setString(5, DIRECTOR);
                	    statement.setTimestamp(6, premiereTimestamp);;
                	 
                	    
                	    // Process the result or perform any additional actions
                	    int rowsInserted = statement.executeUpdate();
                	    // Display success message or redirect to a success page
                	    response.sendRedirect("main.jsp");
                	} catch (Exception e) {
                	    // Handle any errors that occurred during the execution of the query
                	    e.printStackTrace();
                	    
                	    // Display error message or redirect to an error page
                	    response.sendRedirect("main.jsp");
                	}
             }
         }catch (Exception e){
             System.out.println("nope");
             System.out.println(e.toString());
         }
         
         
         
	}
	
}
