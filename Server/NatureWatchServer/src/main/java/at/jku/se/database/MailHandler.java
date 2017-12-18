package at.jku.se.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import at.jku.se.controller.UserController;

public class MailHandler {

	public static void sendMail(String empfaenger, String betreff, String text) {

		// https://myaccount.google.com/lesssecureapps
		try{
           
            Properties prop = getMailProperties();            		
            		
            final String fromEmail = prop.getProperty("fromEmail");
            final String password = prop.getProperty("password");

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(prop, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));

            message.setSubject(betreff);
            message.setText(text);

            Transport.send(message);
        }catch(Exception ex){
            System.out.println("Mail fail");
            System.out.println(ex);
        }
	}

	private static Properties getMailProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "mail.properties";
			input = UserController.class.getClassLoader().getResourceAsStream(
					filename);

			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
			}

			prop.load(input);

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
		
		return prop;
	}
}
