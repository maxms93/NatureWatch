package at.jku.se.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.Species;
import at.jku.se.session.SpeciesFacade;

/**
 * Servlet implementation class SaveController
 */
@WebServlet
@MultipartConfig
public class SaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response)
		DatabaseConnector db = new DatabaseConnector();
		ServletContext ctx = request.getServletContext();
		
		Species s = new Species(0, null, null, null, null, null, 0, 0, null,null,null,null,null,null,null,null,null,null);

		if(request.getParameter("id").equals("")){
				request.setAttribute("errmsgID", "ID Feld ist leer !"); 
				request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}	
		else if(request.getParameter("species").equals("")){
			request.setAttribute("errmsgSpecies", "Species Feld ist leer !"); 
			  request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}
		else if(request.getParameter("category").equals("")){
			request.setAttribute("errmsgCategory", "Species Feld ist leer !"); 
			  request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}
		else if(request.getParameter("latinname").equals("")){
			request.setAttribute("errmsgLatinname", "Lateinischer Name Feld ist leer !"); 
			  request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}
		else if(request.getParameter("normalname").equals("")){
			request.setAttribute("errmsgNormalname", "umgangspr. Name Feld ist leer !"); 
			  request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}
		else if(request.getParameter("description").equals("")){
			request.setAttribute("errmsgDescription", "Beschreibungsfeld ist leer !"); 
			  request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}
		else if(request.getParameter("validfrom").equals("")){
			request.setAttribute("errmsgValidfrom", "Datum von Feld ist leer !"); 
			  request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}
		else if(request.getParameter("validto").equals("")){
			request.setAttribute("errmsgValidto", "Datum bis Feld ist leer !"); 
			  request.getRequestDispatcher("add_animal.jsp").forward(request, response);
		}
		else{
			s.setId(Integer.parseInt(request.getParameter("id")));
			s.setSpecies(request.getParameter("species"));
			s.setCategory(request.getParameter("category"));
			s.setLatinName(request.getParameter("latinname"));
			s.setNormalName(request.getParameter("normalname"));
			s.setDescription(request.getParameter("description"));
	
			s.setValidFrom(Long.parseLong(request.getParameter("validfrom")));
			s.setValidTo(Long.parseLong(request.getParameter("validto")));
			
			Part filePart1 = request.getPart("bild1");
			InputStream inputStream1 = filePart1.getInputStream();
			BufferedImage img1 = ImageIO.read(inputStream1);
			ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
			if(img1 != null){
			ImageIO.write(img1, "jpg", baos1);
			byte[] bild1 =baos1.toByteArray();
			s.setImage1(bild1);
			}
			
			Part filePart2 = request.getPart("bild2");
			InputStream inputStream2 = filePart2.getInputStream();
			BufferedImage img2 = ImageIO.read(inputStream2);
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			if(img2 != null){
			ImageIO.write(img2, "jpg", baos2);
			byte[] bild2 =baos2.toByteArray();
			s.setImage2(bild2);
			}
			
			Part filePart3 = request.getPart("bild3");
			InputStream inputStream3 = filePart3.getInputStream();
			BufferedImage img3 = ImageIO.read(inputStream3);
			ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
			if(img3 != null){
			ImageIO.write(img3, "jpg", baos3);
			byte[] bild3 =baos3.toByteArray();
			s.setImage3(bild3);
			}
			
			Part filePart4 = request.getPart("bild4");
			InputStream inputStream4 = filePart4.getInputStream();
			BufferedImage img4 = ImageIO.read(inputStream4);
			ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
			if(img4 != null){
			ImageIO.write(img4, "jpg", baos4);
			byte[] bild4 =baos4.toByteArray();
			s.setImage4(bild4);
			}
			
			Part filePart5 = request.getPart("bild5");
			InputStream inputStream5 = filePart5.getInputStream();
			BufferedImage img5 = ImageIO.read(inputStream5);
			ByteArrayOutputStream baos5 = new ByteArrayOutputStream();
			if(img5 != null){
			ImageIO.write(img5, "jpg", baos5);
			byte[] bild5 =baos5.toByteArray();
			s.setImage5(bild5);
			}
			
			
			
		}
		
		if(Integer.toString(s.getId()) != null && s.getSpecies() != null && s.getCategory() != null && s.getLatinName() != null && s.getNormalName()!=null 
				&& s.getDescription() != null && Long.toString(s.getValidFrom()) != null  && Long.toString(s.getValidTo()) != null){
		SpeciesFacade.insertSpeciesByAdmin(db.getConnection(), s);
		db.close();
		
		RequestDispatcher dispatcher = ctx.getRequestDispatcher("/WEB-INF/SpeciesList.jsp");
		dispatcher.forward(request, response);
		}
		

	}
}
