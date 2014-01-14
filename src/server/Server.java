package server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/26/13
 * Time: 5:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Server implements Runnable{
    public List<Email> emails = new ArrayList<Email>();
    Type listOfEmails = new TypeToken<List<Email>>(){}.getType();


    public void sinhronizeMails(String[] ips){
        Gson gson = new Gson();
        String mesege = gson.toJson(emails,listOfEmails);
        for (String ip:ips){
            ServerScaner.SendMessegeToServer(ip,mesege);
        }
    }

    public void ExecuteMessege(String messege){
        if(messege.contains(Dictionary.Mails)){
            messege = messege.replaceFirst(Dictionary.Mails,"");
            Gson gson = new Gson();
            List<Email> emails = gson.fromJson(messege,listOfEmails);
            updateMails(emails);
        }
        if(messege.contains(Dictionary.GetEmail)){
            messege.replace(Dictionary.GetEmail,"");
            Gson gson = new Gson();
            String mails = gson.toJson(emails,listOfEmails);
            ServerScaner.SendMessegeToServer(messege,Dictionary.Mails + mails );

        }
        if(messege.contains(Dictionary.SendMails)){
            messege.replace(Dictionary.SendMails,"");
            int num = Integer.parseInt(messege);
            sendMails(num);
        }


    }

    public void sendMails(int num){

    }

    public void updateMails(List<Email> emails){
        if(isMainServer()){
            List<Email> newBase = new ArrayList<Email>();
            for (Email em:emails){
                if(em.fromServer.equals(Dictionary.Sended)) continue;
                if(!this.emails.contains(em)){
                    this.emails.add(em);
                }
            }
        }else {
            this.emails = emails;
        }
    }

    public boolean isMainServer(){
        return true;
    }

    @Override
    public void run() {
        new Thread(new ServerMessenger(this)).start();
        while (!ServerMessenger.exit){
            if(isMainServer()){
//                chekServers();
//                GetMails();
//                SetMails();


            }
        }
    }
    public void chekServers(String[] ips){
        for(String ip: ips){
            if(!ServerScaner.hasConnectionWith(ip)){
            }
        }
    }
    public void GetMails(String[] ips){
        for (String ip:ips){
            String messege = Dictionary.GetEmail + ServerMessenger.ip;
            ServerScaner.SendMessegeToServer(ip,messege);
        }
    }
    public void SetMails(String[] ips){
        Gson gson = new Gson();
        for (String ip:ips){
            String messege = Dictionary.GetEmail + gson.toJson(emails,listOfEmails);
            ServerScaner.SendMessegeToServer(ip,messege);
        }
    }
}
