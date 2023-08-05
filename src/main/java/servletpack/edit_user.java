package servletpack;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class edit_user
 */
@WebServlet("/admin/edit_user")
public class edit_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 
		 
        String username = request.getParameter("username");
        String usernamen = request.getParameter("usernamen");
        String password= request.getParameter("password");
        String rpassword = request.getParameter("rpassword");
     	
        if( !password.equals(rpassword)) {
        	request.setAttribute("result", "Passwords dont match");
        	request.setAttribute("username", username);
        	request.setAttribute("password", password);
        	RequestDispatcher rd = request.getRequestDispatcher("edit_user.jsp");
        	System.out.println("returning to edit page");
        	rd.forward(request,response);
        	
        }else {

            System.out.println("username id is :"+ username);
            System.out.println("In edit servlet ");
            
            try {
            	password = password + "kk@";
             	rpassword = rpassword + "kk@";
               	String hashedpass = password;
              try {
            	  MessageDigest md = MessageDigest.getInstance("SHA-256");
                  byte[] hashedBytes = md.digest(password.getBytes());
                  StringBuilder sb = new StringBuilder();
                  for (byte b : hashedBytes) {
                      sb.append(String.format("%02x", b));
                  }
                  hashedpass = sb.toString(); 
                  System.out.println(hashedpass.length());
              }catch (Exception e){
                  System.out.println("nope");
                  System.out.println(e.toString());
                  return;
              }
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
                   
                   	   
                   	 String query = "UPDATE cinema_last.user SET USERNAME = ?, PASSWORD = ? WHERE USERNAME = ?";
                   	    statement = conn.prepareStatement(query);
                   	   
                   	    statement.setString(1, usernamen);
                   	    statement.setString(2, hashedpass); 
                   	    statement.setString(3, username);
                   	    
                   	    
                   	   statement.executeUpdate();
                   	    
                   	   
                      	 query = "UPDATE cinema_last.reservations set  username = ? where username='"+username+"'";
                      	    statement = conn.prepareStatement(query);
                      	   
                      	    statement.setString(1, usernamen);
                      	   
                      	    
                      	    // Process the result or perform any additional actions
                      	    statement.executeUpdate();
                   	    
                   	    response.sendRedirect("../succlogin.jsp");
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

}
