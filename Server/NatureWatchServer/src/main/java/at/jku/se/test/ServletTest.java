package at.jku.se.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;




import at.jku.se.model.Species;
import at.jku.se.model.User;

public class ServletTest {

	public static void main(String[] args) {

		User u = new User("max4", "1234", "test", "2", "ds", "dsd", "fdf", true);
		
		String postUrl = "http://localhost:9356/NatureWatchServer/user/create";
		String getUrl = "http://localhost:9356/NatureWatchServer/species/1";

		Client client = Client.create();
		
		// GET
		WebResource webResource = client.resource(getUrl);
		Species s = webResource.type("application/json").get(Species.class);
		
		
		//POST

		webResource = client.resource(postUrl);
		ClientResponse  response = webResource.type("application/json").post(
				ClientResponse.class, u);
		
		System.out.println("Response from the Server: ");
		System.out.println(response);

		/*
		 * Client client = ClientBuilder.newClient(); String r = client
		 * .target("http://localhost:9356/NatureWatchServer/user")
		 * .request(MediaType.APPLICATION_JSON) .accept(MediaType.TEXT_PLAIN)
		 * .post(Entity.json(u), String.class);
		 */

	}

}
