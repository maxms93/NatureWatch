package at.jku.se.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.session.SpeciesFacade;

/**
 * Servlet implementation class SpeciesWebController
 */
public class SpeciesWebController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpeciesWebController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getParameter("delSpec") != null) {
			DatabaseConnector db = new DatabaseConnector();
			SpeciesFacade.deleteSpeciesByAdmin(db.getConnection(), request.getParameter("delSpec"));
			response.sendRedirect("SpeciesList.jsp");
		}
		//doGet(request, response);
	}

}
