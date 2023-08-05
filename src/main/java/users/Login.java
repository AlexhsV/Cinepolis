package users;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 12312L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
                String username = request.getParameter("username");
                String password = request.getParameter("password");
        		request.setAttribute("usern",username);
                System.out.println(username);
                System.out.println(password);
                String query = "select username,password,type from cinema_last.user where username="+"'"+username+"'";
                Statement stat = conn.createStatement();
                boolean flag = true;
                
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
                
                try{
                	ResultSet rs = stat.executeQuery(query);
                	if(rs.next() == false) {
                		System.out.println("ResultSet in empty in Java");
                		
                		request.setAttribute("result", "No user found.");
                	}else {
                		String db_username = rs.getString(1);
                		String db_password = rs.getString(2);
                		int type = rs.getInt(3);
                		System.out.println(db_username==username);
                		 
                		if(username.equals(db_username)) {
                			System.out.println("In1");
                            	if(hashedpass.equals(db_password)) {
                            		System.out.println("In2");
                            		
                            		HttpSession session = request.getSession(); 
                            		session.setAttribute("username",username);
                            		session.setAttribute("type",type);
                            		if(type==1) {
                            			response.sendRedirect("contentadmin/main.jsp");
                            			flag=false;
                            		}else if(type==2){
                            			response.sendRedirect("client/main.jsp");
                            			flag=false;
                            		}else if(type==0){
                            			response.sendRedirect("admin/main.jsp");
                            			flag=false;
                            		}
                            		
                            	}else {
                            		System.out.println("In3");
                            		request.setAttribute("result", "Wrong Password.");
                            	
                            	}
                         }
                	}
                	if(flag) {
                		RequestDispatcher rd = request.getRequestDispatcher("log-in.jsp");
                    	rd.forward(request,response);
                	}
                	
                	
                	  
                	
                
                }catch(Exception e) {
                	 System.out.println("nope2");
                     System.out.println(e.toString());
                }
                
             
                
            }
            
           
            conn.close();
            
            
            
            }
        catch (Exception e){
            System.out.println("nope");
            System.out.println(e.toString());
        }
	}

}
