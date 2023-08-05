package servletpack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * Servlet implementation class add_provoli
 */
@WebServlet("/contentadmin/add_provoli")
public class add_provoli extends HttpServlet {
	private static final long serialVersionUID = 232231L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_provoli() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 
		 
        int MOVIE_ID = Integer.parseInt(request.getParameter("title"));
        String CINEMA = request.getParameter("cinema");
        String PREMIERE = request.getParameter("premier").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime premiereDateTime = LocalDateTime.parse(PREMIERE, formatter);       
        Timestamp premiereTimestamp = Timestamp.valueOf(premiereDateTime);
        boolean flag = true;
        LocalDate currentDate = LocalDate.now();
        boolean isBeforeToday = premiereTimestamp.toLocalDateTime().toLocalDate().isBefore(currentDate);
        if(isBeforeToday) {
        	flag=false;
            request.setAttribute("result", "Screening date is in the past.");
            RequestDispatcher rd = request.getRequestDispatcher("provoles_add.jsp");                    	
          	System.out.println("returning to edit provoli page");
         	rd.forward(request,response);
         	return;
        }
      
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
                	  
                	  String query = "select ID,LENGTH from cinema_last.films where ID='"+MOVIE_ID+"'";
                	  
                	  PreparedStatement statement3 = null;
                      statement3 = conn.prepareStatement(query);
                 	 ResultSet rs = statement3.executeQuery(query);
                 	 int mov_len ;
                 	Timestamp provoliEndDate = null;
                 	if(rs.next() == false) {
                  		
                  	    flag=false;
                  	}else {
                  		 
                  		mov_len = rs.getInt("LENGTH");
                  		provoliEndDate = new Timestamp(premiereTimestamp.getTime() + mov_len * 60 * 1000);
                        System.out.println("provoli end dtae:"+provoliEndDate);

                  		
                  	}
                 	 query = "SELECT P.ID, P.DAY, P.FILMS_ID, F.LENGTH " +
                             "FROM cinema_last.provoles AS P " +
                             "INNER JOIN cinema_last.films AS F ON P.FILMS_ID = F.ID " +
                             "WHERE P.CINEMAS_ID = '" + CINEMA + "' " ;
                                     	 
                 	rs.close();
                 	   statement3 = null;
                      statement3 = conn.prepareStatement(query);
                 	 rs = statement3.executeQuery(query);
                 
                 	 
                 	if(provoliEndDate!=null) { 
                      
                 	 int ID = -1;
                 	 String filmid= "";
                 	int len = -1;
                 	Timestamp day;
                    Timestamp existingEndDate ;
                 	 if(rs.next() == false) {
                  		System.out.println("ResultSet in empty in Java");
                  	                 	
                  	}else {
                  		 
                  	 ID = rs.getInt("ID");
                  	day = rs.getTimestamp("DAY");
                  	filmid = rs.getString("FILMS_ID");
                  	len = rs.getInt("LENGTH");
           	
                 existingEndDate = new Timestamp(day.getTime() + len * 60 * 1000);
               
                 if ((premiereTimestamp.after(day) && premiereTimestamp.before(existingEndDate)) || ( provoliEndDate.after(day)&& provoliEndDate.before(existingEndDate))) {
                	 
                     
                     request.setAttribute("result", "Screnings overlap. Start time:"+day+" End time:"+existingEndDate);
                     flag=false;
                     RequestDispatcher rd = request.getRequestDispatcher("provoles_add.jsp");                    	
                   	System.out.println("returning to edit provoli page");
                  	rd.forward(request,response);
                  	return;
            
                 }
                    while(rs.next()) {
                    	 ID = rs.getInt("ID");
                    	  day = rs.getTimestamp("DAY");
                       	filmid = rs.getString("FILMS_ID");
                       	len = rs.getInt("LENGTH");;
                                         }
                    existingEndDate = new Timestamp(day.getTime() + len * 60 * 1000);
                    if ((premiereTimestamp.after(day) && premiereTimestamp.before(existingEndDate)) || ( provoliEndDate.after(day)&& provoliEndDate.before(existingEndDate))) {
                     
                        request.setAttribute("result", "Screnings overlap. Start time:"+day+" End time:"+existingEndDate);
                        flag=false;
                    	RequestDispatcher rd = request.getRequestDispatcher("provoles_add.jsp");                    	
                    	
                    	rd.forward(request,response);
                      	return;

                       
                       
                    }
                  		
                  	}
                   	
                 	}
              	 }
                  catch (Exception e) {
                 
                 	    e.printStackTrace();
                 	    
                 	 
                 	    
                 	}
              }
          }catch (Exception e){
              System.out.println("nope");
              System.out.println(e.toString());
          }
        System.out.println(flag);
        if(flag) {
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
               	 
               	 String query = "select TITLE,ID from cinema_last.films where ID='"+MOVIE_ID+"' ";
                  	 //na to ftiaxw gia duplicates for 3d
               
                    
                    
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
             	
             	}
            	 
            
            	 
            	 PreparedStatement statement = null;
            	 HttpSession session = request.getSession();
            	 
            	 
           
            	    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            	    premiereDateTime = LocalDateTime.parse(PREMIERE, formatter);
            	    premiereTimestamp = Timestamp.valueOf(premiereDateTime);
            	   
            	   query = "INSERT INTO cinema_last.provoles ( FILMS_ID, FILMS_TITLE, CINEMAS_ID, DAY, NUMBER_OF_RESERVATIONS, SEATS, SEAT_NUM) VALUES (?, ?, ?, ?, ?, ?, ?)";
            	    statement = conn.prepareStatement(query);
            	  
            	    statement.setInt(1, MOVIE_ID );
            	    statement.setString(2, TITLE);
            	    statement.setString(3, CINEMA);            	
            	    statement.setTimestamp(4, premiereTimestamp);
            	    statement.setInt(5, 0 );
            	    statement.setString(6, SEAT_TXT);  
            	    statement.setInt(7, SEATS );
            	    
            	    
     
            	    statement.executeUpdate();
             	    response.sendRedirect("mainp.jsp");
            	    return;
            	 
               
               	} catch (Exception e) {
           
               	    e.printStackTrace();
               	    
               	  
               	    response.sendRedirect("main.jsp");
               	    return;
               	}
                
                
               
                
            }
        }catch (Exception e){
            System.out.println("nope");
            System.out.println(e.toString());
        }
        
        
        }
        response.sendRedirect("error.jsp");
   	    return;

	}

}
