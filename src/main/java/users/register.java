package users;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 123123L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
        	RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        	System.out.println("returning to register page");
        	rd.forward(request,response);
        	
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
             

                 	   if(user_exists==0) {
 
                   	password = password + "kk@";
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
                   	    
                
                   	    int rowsInserted = statement.executeUpdate();
            
                   	    response.sendRedirect("log-in.jsp");
                 	   }else{
                      		request.setAttribute("result", "Username exists.");
                        	request.setAttribute("username", username);
                        	request.setAttribute("password", password);
                            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                        		System.out.println("returning to register page");
                            	rd.forward(request,response);

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
