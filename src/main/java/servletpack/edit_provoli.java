package servletpack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
 * Servlet implementation class edit_provoli
 */
@WebServlet("/contentadmin/edit_provoli")
public class edit_provoli extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit_provoli() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 
		 
		 
        int MOVIE_ID = Integer.parseInt(request.getParameter("title"));
        String CINEMA = request.getParameter("cinema");
        String PREMIERE = request.getParameter("premier").toString();
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
                System.out.println("success");
             
               
                try {
               	 
               	 String query = "select TITLE,ID from cinema_last.films where ID='"+MOVIE_ID+"' ";
              
               
                    
                    
                    PreparedStatement statement2 = null;
                    statement2 = conn.prepareStatement(query);
               	 ResultSet rs = statement2.executeQuery(query);
               	 String TITLE = "Null title";
               	 
               	 if(rs.next() == false) {
                		System.out.println("ResultSet in empty in Java");
                		
                	
                	}else {
                		 
              
                	 TITLE = rs.getString("TITLE");
                		
                	}
               	 
               	 query = "select ID,SEATS from cinema_last.cinemas where ID='"+CINEMA+"' ";
                
              statement2 = null;
               statement2 = conn.prepareStatement(query);
              rs = statement2.executeQuery(query);
         	 String SEAT_TXT ="";
              int SEATS=-1;
            	 if(rs.next() == false) {
             		System.out.println("ResultSet in empty in Java");            		
             	
             	}else {
             		
           
             	 SEATS = rs.getInt("SEATS");
             	 
             	 for(int i=0;i<SEATS;i++) {
             		 SEAT_TXT=SEAT_TXT + "0";
             	 }
             	System.out.println("SEATS ARE : " +SEATS+ "| NUMBER OF 0 IN TXT: "+ SEAT_TXT.length());
             	}
            	 System.out.println("title:"+TITLE);
            	 System.out.println("SEATS:"+SEATS);
            
            	 
            	 PreparedStatement statement = null;
            	 HttpSession session = request.getSession();
            	 
            	 
            
            	
            	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
            	   LocalDateTime premiereDateTime = LocalDateTime.parse(PREMIERE, formatter);
            	   Timestamp premiereTimestamp = Timestamp.valueOf(premiereDateTime);
            	   
            	   query = "UPDATE cinema_last.provoles SET FILMS_ID = ?, FILMS_TITLE = ?, CINEMAS_ID = ?, DAY = ?, NUMBER_OF_RESERVATIONS = ?, SEATS = ?, SEAT_NUM = ? WHERE ID = ?";
            	    statement = conn.prepareStatement(query);
            	  
            	    statement.setInt(1, MOVIE_ID );
            	    statement.setString(2, TITLE);
            	    statement.setString(3, CINEMA);            	
            	    statement.setTimestamp(4, premiereTimestamp);
            	    statement.setInt(5, 0 );
            	    statement.setString(6, SEAT_TXT);  
            	    statement.setInt(7, SEATS );
            	    statement.setInt(8, ID );
            	    
            	    
      
            	    int rowsInserted = statement.executeUpdate();
            	
            	    response.sendRedirect("mainp.jsp");
            	 
               
               	} catch (Exception e) {
               	  
               	    e.printStackTrace();
               	    
             
               	    response.sendRedirect("mainp.jsp");
               	}
                
                
               
                
            }
        }catch (Exception e){
            System.out.println("nope");
            System.out.println(e.toString());
        }
        
        
        
	}

}
