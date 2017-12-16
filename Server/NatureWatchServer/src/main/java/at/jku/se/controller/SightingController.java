package at.jku.se.controller;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.Sighting;
import at.jku.se.model.User;
import at.jku.se.session.SightingFacade;
import at.jku.se.session.UserFacade;

@Path("/sighting")
public class SightingController {

	
	@GET
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Sighting getSighting(@PathParam("id") int id) {
  
		DatabaseConnector db = new DatabaseConnector();
		
		Sighting s = SightingFacade.getSigthing(db.getConnection(), id);
  
        return s;
  
    }
	
		
	
	
	/*
	 
	 Functions:
	 	
	 	Erfassen
	 	Get mit Filtern
	 	Ändern
	 	Löschen
	 	
	 */
}
