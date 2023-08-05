package servletpack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class add_res
 */
@WebServlet("/client/add_res")
public class add_res extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_res() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   int provoli_id = Integer.parseInt(request.getParameter("provoli_id").toString());
         
        try {
        	 String[] seats = request.getParameterValues("selectedSeats");
             System.out.println(seats);
          
       
             if(seats!=null) {
         
         
         try {
        	 String RESOURCE_NAME = "jdbc/cinema_last";
        	
             Class.forName("com.mysql.jdbc.Driver");  
             Context initContext = new InitialContext();
             Context envContext = (Context) initContext.lookup("java:/comp/env");
             
             DataSource ds = (DataSource) envContext.lookup(RESOURCE_NAME);
             Connection conn = ds.getConnection();
   
             PreparedStatement statement;
             HttpSession session = request.getSession();
             if   (seats != null) {
            	 String query = "SELECT NUMBER_OF_RESERVATIONS,SEATS FROM cinema_last.provoles WHERE ID='"+provoli_id+"'";
            	 statement = conn.prepareStatement(query);
             	 ResultSet rs = statement.executeQuery(query);
             	 String totals ="";
                 int seatn =-1;
             	 while(rs.next()) {
               		
             		seatn = rs.getInt("NUMBER_OF_RESERVATIONS");
             		totals = rs.getString("SEATS");
                 }
             	 
             	char[] charArray = totals.toCharArray();
                    int temp;
            	    for (String seat : seats) {
            	        
            	        System.out.println("Selected seat: " + seat);
            	        
            	        
                   	    query = "INSERT INTO cinema_last.reservations ( username,PROVOLES_ID,SEAT ) VALUES (?, ?, ?)";
                   	    statement = conn.prepareStatement(query);
                   	  
                   	    statement.setString(1, String.valueOf(session.getAttribute("username")));
                   	    statement.setInt(2, provoli_id);
                   	    statement.setInt(3, Integer.parseInt(seat));
                   	    
                      	statement.executeUpdate();
                      	
                      	seatn+=1;
                      	
                      	charArray[Integer.parseInt(seat)-1] = '1';

                      	
            	        
            	    }
            	     totals = new String(charArray);
            	    if(seatn != -1){
            	    	 query = "UPDATE cinema_last.provoles SET SEATS = ?, NUMBER_OF_RESERVATIONS = ? WHERE ID = ?";
                    	    statement = conn.prepareStatement(query);

                 	    statement.setString(1, totals);
                    	    statement.setInt(2, seatn);
                    	    statement.setInt(3, provoli_id);
                       	statement.executeUpdate();
            	    }
            	   

            	}
            
           
         
       
            
             response.sendRedirect("succ_res.jsp?id=" + provoli_id);
             return;
             
         }catch (Exception e){
             System.out.println("nope");
             System.out.println(e.toString());
         }
         
        } response.sendRedirect("fail_res.jsp?id=" + provoli_id);
        return;
             
             
        }catch (Exception e){
            System.out.println("nope");
            System.out.println(e.toString());
            response.sendRedirect("fail_res.jsp?id=" + provoli_id);
        }
       
         
	}

}
