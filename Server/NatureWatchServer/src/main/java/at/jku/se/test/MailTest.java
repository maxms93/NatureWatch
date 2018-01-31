package at.jku.se.test;

import at.jku.se.database.MailHandler;

public class MailTest {

	public static void main(String[] args) {
		
		MailHandler mh = new MailHandler();
		mh.sendMail("max-ms@gmx.at", "Test", "test");

	}

}
