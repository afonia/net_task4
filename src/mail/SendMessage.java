package mail;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/26/13
 * Time: 5:37 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMessage {

    public void sendMail(String to,
                                String subject, String messageBody, String host, final String user, final String password) throws
            MessagingException, AddressException {
        //String host = "smtp.mail.ru";
      //  final String user = "serega-afon@mail.ru";
       // final String password = "rtyujh";

        // Setup mail server
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);


        // Get a mail session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });


        // Define a new mail message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);

        // Create a message part to represent the body text
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(messageBody);

        //use a MimeMultipart as we need to handle the file attachments
        Multipart multipart = new MimeMultipart();

        //add the message body to the mime message
        multipart.addBodyPart(messageBodyPart);

        // Put all message parts in the message
        message.setContent(multipart);

        // Send the message
        Transport.send(message);
    }
}