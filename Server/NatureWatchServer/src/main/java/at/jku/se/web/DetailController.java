package at.jku.se.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.Sighting;
import at.jku.se.session.SightingFacade;
import at.jku.se.session.SpeciesFacade;

/**
 * Servlet implementation class DetailController
 */
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailController() {
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
		if (request.getParameter("filter") != null) {
			String user;
			String speciesId;
			String city;
			String country;
			String enabled;
			String dateFrom;
			String dateTo;
			
			user = request.getParameter("userName");
			speciesId = request.getParameter("speciesName");
			city = request.getParameter("speciesName");
			country = request.getParameter("cityName");
			enabled = request.getParameter("itemEnabled");
			dateFrom = request.getParameter("datefrom");
			dateTo = request.getParameter("dateto");
			
			ServletContext ctx = request.getServletContext();

			request.setAttribute("userName", user);
			request.setAttribute("speciesItem", speciesId);
			request.setAttribute("cityName", city);
			request.setAttribute("countryName", country);
			request.setAttribute("itemEnabled", enabled);
			request.setAttribute("datefrom", dateFrom);
			request.setAttribute("dateto", dateTo);
			RequestDispatcher dispatcher = ctx.getRequestDispatcher("/Sightings.jsp");
			dispatcher.forward(request, response);

		}
		else if (request.getParameter("name") != null) {
			ServletContext ctx = request.getServletContext();
			String id = request.getParameter("name");
			//System.out.println(id);
			request.setAttribute("name", id);
			RequestDispatcher dispatcher = ctx.getRequestDispatcher("/SightingsDetail.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("checkEna") != null)
		{
			DatabaseConnector db = new DatabaseConnector();
			//response.sendRedirect("index.jsp");
			String[] selectItemsId = request.getParameterValues("selectedItemId");
			//System.out.println(selectItemsId[0]);
			ArrayList<Sighting> sightingsList = new ArrayList<Sighting>();
			Sighting sighting;
			if (selectItemsId != null) {
				for (int i = 0; i < selectItemsId.length; i++) {
					sighting = SightingFacade.getSigthingAdmin(db.getConnection(), Long.parseLong(selectItemsId[i]));
					if (sighting != null) {
						sightingsList.add(sighting);
					}
				}
				
				for(Sighting s : sightingsList)
				{
					s.setEnabled(true);
				}
				SightingFacade.updateSightingAdmin(db.getConnection(), sightingsList);
				db.close();
				response.sendRedirect("Sightings.jsp");
			}
		}
		else if(request.getParameter("checkDel") != null)
		{
			DatabaseConnector db = new DatabaseConnector();
			//response.sendRedirect("index.jsp");
			String[] selectItemsId = request.getParameterValues("selectedItemId");
			//System.out.println(selectItemsId[0]);
			if (selectItemsId != null) {
				SightingFacade.deleteSightingByAdmin(db.getConnection(), selectItemsId);
				db.close();
				response.sendRedirect("Sightings.jsp");
			}
		}
		
		
		//response.sendRedirect("SightingsDetail.jsp");
	}
	
	public static String getUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String userName;
		if(request.getParameter("userName") != null && request.getParameter("userName") != "")
		{
			userName = request.getParameter("userName");
		}
		else
		{
			userName = "%";
		}
		return userName; 
	}
	public static String getSpeciesId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String speciesId;
		String speciesName = request.getParameter("speciesItem");
		DatabaseConnector db = new DatabaseConnector();
		String name = request.getParameter("speciesItem");
		if(name != null && !name.equals("-"))
		{
			speciesId = String.valueOf(SpeciesFacade.getSpeciesIdForAdmin(db.getConnection(), speciesName));
		}
		else
		{
			speciesId = "%";
		}
		db.close();
		return speciesId; 
	}
	
	public static String getGemeindeName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String cityName;
		if(request.getParameter("cityName") != null && request.getParameter("cityName") != "")
		{
			cityName = request.getParameter("cityName");
		}
		else
		{
			cityName = "%";
		}
		return cityName; 
	}
	
	public static String getCountryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String countryName;
		if(request.getParameter("countryName") != null && request.getParameter("countryName") != "")
		{
			countryName = request.getParameter("countryName");
		}
		else
		{
			countryName = "%";
		}
		return countryName; 
	}
	
	public static String getEnabled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String enable = request.getParameter("itemEnabled");
		if (enable == null || enable.equals("B")) {
			return "%";
		}
		else if(enable.equals("Y"))
		{
			return "Y";
		}
		else
		{
			return "N";
		}
	}
	
	@SuppressWarnings({"static-access" })
	public static Date getDateFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Calendar c = Calendar.getInstance();
		Date dateFrom = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		String[] parseDate;
		String strDateFrom = request.getParameter("datefrom");
		if (strDateFrom != null && strDateFrom != "") {
			parseDate = strDateFrom.split("/");
			c.set(Integer.parseInt(parseDate[2]), Integer.parseInt(parseDate[1])-1,
					Integer.parseInt(parseDate[0]),0, 0, 0);
			/*c.set(Integer.parseInt(parseDate[2]), Integer.parseInt(parseDate[0])-1,
					Integer.parseInt(parseDate[1]),0, 0, 0);*/
			/*dateFrom.setMonth(Integer.parseInt(parseDate[0])-1);
			dateFrom.setDate(Integer.parseInt(parseDate[1]));
			dateFrom.setYear(Integer.parseInt(parseDate[2])-1900);
			dateFrom.setHours(0);
			dateFrom.setMinutes(0);
			dateFrom.setSeconds(0);*/
			dateFrom = c.getTime();
			return dateFrom;
		}
		else
		{
			/*c.setTime(new Date(Long.MIN_VALUE));
			c.set(Calendar.YEAR, 1970);*/
			c.set(2010, 0, 1);
			dateFrom = c.getTime();

			/*dateFrom.setMonth(0);
			dateFrom.setDate(1);
			dateFrom.setYear(70);
			dateFrom.setHours(0);
			dateFrom.setMinutes(0);
			dateFrom.setSeconds(0);*/

			return dateFrom;
		}
	}
	
	@SuppressWarnings({"static-access" })
	public static Date getDateTo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Calendar c = Calendar.getInstance();
		Date dateTo = new Date();
		String[] parseDate;
		String strDateTo = request.getParameter("dateto");
		if (strDateTo != null && strDateTo != "") {
			parseDate = strDateTo.split("/");
			c.set(Integer.parseInt(parseDate[2]), Integer.parseInt(parseDate[1])-1,Integer.parseInt(parseDate[0]),0, 0, 0);
			/*c.set(Integer.parseInt(parseDate[2]), Integer.parseInt(parseDate[0])-1,
					Integer.parseInt(parseDate[1]),0, 0, 0);*/
			/*dateTo.setMonth(Integer.parseInt(parseDate[1])-1);
			dateTo.setDate(Integer.parseInt(parseDate[2]));
			dateTo.setYear(Integer.parseInt(parseDate[0])-1900);
			dateTo.setHours(0);
			dateTo.setMinutes(0);
			dateTo.setSeconds(0);*/
			dateTo = c.getTime();
			return dateTo;
		}
		else
		{
			/*c.setTime(new Date(Long.MIN_VALUE));
			c.set(Calendar.YEAR, 2100);
			dateTo = c.getTime();*/
			dateTo = c.getInstance().getTime();
			return dateTo;
		}
	}
}
