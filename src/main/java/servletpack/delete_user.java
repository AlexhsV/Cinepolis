package servletpack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
 * Servlet implementation class delete_user
 */
@WebServlet("/admin/delete_user")
public class delete_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("idnumber") ;
		System.out.println("username: " + username);
		       
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
             	 
           
            
             	 String query = "delete from cinema_last.reservations where user_username='"+username+"'";
             	    statement = conn.prepareStatement(query);
             	   statement.executeUpdate();
             	
             	    
             	  
             	  
             	 
             	    
                	  query= "delete from cinema_last.user where username='"+username+"'";
               	    statement = conn.prepareStatement(query);

               	     statement.executeUpdate();


             	    response.sendRedirect("../succlogin.jsp");
             	} catch (Exception e) {
             
             	    e.printStackTrace();
             	    
             	
             	    response.sendRedirect("error.jsp");
             	}
          }
      }catch (Exception e){
          System.out.println("nope");
          System.out.println(e.toString());
      }
      
	}

}
