package servletpack;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class delete_provoli
 */
@WebServlet("/contentadmin/delete_provoli")
public class delete_provoli extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_provoli() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		
		int ID = Integer.parseInt(request.getParameter("idnumber")) ;
		
		       
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
             	 
           
            
             	 String query = "delete from cinema_last.reservations where PROVOLES_ID='"+ID+"'";
             	    statement = conn.prepareStatement(query);
                    statement.executeUpdate();
     
             	    
                	query= "delete from cinema_last.provoles where ID='"+ID+"'";
               	    statement = conn.prepareStatement(query);

               	    statement.executeUpdate();


             	    response.sendRedirect("mainp.jsp");
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
