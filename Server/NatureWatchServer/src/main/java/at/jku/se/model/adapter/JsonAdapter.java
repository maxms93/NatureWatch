package at.jku.se.model.adapter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.util.Base64;

import at.jku.se.model.Sighting;
import at.jku.se.model.Species;
import at.jku.se.model.User;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JsonAdapter {

	public static <T> String write(T value) {

		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);

		try {

			if (value instanceof Species) {
				writeSpecies(writer, (Species) value);
			} else if (value instanceof Sighting) {
				writeSighting(writer, (Sighting) value);
			} else if (value instanceof User) {
				writeUser(writer, (User) value);
			}

			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	public static <T> T read(String value, T type) {

		JsonReader reader = new JsonReader(new StringReader(value));

		try {

			if (type instanceof Species) {
				return (T) readSpecies(reader);
			} else if (type instanceof Sighting) {
				return (T) readSighting(reader);
			} else if (type instanceof User) {
				return (T) readUser(reader);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static <T> String writeList(List<T> list) {
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);

		try {

			writer.beginArray();

			for (T value : list) {

				if (value instanceof Species) {
					writeSpecies(writer, (Species) value);
				} else if (value instanceof Sighting) {
					writeSighting(writer, (Sighting) value);
				} else if (value instanceof User) {
					writeUser(writer, (User) value);
				}

			}

			writer.endArray();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sw.toString();
	}

	public static <T> List<T> readList(String value, T type) {

		JsonReader reader = new JsonReader(new StringReader(value));
		ArrayList<T> result = new ArrayList<T>();

		try {

			reader.beginArray();

			while (reader.hasNext()) {

				if (type instanceof Species) {
					result.add((T) readSpecies(reader));
				} else if (type instanceof Sighting) {
					result.add((T) readSighting(reader));
				} else if (type instanceof User) {
					result.add((T) readUser(reader));
				}

			}

			reader.endArray();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;

	}

	private static Species readSpecies(JsonReader reader) throws IOException {

		int id = -1;
		String species = null;
		String category = null;
		String latinName = null;
		String normalName = null;
		String description = null;
		int validFrom = -1;
		int validTo = -1;
		byte[] image1 = null;
		byte[] image2 = null;
		byte[] image3 = null;
		byte[] image4 = null;
		byte[] image5 = null;

		reader.beginObject();

		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("id")) {
				id = reader.nextInt();
			} else if (name.equals("species")) {
				species = reader.nextString();
			} else if (name.equals("category")) {
				category = reader.nextString();
			} else if (name.equals("latinName")) {
				latinName = reader.nextString();
			} else if (name.equals("normalName")) {
				normalName = reader.nextString();
			} else if (name.equals("description")) {
				description = reader.nextString();
			} else if (name.equals("validFrom")) {
				validFrom = reader.nextInt();
			} else if (name.equals("validTo")) {
				validTo = reader.nextInt();
			} else if (name.equals("image1")) {
				image1 = Base64.decodeBase64(reader.nextString());
			} else if (name.equals("image2")) {
				image2 = Base64.decodeBase64(reader.nextString());
			} else if (name.equals("image3")) {
				image3 = Base64.decodeBase64(reader.nextString());
			} else if (name.equals("image4")) {
				image4 = Base64.decodeBase64(reader.nextString());
			} else if (name.equals("image5")) {
				image5 = Base64.decodeBase64(reader.nextString());
			}
		}

		reader.endObject();

		return new Species(id, species, category, latinName, normalName,
				description, validFrom, validTo, image1, image2, image3,
				image4, image5);
	}

	private static Sighting readSighting(JsonReader reader) throws IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

		Long id = (long) -1;
		int speciesId = -1;
		String description = null;
		double longitude = 0.0;
		double latitude = 0.0;
		int seaLevel = -1;
		String state = null;
		String country = null;
		String city = null;
		String user = null;
		Date dateTime = null;
		boolean enabled = false;
		byte[] image1 = null;
		byte[] image2 = null;
		byte[] image3 = null;

		reader.beginObject();

		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("id")) {
				id = reader.nextLong();
			} else if (name.equals("speciesId")) {
				speciesId = reader.nextInt();
			} else if (name.equals("description")) {
				description = reader.nextString();
			} else if (name.equals("longitude")) {
				longitude = reader.nextDouble();
			} else if (name.equals("latitude")) {
				latitude = reader.nextDouble();
			} else if (name.equals("seaLevel")) {
				seaLevel = reader.nextInt();
			} else if (name.equals("state")) {
				state = reader.nextString();
			} else if (name.equals("country")) {
				country = reader.nextString();
			} else if (name.equals("city")) {
				city = reader.nextString();
			} else if (name.equals("user")) {
				user = reader.nextString();
			} else if (name.equals("dateTime")) {
				dateTime = sdf.parse(reader.nextString());
			} else if (name.equals("enabled")) {
				enabled = reader.nextBoolean();
			} else if (name.equals("image1")) {
				image1 = Base64.decodeBase64(reader.nextString());
			} else if (name.equals("image2")) {
				image2 = Base64.decodeBase64(reader.nextString());
			} else if (name.equals("image3")) {
				image3 = Base64.decodeBase64(reader.nextString());
			}
		}

		reader.endObject();

		return new Sighting(id, speciesId, description, longitude, latitude,
				seaLevel, state, country, city, user, dateTime, enabled,
				image1, image2, image3);
	}

	private static User readUser(JsonReader reader) throws IOException {

		String username = null;
		String password = null;
		String email = null;
		String firstname = null;
		String lastname = null;
		String zip = null;
		String city = null;
		boolean enabled = false;

		reader.beginObject();

		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("username")) {
				username = reader.nextString();
			} else if (name.equals("password")) {
				password = reader.nextString();
			} else if (name.equals("email")) {
				email = reader.nextString();
			} else if (name.equals("firstname")) {
				firstname = reader.nextString();
			} else if (name.equals("lastname")) {
				lastname = reader.nextString();
			} else if (name.equals("zip")) {
				zip = reader.nextString();
			} else if (name.equals("city")) {
				city = reader.nextString();
			} else if (name.equals("enabled")) {
				enabled = reader.nextBoolean();
			}
		}

		reader.endObject();

		return new User(username, password, email, firstname, lastname, zip,
				city, enabled);
	}

	private static void writeSpecies(JsonWriter writer, Species value)
			throws IOException {

		writer.beginObject();

		writer.name("id").value(value.getId());
		writer.name("species").value(value.getSpecies());
		writer.name("category").value(value.getCategory());
		writer.name("latinName").value(value.getLatinName());
		writer.name("normalName").value(value.getNormalName());
		writer.name("description").value(value.getDescription());
		writer.name("validFrom").value(value.getValidFrom());
		writer.name("validTo").value(value.getValidTo());
		if (value.getImage1() != null) {
			writer.name("image1").value(
					Base64.encodeBase64String(value.getImage1()));
		}
		if (value.getImage2() != null) {
			writer.name("image2").value(
					Base64.encodeBase64String(value.getImage2()));
		}
		if (value.getImage3() != null) {
			writer.name("image3").value(
					Base64.encodeBase64String(value.getImage3()));
		}
		if (value.getImage4() != null) {
			writer.name("image4").value(
					Base64.encodeBase64String(value.getImage4()));
		}
		if (value.getImage5() != null) {
			writer.name("image5").value(
					Base64.encodeBase64String(value.getImage5()));
		}

		writer.endObject();
		writer.flush();

	}

	private static void writeSighting(JsonWriter writer, Sighting value)
			throws IOException {

		writer.beginObject();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

		writer.name("id").value(value.getId());
		writer.name("speciesId").value(value.getSpeciesId());
		writer.name("description").value(value.getDescription());
		writer.name("longitude").value(value.getLongitude());
		writer.name("latitude").value(value.getLatitude());
		writer.name("seaLevel").value(value.getSeaLevel());
		writer.name("state").value(value.getState());
		writer.name("country").value(value.getCountry());
		writer.name("city").value(value.getCity());
		writer.name("user").value(value.getUser());
		writer.name("dateTime").value(sdf.format(value.getDateTime()));
		writer.name("enabled").value(value.isEnabled());
		if (value.getImage1() != null) {
			writer.name("image1").value(
					Base64.encodeBase64String(value.getImage1()));
		}
		if (value.getImage2() != null) {
			writer.name("image2").value(
					Base64.encodeBase64String(value.getImage2()));
		}
		if (value.getImage3() != null) {
			writer.name("image3").value(
					Base64.encodeBase64String(value.getImage3()));
		}

		writer.endObject();
		writer.flush();

	}

	private static void writeUser(JsonWriter writer, User value)
			throws IOException {

		writer.beginObject();

		writer.name("username").value(value.getUsername());
		writer.name("password").value(value.getPassword());
		writer.name("email").value(value.getEmail());
		writer.name("firstname").value(value.getFirstname());
		writer.name("lastname").value(value.getLastname());
		writer.name("zip").value(value.getZip());
		writer.name("city").value(value.getCity());
		writer.name("enabled").value(value.isEnabled());

		writer.endObject();
		writer.flush();

	}

}
