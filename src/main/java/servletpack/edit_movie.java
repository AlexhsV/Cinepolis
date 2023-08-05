package servletpack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Servlet implementation class edit_movie
 */
@WebServlet("/contentadmin/edit_movie")
public class edit_movie extends HttpServlet {
	private static final long serialVersionUID = 4351L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit_movie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 

		String TITLE = request.getParameter("title");
		int LENGTH = Integer.parseInt(request.getParameter("length"));
		String CATEGORY = request.getParameter("category");
		String DESCRIPTION = request.getParameter("description");
		String DIRECTOR = request.getParameter("director");
		int ID = Integer.parseInt(request.getParameter("idnumber")) ;
		String PREMIERE = request.getParameter("premier");
	

		



        
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
          
               
               	 
               	 PreparedStatement statement = null;
               	 HttpSession session = request.getSession();
               	 
               	 
               	   System.out.println(String.valueOf(session.getAttribute("username")));               
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
               	   LocalDate premiereDate = LocalDate.parse(PREMIERE, formatter);
               	    
               	   
               	 String query = "UPDATE cinema_last.films SET TITLE = ?, LENGTH = ?, CATEGORY = ?, DESCRIPTION = ?, DIRECTOR = ?, PREMIERE = ? WHERE ID = ?";
               	    statement = conn.prepareStatement(query);
               	   
               	    
               	    statement.setString(1, TITLE);
               	    statement.setInt(2, LENGTH);
               	    statement.setString(3, CATEGORY);
               	    statement.setString(4, DESCRIPTION);
               	    statement.setString(5, DIRECTOR);
               	    statement.setDate(6,java.sql.Date.valueOf( premiereDate));
               	    statement.setInt(7, ID);
               	    
               
               	    int rowsInserted = statement.executeUpdate();
            
               	    response.sendRedirect("main.jsp");
               	} catch (Exception e) {
               
               	    e.printStackTrace();
               	    
          
               	    response.sendRedirect("main.jsp");
               	}
            }
        }catch (Exception e){
            System.out.println("nope");
            System.out.println(e.toString());
        }
	}

}
