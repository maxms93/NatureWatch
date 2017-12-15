package at.jku.se.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import at.jku.se.session.UserFacade;
import at.jku.se.database.DatabaseConnector;
import at.jku.se.model.User;

@Path("/user")
public class UserController {
	
	
	@GET
    @Path("/{name}")
    public Response getPassword(@PathParam("name") String name) {
  
		DatabaseConnector db = new DatabaseConnector();
		
		User u = UserFacade.getUser(db.getConnection(), name);
        String output = "Welcome   : " + u.getPassword();
  
        return Response.status(200).entity(output).build();
  
    }
	/*Functions:
		
		Anmeleden
		Registrieren
		*/

}
