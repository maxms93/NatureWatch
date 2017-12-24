package at.jku.se.database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPHandler {

	private FTPClient ftpClient;

	public void putFile(String name, byte[] data) {

		InputStream inputStream = new ByteArrayInputStream(data);

		try {

			boolean done = ftpClient.storeFile(name, inputStream);

			if (done) {
				System.out.println("The first file is uploaded successfully.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
	
	public byte[] deleteFile(String name) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try {
			
		boolean success = ftpClient.deleteFile(name);
		return outputStream.toByteArray();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return null;
	}

	public byte[] getFile(String name) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try {
			
		boolean success = ftpClient.retrieveFile(name, outputStream);
		return outputStream.toByteArray();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return null;
	}

	public FTPHandler() {

		Properties prop = new Properties();
		InputStream input = null;
		String server = null;
		int port = 0;
		String user = null;
		String pass = null;

		try {

			String filename = "ftp.properties";
			input = FTPHandler.class.getClassLoader()
					.getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			prop.load(input);

			System.out.println(prop.getProperty("server"));
			System.out.println(prop.getProperty("port"));
			System.out.println(prop.getProperty("user"));
			System.out.println(prop.getProperty("password"));

			server = prop.getProperty("server");
			port = Integer.parseInt(prop.getProperty("port"));
			user = prop.getProperty("user");
			pass = prop.getProperty("password");

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

		ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
