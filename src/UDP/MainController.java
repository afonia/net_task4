package UDP;

import avl.AvlTree;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mail.Email;
import mail.EmailManager;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;



/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 1/15/14
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainController implements Runnable {
    ServerLisener serverLisener;
    AvlTree avlTree;
    EmailManager emailManager;
    String ip;
    boolean HasUbtatesForAVL = false;
    boolean HasUbtatesForMails = false;
    int broadcastNum = 0;

    public MainController(){
        serverLisener = new ServerLisener(this);
        ip = serverLisener.getInternetAddress().toString();
        avlTree = new AvlTree();
        avlTree.insert(ip, null);
        emailManager = new EmailManager();
        initMails();


        new Thread(serverLisener).start();
    }

    private void initMails() {
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест1", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест2", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест3", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест4", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест5", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест6", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест7", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));

    }

    @Override
    public void run() {
        System.out.println("mainController start");
        while (true){
            avlTree.debug(avlTree.root);
            try {
            if(isMain()){
                broadcastNum++;
                if(broadcastNum>0) serverLisener.BroadCastMessege(Dictionary.IAmMain + Dictionary.End);
                if(avlTree.notAlone()) {
                    if(HasUbtatesForAVL){
                        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                        String js = gson.toJson(avlTree);
                        String messege = Dictionary.ByMainServer + Dictionary.AVLUbdates + js + Dictionary.End;
                        serverLisener.BroadCastMessege(messege);
                        HasUbtatesForAVL = false;
                    }
                    if(HasUbtatesForMails){
                        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                        String js = gson.toJson(emailManager);
                        String messege = Dictionary.ByMainServer + Dictionary.MessegeUbtates + js + Dictionary.End;
                        serverLisener.BroadCastMessege(messege);
                        HasUbtatesForMails = false;
                    }
                }
            }
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    public boolean isMain(){
        return avlTree.isMain(ip);
    }
    public void addToAvl(String ip){
        if(avlTree.findKeyByIp(ip)!=null){
            avlTree.insert(ip,null);
            HasUbtatesForMails = true;
            HasUbtatesForAVL = true;
            System.out.println(ip+"<------------------Added to avl");
        }
    }
    public void updateMails(String js){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        EmailManager emailManager2 = gson.fromJson(js, EmailManager.class);
        emailManager = emailManager2;
    }
    public void updateAVL(String js){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        AvlTree avlN = gson.fromJson(js, AvlTree.class);
        avlTree = avlN;
    }

}
