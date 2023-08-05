package servletpack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class transfer_user_data
 */
@WebServlet("/admin/transfer_user_data")
public class transfer_user_data extends HttpServlet {
	private static final long serialVersionUID = 42342L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfer_user_data() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user");
		String username = request.getParameter("id");
		
		System.out.println("transfer username is : " + username);
		
		if(username.equals("create_new_user") ) {
		
			
			 request.setAttribute("username", " ");
			 request.getRequestDispatcher("user_add.jsp").forward(request, response);

		

		}else {
			

			request.setAttribute("username", username);
			request.setAttribute("password", "");
			request.getRequestDispatcher("edit_user.jsp").forward(request, response);
			// response.sendRedirect("edit_movie.jsp");
		}
		
	}

}
