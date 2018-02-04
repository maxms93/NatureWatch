package at.jku.se.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.User;
import at.jku.se.session.UserFacade;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DatabaseConnector db = new DatabaseConnector();
		User u = UserFacade.getUser(db.getConnection(), request.getParameter("username"));
		db.close();
		
		ServletContext ctx = request.getServletContext();
		
		
		if(u != null //&& u.getUsername().equals(request.getParameter("username")) 
				&& u.getPassword().equals(request.getParameter("password"))
				&& u.isAdminflag() == true) {
			
			RequestDispatcher dispatcher = ctx.getRequestDispatcher("/WEB-INF/Sightings.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("/WEB-INF/menu.jsp");
		}
		else{
			RequestDispatcher dispatcher = ctx.getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("/WEB-INF/error.jsp");
		}
		
		
		
	}

}
