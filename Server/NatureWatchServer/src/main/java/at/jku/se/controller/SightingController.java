package at.jku.se.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.Sighting;
import at.jku.se.model.adapter.JsonAdapter;
import at.jku.se.session.SightingFacade;

@Path("/sighting")
public class SightingController {

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSighting(@PathParam("id") Long id) {

		DatabaseConnector db = new DatabaseConnector();

		Sighting s = SightingFacade.getSigthing(db.getConnection(), id);

		db.close();

		return JsonAdapter.write(s);

	}

	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSightingsFilter(
			@DefaultValue("%") @QueryParam("dateFrom") String dateFrom,
			@DefaultValue("%") @QueryParam("dateTo") String dateTo,
			// city einbauen
			// enabled default value Y
			@DefaultValue("%") @QueryParam("user") String user,
			@DefaultValue("%") @QueryParam("speciesId") String speciesId,
			@DefaultValue("%") @QueryParam("state") String state,
			@DefaultValue("%") @QueryParam("country") String country,
			@DefaultValue("Y") @QueryParam("enabled") String enabled)
			throws ParseException {

		Calendar cfrom = Calendar.getInstance();
		Calendar cto = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (dateFrom.equals("%")) {
			// cfrom.set(1970, 0, 1, 0, 0, 0);
			cfrom.setTime(new Date(Long.MIN_VALUE));
			cfrom.set(Calendar.YEAR, 1970);
		} else {
			cfrom.setTime(sdf.parse(dateFrom));
		}

		if (dateTo.equals("%")) {
			cto.setTime(new Date(Long.MIN_VALUE));
			cto.set(Calendar.YEAR, 2100);
		} else {
			cto.setTime(sdf.parse(dateTo));
		}

		Date dateFr = cfrom.getTime();
		Date dateT = cto.getTime();

		// System.out.println(sdf.format(dateFr));
		// System.out.println(sdf.format(dateT));
		DatabaseConnector db = new DatabaseConnector();

		List<Sighting> list = SightingFacade.getSightingFilter(
				db.getConnection(), dateFr, dateT, user, speciesId, state,
				country, enabled);

		db.close();

		return JsonAdapter.writeList(list);

	}

	@POST
	@Path("/create")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response createSighting(String newSighting) {
		DatabaseConnector db = new DatabaseConnector();

		SightingFacade.insertSighting(db.getConnection(), JsonAdapter.read(newSighting, new Sighting()));

		db.close();

		return Response.status(200).entity("Sighting created!").build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteSighting(@PathParam("id") Long id) {
		DatabaseConnector db = new DatabaseConnector();
		Sighting s = SightingFacade.getSigthing(db.getConnection(), id);

		SightingFacade.deleteSighting(db.getConnection(), s);
		
		db.close();

		return Response.status(200).entity("Sighting deleted!").build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response updateSighting(String s) {
		DatabaseConnector db = new DatabaseConnector();

		SightingFacade.updateSighting(db.getConnection(), JsonAdapter.read(s, new Sighting()));
		
		db.close();

		return Response.status(200).entity("Sighting updated!").build();
	}

	@GET
	@Path("/enabled")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSightingsFilterForAdmin(
			/*@DefaultValue("%") @QueryParam("dateFrom") Date dateFrom,
			@DefaultValue("%") @QueryParam("dateTo") Date dateTo,*/
			//city einbauen
			@DefaultValue("%") @QueryParam("user") String user,
			@DefaultValue("%") @QueryParam("speciesId") String speciesId,
			@DefaultValue("%") @QueryParam("state") String state,
			@DefaultValue("%") @QueryParam("country") String country,
			@DefaultValue("%") @QueryParam("enabled") String enabled) {

		DatabaseConnector db = new DatabaseConnector();
		
		List<Sighting> list = SightingFacade.getSightingFilterForAdmin(db.getConnection(), /*dateFrom, dateTo, */user,
				speciesId, state, country,enabled);
		
		db.close();

		return JsonAdapter.writeList(list);
	}

	@PUT
	@Path("/enabled/update")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response updateSightingAdmin(String list) {
		DatabaseConnector db = new DatabaseConnector();

		SightingFacade.updateSightingAdmin(db.getConnection(), (ArrayList<Sighting>) JsonAdapter.readList(list, new Sighting()));

		db.close();
		
		return Response.status(200).entity("Sighting updated!").build();
	}

	/*
	 * 
	 * Functions:
	 * 
	 * Erfassen Get mit Filtern Ändern Löschen
	 */
}
