package at.jku.se.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import at.jku.se.model.Sighting;
import at.jku.se.model.Species;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owlike.genson.Genson;

public class gettest {

	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static String readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    Object o = new URL(url).getContent();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      return jsonText;
	    } finally {
	      is.close();
	    }
	  }

	  public static void main(String[] args) throws IOException, JSONException {
	    String json = readJsonFromUrl("http://localhost:9356/NatureWatchServer/rest/species/1");
	    System.out.println(json.toString());
	    
	    Genson genson = new Genson();

	    Species species = genson.deserialize(json, Species.class);
	    
	    System.out.println(species.getCategory());
	  }

}
