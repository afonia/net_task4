package mail;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 1/15/14
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailManager {
    public ArrayList emails = new ArrayList<Email>();
    public Hashtable<String,Object> mapSend = new Hashtable<String,Object>();
    public int sendEmail = 2;
    public int sendEmailDelay = 5;

    public boolean sendEmails(String ip) {
        int[] sendArray =  (int[])mapSend.get(ip);
        while( sendArray.length !=0 ) {
            sendEmail(ip);
            sendArray =  (int[])mapSend.get(ip);
            try {
                System.out.println( sendArray.length);

                if(sendArray.length !=0 ) {
                    System.out.println("wait");
                 //   System.out.println(sendArray[0]);
                    Thread.sleep(1000 * sendEmailDelay);
                }  else {
                    System.out.println("complete");
                }

            } catch ( java.lang.InterruptedException ie) {
                System.out.println(ie);

            }
        }
        return true;
    }

    protected boolean sendEmail(String ip) {
        SendMessage sendMessage = new SendMessage();
        int[] sendArray =  (int[])mapSend.get(ip);
        int sendCounter = 0;
        Email curEmail = null;
        for(int i : sendArray) {
            if(sendCounter >= sendEmail)  {
                break;
            }
            curEmail = (Email)emails.get(i);
            System.out.println("send" + i);
            try {
                sendMessage.sendMail(curEmail.getTo(), curEmail.getSubject(), curEmail.getMessageBody(), curEmail.getHost(), curEmail.getUser(), curEmail.getPassword());
            } catch (MessagingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
          //  System.out.println(i);
           // System.out.println(sendArray[0]);
            sendArray =ArrayUtils.removeElement(sendArray, i);
            mapSend.remove(ip);
            mapSend.put(ip, sendArray);
            //System.out.println(sendArray[0]);
            sendCounter++;
        }
        return true;
    }
   // public ArrayList list
}
