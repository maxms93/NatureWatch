package at.jku.se.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.Sighting;
import at.jku.se.session.SightingFacade;

/**
 * Servlet implementation class EnableServlet
 */
public class EnableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnableServlet() {
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
		//doGet(request, response);
		DatabaseConnector db = new DatabaseConnector();
		Sighting s = SightingFacade.getSigthingAdmin(db.getConnection(), Long.parseLong(request.getParameter("enableSub")));
		//System.out.println(s.isEnabled());
		//System.out.println(s.getUser());
		s.setEnabled(true);
		//SightingFacade.updateSighting(db.getConnection(), s);
		ArrayList<Sighting> slist = new ArrayList<Sighting>();
		slist.add(s);
		SightingFacade.updateSightingAdmin(db.getConnection(), slist);
		response.sendRedirect("Sightings.jsp");
	}

}
