package at.jku.se.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.jku.se.database.FileHandler;
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
				String img2Name = result.getString("IMAGE2");
				String img3Name = result.getString("IMAGE3");
				String img4Name = result.getString("IMAGE4");
				String img5Name = result.getString("IMAGE5");

				FileHandler ftp = new FileHandler();
				byte[] image1 = ftp.getFile(img1Name);
				byte[] image2 = ftp.getFile(img2Name);
				byte[] image3 = ftp.getFile(img3Name);
				byte[] image4 = ftp.getFile(img4Name);
				byte[] image5 = ftp.getFile(img5Name);

				s = new Species(rid, species, category, latinName, normalName,
						description, validFrom, validTo, image1, image2,
						image3, image4, image5);

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
							+ " and (? >= validFrom " + " and ? <= validTo) ");
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
				String img2Name = result.getString("IMAGE2");
				String img3Name = result.getString("IMAGE3");
				String img4Name = result.getString("IMAGE4");
				String img5Name = result.getString("IMAGE5");

				FileHandler ftp = new FileHandler();
				byte[] image1 = ftp.getFile(img1Name);
				byte[] image2 = ftp.getFile(img2Name);
				byte[] image3 = ftp.getFile(img3Name);
				byte[] image4 = ftp.getFile(img4Name);
				byte[] image5 = ftp.getFile(img5Name);

				s = new Species(rid, rspecies, rcategory, rlatinName,
						rnormalName, rdescription, rvalidFrom, rvalidTo,
						image1, image2, image3, image4, image5);

				list.add(s);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<Species> getSpeciesForAdmin(Connection connection,
			String species) {

		ArrayList<Species> list = new ArrayList<Species>();

		Species s = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * from species where species like ? ");
			statement.setString(1, species);
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
				String img2Name = result.getString("IMAGE2");
				String img3Name = result.getString("IMAGE3");
				String img4Name = result.getString("IMAGE4");
				String img5Name = result.getString("IMAGE5");

				FileHandler ftp = new FileHandler();
				byte[] image1 = ftp.getFile(img1Name);
				byte[] image2 = ftp.getFile(img2Name);
				byte[] image3 = ftp.getFile(img3Name);
				byte[] image4 = ftp.getFile(img4Name);
				byte[] image5 = ftp.getFile(img5Name);

				s = new Species(rid, rspecies, rcategory, rlatinName,
						rnormalName, rdescription, rvalidFrom, rvalidTo,
						image1, image2, image3, image4, image5);

				list.add(s);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static Long getSpeciesIdForAdmin(Connection connection,
			String species) {
		Long rid = (long) 0;

		try {

			PreparedStatement statement = connection
					.prepareStatement("SELECT id from species where species like ? ");
			statement.setString(1, species);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				rid = result.getLong("ID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rid;
	}

	public static void deleteSpeciesByAdmin(Connection connection, String id) {

		try {
			
			Species s = getSpecies(connection, Integer.parseInt(id));
			
			FileHandler ftp = new FileHandler();
			if (s.getImage1() != null) {
				ftp.deleteFile("art_"+s.getId()+"_1");
			}
			if (s.getImage2() != null) {
				ftp.deleteFile("art_"+s.getId()+"_2");
			}
			if (s.getImage3() != null) {
				ftp.deleteFile("art_"+s.getId()+"_3");
			}
			if (s.getImage4() != null) {
				ftp.deleteFile("art_"+s.getId()+"_4");
			}
			if (s.getImage5() != null) {
				ftp.deleteFile("art_"+s.getId()+"_5");
			}
			PreparedStatement statement2 = connection
					.prepareStatement("DELETE FROM species WHERE id = ?");
			statement2.setLong(1, Integer.parseInt(id));
			statement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
