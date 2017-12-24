package at.jku.se.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;

import at.jku.se.database.FTPHandler;

public class FTPTest {

	public static void main(String[] args) {
		
		FTPHandler ftp = new FTPHandler();

		 String server = "127.0.0.1";
	        int port = 21;
	        String user = "admin";
	        String pass = "admin";
	 
	        FTPClient ftpClient = new FTPClient();
	        try {
	 
	            ftpClient.connect(server, port);
	            ftpClient.login(user, pass);
	            ftpClient.enterLocalPassiveMode();
	 
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	 
	            // APPROACH #1: uploads first file using an InputStream
	            File firstLocalFile = new File("C:/Users/Max/Downloads/1.jpg");
	 
	            String firstRemoteFile = "1.jpg";
	            InputStream inputStream = new FileInputStream(firstLocalFile);
	 
	            System.out.println("Start uploading first file");
	            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
	            inputStream.close();
	            if (done) {
	                System.out.println("The first file is uploaded successfully.");
	            }
	            
	            String remoteFile1 = "/1.jpg";
	            ByteArrayOutputStream bo = new ByteArrayOutputStream();
	            boolean success = ftpClient.retrieveFile(remoteFile1, bo);
	            byte[] barr = bo.toByteArray();
	 

	 
	        } catch (IOException ex) {
	            System.out.println("Error: " + ex.getMessage());
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }

}
