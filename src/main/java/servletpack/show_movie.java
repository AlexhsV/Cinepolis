package servletpack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class show_movie
 */
@WebServlet("/client/show_movie")
public class show_movie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public show_movie() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ID = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();

	
		if(session.getAttribute("username")==null ){
			response.sendRedirect("log-in.jsp");
			return;
		}
		
		if(ID<0) {
			 response.sendRedirect("movie_add.jsp");
		}else {
			String typestring = session.getAttribute("type").toString();
			
			
				request.setAttribute("movie_id", Integer.toString(ID));
				request.getRequestDispatcher("view_movie.jsp").forward(request, response);
			
		}
		
	}

}
