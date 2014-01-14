package POP3;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import java.util.Date;
import java.util.Properties;

public class POP3Clint {

    public static void main(String[] args) throws Exception {

        String host = "pop.mail.ru";
        String user = "robtest1@mail.ru";
        String password = "robtest123";

        // Get system properties
        Properties properties = System.getProperties();

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        // Get a Store object that implements the specified protocol.
        Store store = session.getStore("pop3");

        // Connect to the current host using the specified username and
        // password.
        store.connect(host, user, password);

        // Create a Folder object corresponding to the given name.
        Folder folder = store.getFolder("inbox");

        // Open the Folder.
        folder.open(Folder.READ_ONLY);

        // Get the messages from the server
        Message[] messages = folder.getMessages();

        // Display message.
        int len = 10;
        if (len > messages.length)
            len = messages.length;
        for (int i = 0; i < 10; i++) {
            System.out.println("------------ Message " + (i + 1)
                    + " ------------");
            // Here's the big change...
            String from = InternetAddress.toString(messages[i].getFrom());
            if (from != null) {
                System.out.println("From: " + from);
            }
            String replyTo = InternetAddress.toString(messages[i].getReplyTo());
            if (replyTo != null) {
                System.out.println("Reply-to: " + replyTo);
            }
            String to = InternetAddress.toString(messages[i]
                    .getRecipients(Message.RecipientType.TO));
            if (to != null) {
                System.out.println("To: " + to);
            }
            String cc = InternetAddress.toString(messages[i]
                    .getRecipients(Message.RecipientType.CC));
            if (cc != null) {
                System.out.println("Cc: " + cc);
            }
            String bcc = InternetAddress.toString(messages[i]
                    .getRecipients(Message.RecipientType.BCC));
            if (bcc != null) {
                System.out.println("Bcc: " + to);
            }
            String subject = messages[i].getSubject();
            if (subject != null) {
                System.out.println("Subject: " + subject);
            }
            Object text = messages[i].getContent();
            if (text != null) {
                System.out.println("TEXT: \n" + text);
            }
            Date sent = messages[i].getSentDate();
            if (sent != null) {
                System.out.println("Sent: " + sent);
            }
            Date received = messages[i].getReceivedDate();
            if (received != null) {
                System.out.println("Received: " + received);
            }
            System.out.println();
        }

        folder.close(true);
        store.close();
    }
}