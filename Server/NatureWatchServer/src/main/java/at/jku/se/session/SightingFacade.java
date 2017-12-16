package at.jku.se.session;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import at.jku.se.database.FTPHandler;
import at.jku.se.model.Sighting;

public class SightingFacade {

	public static Sighting getSigthing(Connection connection, int id) {

		Sighting s = null;
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * from sighting where id = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				int rid = result.getInt("ID");
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
				String imgName= result.getString("IMAGE1");
				
				FTPHandler ftp = new FTPHandler();
				byte[] imagebytes = ftp.getFile(imgName);
				
				s = new Sighting(rid, speciesId, description, longitude, latitude, seaLevel, state, country, city, user, dateTime, enabled);
				s.addImages(imagebytes);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

}
