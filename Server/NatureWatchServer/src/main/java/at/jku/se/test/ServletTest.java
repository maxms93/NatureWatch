package at.jku.se.test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.owlike.genson.Genson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import at.jku.se.database.FileHandler;
import at.jku.se.model.Species;
import at.jku.se.model.User;
import at.jku.se.model.adapter.JsonAdapter;

public class ServletTest {

	public static void main(String[] args) {

		try {

			String url = "http://localhost:9356/NatureWatchServer/rest/species/list";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			Gson gson = new Gson();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			//Species species = JsonAdapter.read(response.toString(), new Species());
			List<Species> l = JsonAdapter.readList(response.toString(), new Species());
			
			//System.out.println(species.getCategory());
			System.out.println("test");
			//FileHandler ftp = new FileHandler();
			
			//ftp.putFile("max", species.getImage1());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
