package servletpack;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Statement;


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
 * Servlet implementation class add_user
 */
@WebServlet("/admin/add_user")
public class add_user extends HttpServlet {
	private static final long serialVersionUID = 1223L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 
		 
        String username = request.getParameter("username");
        System.out.println(username);
        String password= request.getParameter("password");
        System.out.println("p is:"+password);
        String rpassword= request.getParameter("rpassword");
        String create= request.getParameter("create");
        System.out.println("rp is:"+rpassword);
        int type= Integer.parseInt(request.getParameter("type"));
        System.out.println("type is:"+type);
        LocalDate birthday = null;

     
        
        if(type==2) {
        	birthday = LocalDate.parse(request.getParameter("date"));
        }
        
        
        if( !password.equals(rpassword)) {
        	request.setAttribute("result", "Passwords dont match");
        	request.setAttribute("username", username);
        	request.setAttribute("password", password);
        	if(create.equals("c")) {
        		RequestDispatcher rd = request.getRequestDispatcher("user_add.jsp");
        		System.out.println("returning to add page");
            	rd.forward(request,response);
        	}else {
        		RequestDispatcher rd = request.getRequestDispatcher("edit_user.jsp");
        		System.out.println("returning to edit page");
            	rd.forward(request,response);

        	}
        	
        	
        	return;
        	
        }else {

            System.out.println("username id is :"+ username);
            System.out.println("In edit servlet ");
            
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
                   	 
                   	 
                    	 String query = "select count(username) as C from cinema_last.user where username='"+username+"'";
                    	 Statement stat = conn.createStatement();
                        ResultSet rs = stat.executeQuery(query);
                        rs.next();
                   	   int user_exists = rs.getInt("C");
                   	System.out.println("numberrrr:"+user_exists);
                   	   if(user_exists==0) {
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
                   		   query = "INSERT INTO cinema_last.user ( username,password,create_time,type,DATE_OF_BIRTH) VALUES (?, ?, ?, ?, ?)";
                  	    statement = conn.prepareStatement(query);
                    	   
                  	    statement.setString(1, username);
                  	    statement.setString(2, hashedpass); 
                       statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                       statement.setInt(4, type);
                       if (type == 2) {
                           statement.setObject(5, birthday);
                       } else {
                           statement.setObject(5, null);
                       }
                  	    
                  	    // Process the result or perform any additional actions
                  	    int rowsInserted = statement.executeUpdate();
                  	    // Display success message or redirect to a success page
                  	    response.sendRedirect("main.jsp");}
                   	   else {
                   		request.setAttribute("result", "Username exists.");
                    	request.setAttribute("username", username);
                    	request.setAttribute("password", password);
                    	if(create.equals("c")) {
                    		RequestDispatcher rd = request.getRequestDispatcher("user_add.jsp");
                    		System.out.println("returning to add page");
                        	rd.forward(request,response);
                    	}else {
                    		RequestDispatcher rd = request.getRequestDispatcher("edit_user.jsp");
                    		System.out.println("returning to edit page");
                        	rd.forward(request,response);

                    	}
                   	   }
                  	 
                   	} catch (Exception e) {
                   	    // Handle any errors that occurred during the execution of the query
                   	    e.printStackTrace();
                   	    
                   	    // Display error message or redirect to an error page
                   	    response.sendRedirect("error.jsp");
                   	}
                }
            }catch (Exception e){
                System.out.println("nope");
                System.out.println(e.toString());
            }
        }
        
        
        
        
        
        
	}

}
