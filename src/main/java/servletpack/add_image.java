package servletpack;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.NamingException;


@WebServlet("/contentadmin/add_image")
@MultipartConfig
public class add_image extends HttpServlet {
    private static final long serialVersionUID = 142234L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("into servlet");
        int movieId = Integer.parseInt(request.getParameter("idnumber"));
        Part photoPart = request.getPart("photo");
        Part photo2Part = request.getPart("photo2");
       
   
        
      //  String filename = UUID.randomUUID().toString() + ".jpg";
        String filename = movieId + ".jpg";
        String imagePath = "/CinepolisBackEnd/src/main/webapp/assets/movie_backgrounds/" + filename;
     
        String absolutePath = "C:\\Users\\Kostas\\Eclipse23\\CinepolisBackEnd\\src\\main\\webapp\\assets\\movie_backgrounds\\"+ filename ;
        System.out.println("PATH IS:"+imagePath);
        System.out.println("PATH IS:"+absolutePath);
        try (InputStream inputStream = photo2Part.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(absolutePath)) {

               BufferedImage image = ImageIO.read(inputStream);
               ImageIO.write(image, "jpg", outputStream);
           } catch (IOException e) {
               e.printStackTrace();
               response.sendRedirect("main.jsp");
               return;
           }
        
        InputStream photoInputStream = photoPart.getInputStream();

        try  
        
        {       String RESOURCE_NAME = "jdbc/cinema_last";
                System.out.println("in");
                Class.forName("com.mysql.jdbc.Driver");  
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
        
                DataSource ds = (DataSource) envContext.lookup(RESOURCE_NAME);
                Connection conn = ds.getConnection();
                String query = "UPDATE cinema_last.films SET POSTER = ?, imageloc = ? WHERE id = ?";
             try (PreparedStatement statement = conn.prepareStatement(query)) {
            	statement.setBlob(1, photoInputStream);
                statement.setInt(3, movieId);
                statement.setString(2, filename.toString());
                statement.executeUpdate();
            }
            response.sendRedirect("main.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("main.jsp");
        } catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (NamingException e) {
		
			e.printStackTrace();
		}
    }

    
}
