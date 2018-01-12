package at.jku.se.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

	private String directory = "images/";

	public void putFile(String name, byte[] data) {

		if (name == null) {
			System.out.println("Filename is null!");
			return;
		}
		try {

			Path file = Paths.get(this.directory + name);
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
		
		if (name == null) {
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
