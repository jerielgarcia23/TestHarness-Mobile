package Utilities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class AutomatedEmailNotification {

    private static String host = "10.100.50.25";
    private static String user = "felix.livshits@shomi.com";
    private static String pass = "asdf";

    public static void sendEmail(String toAddr, String subject, String body)
    {
    	
    	// Recipient's email ID needs to be mentioned.
        String to = "felix.livshits@shomi.com";

        // Sender's email ID needs to be mentioned
        String from = "MobAutomatedTesting@shomi.com";

        // Assuming you are sending email from localhost
        //String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("This is the Subject Line!");

           // Now set the actual message
           message.setText(body);

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
    	
    	
    	
    	/*
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.user", user);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(prop);
        MimeMessage message = new MimeMessage(session);

        try
        {
            message.setFrom(new InternetAddress("MobAutomatedTesting@shomi.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));
            message.setSubject(subject);
            message.setText(body);
            
            SmtpAuthenticator authentication = new SmtpAuthenticator();
            javax.mail.Message msg = new MimeMessage(Session
                                .getDefaultInstance(prop, authentication));
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pass);
            Transport.send(msg, msg.getAllRecipients()); 
            transport.close();
            System.out.println("done");
        }
        catch (AddressException e) {e.printStackTrace();}
        catch (MessagingException e) {e.printStackTrace();}
        */
    }
}

