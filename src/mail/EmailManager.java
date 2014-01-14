package mail;

import com.google.gson.annotations.Expose;
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
    @Expose
    public ArrayList<Email> emails = new ArrayList<Email>();
    @Expose
    public Hashtable<String,Integer[]> mapSend = new Hashtable<String,Integer[]>();
    @Expose
    public int sendEmail = 2;
    @Expose
    public int sendEmailDelay = 5;

    public Integer[] sendEmails(String ip) {
        Integer[] sendArray =  mapSend.get(ip);
        while( sendArray.length !=0 ) {
            sendEmail(ip);
            sendArray =  mapSend.get(ip);
            try {
               // System.out.println( sendArray.length);

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
        return mapSend.get(ip);
    }

    protected boolean sendEmail(String ip) {
        SendMessage sendMessage = new SendMessage();
        Integer[] sendArray =  mapSend.get(ip);
        //System.out.println(sendArray[2]);
        int sendCounter = 0;
        Email curEmail = null;
        for(int i=0; i<sendArray.length;i++) {
            if(sendCounter >= sendEmail)  {
                break;
            }
            curEmail = (Email)emails.get(sendArray[i]);
            System.out.println(curEmail.getSubject() + " send" + i);
            try {
                sendMessage.sendMail(curEmail.getTo(), curEmail.getSubject(), curEmail.getMessageBody() + ip, curEmail.getHost(), curEmail.getUser(), curEmail.getPassword());
            } catch (MessagingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
          //  System.out.println(i);
           // System.out.println(sendArray[0]);
            sendArray = (Integer[])ArrayUtils.removeElement(sendArray, sendArray[i]);
            mapSend.remove(ip);
            mapSend.put(ip, sendArray);
            //System.out.println(sendArray[0]);
            sendCounter++;
        }
        return true;
    }

    public void updateMapSendInMain(String ip, Integer[] array) {
        mapSend.remove(ip);
        mapSend.put(ip, array);
    }

    public void createSendListInMain(String[] ips) {
        int count= ips.length;
        mapSend = new Hashtable<String,Integer[]>();
        int emailCountSize =  emails.size();
        int emailCount =  emailCountSize / count;
        int counter = 0;
        for(String str : ips) {
            Integer[] ourArray = null;
            if( (counter == 0) && (emailCountSize%count != 0) ) {
                ourArray = new Integer[emailCount+1];
                for(int i =0; i < emailCount;i++) {
                    ourArray[i] =  counter*emailCount + i;
                }
                ourArray[emailCount] = emailCountSize-1;
            }  else {
                ourArray = new Integer[emailCount];
                for(int i =0; i < emailCount;i++) {
                    ourArray[i] =  counter*emailCount + i;
                }
            }
            mapSend.put(str, ourArray);
            counter++;
        }

    }
   // public ArrayList list
}
