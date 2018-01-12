package at.jku.se.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.jku.se.database.FileHandler;
import at.jku.se.model.Sighting;

public class SightingFacade {

	public static Sighting getSigthing(Connection connection, Long id) {

		Sighting s = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from sighting where id = ? AND enabled like ?");
			statement.setLong(1, id);
			statement.setString(2, "Y");
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				Long rid = result.getLong("ID");
				int speciesId = result.getInt("SPECIESID");
				String description = result.getString("DESCRIPTION");
				double longitude = result.getDouble("LONGITUDE");
				double latitude = result.getDouble("LATITUDE");
				int seaLevel = result.getInt("SEALEVEL");
				String state= result.getString("STATE");
				String country= result.getString("COUNTRY");
				String city = result.getString("CITY");
				String user = result.getString("USER");
				Date dateTime = result.getDate("DATETIME");
				boolean enabled = result.getString("ENABLED").equals("Y");
				String imgName1= result.getString("IMAGE1");
				String imgName2= result.getString("IMAGE2");
				String imgName3= result.getString("IMAGE3");
				
				FileHandler ftp = new FileHandler();
				byte[] image1 = ftp.getFile(imgName1);
				byte[] image2 = ftp.getFile(imgName2);
				byte[] image3 = ftp.getFile(imgName3);
				
				s = new Sighting(speciesId, description, longitude, latitude, seaLevel, state, country, city, user, dateTime, enabled, image1,image2,image3);
				s.setId(rid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static List<Sighting> getSightingFilter(Connection connection, /*Date dateFrom, Date dateTo, */
			String user, String speciesId, String state, String country)
	{
		
		
		ArrayList<Sighting> list = new ArrayList<Sighting>();
		
		Sighting s = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * from sighting where enabled like ? "
							+ " and user like ? "
							+ " and speciesid like ? "
							+ " and state like ? "
							+ " and country like ? ");
							/*+ " and (? >= dateFrom"
							+ " and ? <= dateTo) ");*/
			statement.setString(1, "Y");
			statement.setString(2, user);
			statement.setString(3, speciesId);
			statement.setString(4, state);
			statement.setString(5, country);
			/*statement.setDate(6, new java.sql.Date( dateFrom.getTime()));
			statement.setDate(7, new java.sql.Date( dateTo.getTime()));*/
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Long rid = result.getLong("ID");
				int rspeciesId = result.getInt("SPECIESID");
				String rdescription = result.getString("DESCRIPTION");
				double rlongitude = result.getDouble("LONGITUDE");
				double rlatitude = result.getDouble("LATITUDE");
				int rseaLevel = result.getInt("SEALEVEL");
				String rcity = result.getString("CITY");
				String rstate = result.getString("STATE");
				String rcountry = result.getString("COUNTRY");
				String ruser = result.getString("USER");
				Date rDate = result.getDate("DATETIME");
				boolean enabled = result.getString("ENABLED").equals("Y");
				String img1Name = result.getString("IMAGE1");
				String img2Name = result.getString("IMAGE2");
				String img3Name = result.getString("IMAGE3");

				FileHandler ftp = new FileHandler();
				byte[] image1 = ftp.getFile(img1Name);
				byte[] image2 = ftp.getFile(img2Name);
				byte[] image3 = ftp.getFile(img3Name);

				s = new Sighting(rspeciesId, rdescription, rlongitude, rlatitude, rseaLevel,
						rstate, rcountry, rcity, ruser, rDate, enabled, image1, image2, image3);
				s.setId(rid);
				list.add(s);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void insertSighting(Connection connection, Sighting sighting) {
		
		
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO sighting(SPECIESID,DESCRIPTION,LONGITUDE,LATITUDE,SEALEVEL,CITY,STATE,COUNTRY,USER,DATETIME,ENABLED,IMAGE1, IMAGE2, IMAGE3) "
							+ "	VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			//statement.setInt(1, sighting.getId());
			statement.setInt(1, sighting.getSpeciesId());
			statement.setString(2, sighting.getDescription());
			statement.setDouble(3, sighting.getLongitude());
			statement.setDouble(4, sighting.getLatitude());
			statement.setInt(5, sighting.getSeaLevel());
			statement.setString(6, sighting.getCity());
			statement.setString(7, sighting.getState());
			statement.setString(8, sighting.getCountry());
			statement.setString(9, sighting.getUser());
			statement.setDate(10, new java.sql.Date(sighting.getDateTime().getDate()));
			//statement.setDate(11, (java.sql.Date) sighting.getDateTime());
			statement.setBoolean(11, sighting.isEnabled());
		    statement.setBytes(12, sighting.getImage1());
		    statement.setBytes(13, sighting.getImage2());
		    statement.setBytes(14, sighting.getImage3());
			FileHandler ftp = new FileHandler();
			if (sighting.getImage1() != null) {
				ftp.putFile(sighting.getId()+"_"+"1", sighting.getImage1());
			}
			if (sighting.getImage2() != null) {
				ftp.putFile(sighting.getId()+"_"+"2", sighting.getImage2());
			}
			if (sighting.getImage3() != null) {
				ftp.putFile(sighting.getId()+"_"+"3", sighting.getImage3());
			}
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteSighting(Connection connection, Sighting s) {
		
		try {
			
				PreparedStatement statement2 = connection.prepareStatement("DELETE FROM sighting WHERE id = ?");
				statement2.setLong(1, s.getId());
				statement2.executeUpdate();

				FileHandler ftp = new FileHandler();
				if (s.getImage1() != null) {
					ftp.deleteFile(s.getId()+"_1");
				}
				if (s.getImage2() != null) {
					ftp.deleteFile(s.getId()+"_2");
				}
				if (s.getImage3() != null) {
					ftp.deleteFile(s.getId()+"_3");
				}
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateSighting(Connection connection, Sighting s)
	{
		try {
			PreparedStatement statement = connection
					.prepareStatement("UPDATE sighting SET"
							+ " speciesid = ? "
							+ " ,longitude = ? "
							+ "	, latitude = ? "
							+ " , description = ? "
							+ " , enabled = ? "
							+ "WHERE id = ? ");
			statement.setInt(1, s.getSpeciesId());
			statement.setDouble(2, s.getLongitude());
			statement.setDouble(3, s.getLatitude());
			statement.setString(4, s.getDescription());
			statement.setLong(5, s.getId());
			statement.executeUpdate();
			
			Sighting s2 = getSigthing(connection, s.getId());
			FileHandler ftp = new FileHandler();
			
			if (s2.getImage1() != s.getImage1()) {
				ftp.deleteFile(s2.getId()+"_1");
				ftp.putFile(s.getId()+"_1", s.getImage1());
			}
			if (s2.getImage2() != s.getImage2()) {
				ftp.deleteFile(s2.getId()+"_2");
				ftp.putFile(s.getId()+"_2", s.getImage2());
			}
			if (s2.getImage3() != s.getImage3()) {
				ftp.deleteFile(s2.getId()+"_3");
				ftp.putFile(s.getId()+"_3", s.getImage3());
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Sighting> getSightingFilterForAdmin(Connection connection, /*Date dateFrom, Date dateTo, */
			String user, String speciesId, String state, String country, String enabled)
	{
		
		
		ArrayList<Sighting> list = new ArrayList<Sighting>();
		
		Sighting s = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * from sighting where user like ? "
							+ " and speciesid like ? "
							+ " and state like ? "
							+ " and country like ? "
							+ " and enabled like ? ");
							/*+ " and (? >= dateFrom"
							+ " and ? <= dateTo) ");*/
			statement.setString(1, user);
			statement.setString(2, speciesId);
			statement.setString(3, state);
			statement.setString(4, country);
			statement.setString(5, enabled);
			/*statement.setDate(5, new java.sql.Date( dateFrom.getTime()));
			statement.setDate(6, new java.sql.Date( dateTo.getTime()));*/
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Long rid = result.getLong("ID");
				int rspeciesId = result.getInt("SPECIESID");
				String rdescription = result.getString("DESCRIPTION");
				double rlongitude = result.getDouble("LONGITUDE");
				double rlatitude = result.getDouble("LATITUDE");
				int rseaLevel = result.getInt("SEALEVEL");
				String rcity = result.getString("CITY");
				String rstate = result.getString("STATE");
				String rcountry = result.getString("COUNTRY");
				String ruser = result.getString("USER");
				Date rDate = result.getDate("DATETIME");
				boolean renabled =  result.getString("ENABLED").equals("Y");
				String img1Name = result.getString("IMAGE1");
				String img2Name = result.getString("IMAGE2");
				String img3Name = result.getString("IMAGE3");

				FileHandler ftp = new FileHandler();
				byte[] image1 = ftp.getFile(img1Name);
				byte[] image2 = ftp.getFile(img2Name);
				byte[] image3 = ftp.getFile(img3Name);

				s = new Sighting(rspeciesId, rdescription, rlongitude, rlatitude, rseaLevel,
						rstate, rcountry, rcity, ruser, rDate, renabled, image1, image2, image3);
				s.setId(rid);
				list.add(s);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void updateSightingAdmin(Connection connection, ArrayList<Sighting> slist)
	{
		try {
			for (int i = 0; i < slist.size(); i++) {
				
			
				PreparedStatement statement = connection
					.prepareStatement("UPDATE sighting SET"
							+ " enabled = ? "
							+ "WHERE id = ? ");
				statement.setString(1, "Y");
				statement.setLong(2, slist.get(i).getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
