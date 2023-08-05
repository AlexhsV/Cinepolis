package servletpack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class transfer_movie_data
 */
@WebServlet("/contentadmin/transfer_movie_data")
public class transfer_movie_data extends HttpServlet {
	private static final long serialVersionUID = 123L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfer_movie_data() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ID = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();

	
		if(session.getAttribute("username")==null){
			response.sendRedirect("log-in.jsp");
			return;
		}
		
		if(ID<0) {
			 response.sendRedirect("movie_add.jsp");
		}else {
			String typestring = session.getAttribute("type").toString();
			int type_int = Integer.valueOf(typestring);
			if(type_int==1) {
				request.setAttribute("movie_id", Integer.toString(ID));
				request.getRequestDispatcher("edit_movie.jsp").forward(request, response);
				
			}
		}
		
	}

}
