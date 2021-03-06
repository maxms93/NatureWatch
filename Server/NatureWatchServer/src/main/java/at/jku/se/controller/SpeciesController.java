package at.jku.se.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.owlike.genson.Genson;

import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.Species;
import at.jku.se.model.adapter.JsonAdapter;
import at.jku.se.session.SpeciesFacade;


@Path("/species")
public class SpeciesController {

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSpecies(@PathParam("id") int id) {

		DatabaseConnector db = new DatabaseConnector();

		Species s = SpeciesFacade.getSpecies(db.getConnection(), id);

		db.close();
		    
		return JsonAdapter.write(s);

	}

	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSpecies(
			@DefaultValue("%") @QueryParam("species") String species,
			@DefaultValue("%") @QueryParam("category") String category,
			@DefaultValue("%") @QueryParam("latinName") String latinName,
			@DefaultValue("%") @QueryParam("normalName") String normalName) {

		DatabaseConnector db = new DatabaseConnector();

		Date date = new Date();

		
		List<Species> list = SpeciesFacade.getSpecies(db.getConnection(),
				species, category, latinName, normalName, date.getMonth()+1);

		db.close();
		
		return JsonAdapter.writeList(list);

	}

}
