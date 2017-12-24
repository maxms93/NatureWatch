package at.jku.se.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.jku.se.database.FTPHandler;
import at.jku.se.model.Species;

public class SpeciesFacade {

	public static Species getSpecies(Connection connection, int id) {

		Species s = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * from species where id = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int rid = result.getInt("ID");
				String species = result.getString("SPECIES");
				String description = result.getString("DESCRIPTION");
				String category = result.getString("CATEGORY");
				String latinName = result.getString("LATINNAME");
				String normalName = result.getString("NORMALNAME");
				int validFrom = result.getInt("VALIDFROM");
				int validTo = result.getInt("VALIDTO");
				String img1Name = result.getString("IMAGE1");

				FTPHandler ftp = new FTPHandler();
				byte[] imagebytes = ftp.getFile(img1Name);

				s = new Species(rid, species, category, latinName, normalName,
						description, validFrom, validTo);
				s.addImage(imagebytes);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	public static List<Species> getSpecies(Connection connection,
			String species, String category, String latinName,
			String normalName, int valid) {
		
		ArrayList<Species> list = new ArrayList<Species>();
		
		Species s = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * from species where species like ? "
							+ " and category like ? "
							+ " and latinName like ? "
							+ " and normalName like ? "
							+ " and (? >= validFrom "
							+ " and ? <= validTo) ");
			statement.setString(1, species);
			statement.setString(2, category);
			statement.setString(3, latinName);
			statement.setString(4, normalName);
			statement.setInt(5, valid);
			statement.setInt(6, valid);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int rid = result.getInt("ID");
				String rspecies = result.getString("SPECIES");
				String rdescription = result.getString("DESCRIPTION");
				String rcategory = result.getString("CATEGORY");
				String rlatinName = result.getString("LATINNAME");
				String rnormalName = result.getString("NORMALNAME");
				int rvalidFrom = result.getInt("VALIDFROM");
				int rvalidTo = result.getInt("VALIDTO");
				String img1Name = result.getString("IMAGE1");

				FTPHandler ftp = new FTPHandler();
				byte[] imagebytes = ftp.getFile(img1Name);

				s = new Species(rid, rspecies, rcategory, rlatinName, rnormalName,
						rdescription, rvalidFrom, rvalidTo);
				s.addImage(imagebytes);
				
				list.add(s);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
