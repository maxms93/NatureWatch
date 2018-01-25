package at.jku.se.database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FileHandler {

	private String directory = "C://Users/Max/Dropbox/STUDIUM/IT/trunk/Server/NatureWatchServer/images/";
	
	public FileHandler(){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "server.properties";
			input = DatabaseConnector.class.getClassLoader()
					.getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			prop.load(input);

			System.out.println(prop.getProperty("imageDirectory"));
			directory = prop.getProperty("imageDirectory");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
	
	public void putFile(String name, byte[] data) {

		if (name == null) {
			System.out.println("Filename is null!");
			return;
		}
		try {
			Path file = Paths.get(directory + name);
			Files.write(file, data);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public boolean deleteFile(String name) {

		if (name == null) {
			System.out.println("Filename is null!");
			return true;
		}

		File file = new File(this.directory + name);
		return file.delete();

	}

	public byte[] getFile(String name) {
		
		if (name == null || name.equals("")) {
			System.out.println("Filename is null!");
			return null;
		}

		try {
			File file = new File(this.directory + name);
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
