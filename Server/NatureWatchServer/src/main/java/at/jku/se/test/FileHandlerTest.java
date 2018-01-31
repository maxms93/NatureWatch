package at.jku.se.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.bval.jsr303.util.IOUtils;

import at.jku.se.database.FileHandler;

public class FileHandlerTest {

	public static void main(String[] args) {

		try {

			FileHandler handler = new FileHandler();
			
			String firstRemoteFile = "1.jpg";
			
			//handler.deleteFile(firstRemoteFile);

			File firstLocalFile = new File("C:/Users/Max/Downloads/1.jpg");

			InputStream inputStream = new FileInputStream(firstLocalFile);

			BufferedImage img = ImageIO.read(inputStream);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", baos);

			handler.putFile(firstRemoteFile, baos.toByteArray());
			
			byte[] a = handler.getFile(firstRemoteFile);
			
			handler.putFile("2.jpg", a);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
