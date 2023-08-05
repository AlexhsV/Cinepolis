package servletpack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class transfer_provoles_data
 */
@WebServlet("/contentadmin/transfer_provoles_data")
public class transfer_provoles_data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfer_provoles_data() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int ID = Integer.parseInt(request.getParameter("id"));
		
		System.out.println(ID);
		
		if(ID<0) {
			 response.sendRedirect("provoles_add.jsp");
		}else {
			request.setAttribute("provoles_id", Integer.toString(ID));
			request.getRequestDispatcher("edit_provoli.jsp").forward(request, response);
			
		}
		
	}

}
