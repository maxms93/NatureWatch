package at.jku.se.controller;

import java.util.ArrayList;
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
import at.jku.se.session.SightingFacade;

@Path("/sighting")
public class SightingController {

	
	@GET
    @Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Sighting getSighting(@PathParam("id") Long id) {
  
		DatabaseConnector db = new DatabaseConnector();
		
		Sighting s = SightingFacade.getSigthing(db.getConnection(), id);
		
		db.close();
  
        return s;
  
    }
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sighting> getSightingsFilter(
			/*@DefaultValue("%") @QueryParam("dateFrom") Date dateFrom,
			@DefaultValue("%") @QueryParam("dateTo") Date dateTo,*/
			@DefaultValue("%") @QueryParam("user") String user,
			@DefaultValue("%") @QueryParam("speciesId") String speciesId,
			@DefaultValue("%") @QueryParam("state") String state,
			@DefaultValue("%") @QueryParam("country") String country) {

		DatabaseConnector db = new DatabaseConnector();
		
		List<Sighting> list = SightingFacade.getSightingFilter(db.getConnection(), /*dateFrom, dateTo, */user,
				speciesId, state, country);

		db.close();
		
		return list;

	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSighting(Sighting newSighting)
	{
		DatabaseConnector db = new DatabaseConnector();
		
		SightingFacade.insertSighting(db.getConnection(), newSighting);
		
		db.close();
		
		return Response.status(200).entity("Sighting created!").build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSighting(@PathParam("id") Long id)
	{
		DatabaseConnector db = new DatabaseConnector();
		Sighting s = SightingFacade.getSigthing(db.getConnection(), id);
		
		SightingFacade.deleteSighting(db.getConnection(), s);
		
		db.close();
		
		return Response.status(200).entity("Sighting deleted!").build();
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSighting(Sighting s)
	{
		DatabaseConnector db = new DatabaseConnector();
				
		SightingFacade.updateSighting(db.getConnection(), s);
		
		db.close();
		  
		return Response.status(200).entity("Sighting updated!").build();
	}
	
	@GET
	@Path("/enabled")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sighting> getSightingsFilterForAdmin(
			/*@DefaultValue("%") @QueryParam("dateFrom") Date dateFrom,
			@DefaultValue("%") @QueryParam("dateTo") Date dateTo,*/
			@DefaultValue("%") @QueryParam("user") String user,
			@DefaultValue("%") @QueryParam("speciesId") String speciesId,
			@DefaultValue("%") @QueryParam("state") String state,
			@DefaultValue("%") @QueryParam("country") String country,
			@DefaultValue("%") @QueryParam("enabled") String enabled) {

		DatabaseConnector db = new DatabaseConnector();
		
		List<Sighting> list = SightingFacade.getSightingFilterForAdmin(db.getConnection(), /*dateFrom, dateTo, */user,
				speciesId, state, country,enabled);
		
		db.close();

		return list;
	}
	
	@PUT
	@Path("/enabled/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSightingAdmin(ArrayList<Sighting> slist)
	{
		DatabaseConnector db = new DatabaseConnector();
				
		SightingFacade.updateSightingAdmin(db.getConnection(), slist);
		
		db.close();
		
		return Response.status(200).entity("Sighting updated!").build();
	}
	
}
