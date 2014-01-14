package UDP;

import avl.AvlTree;
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



    public MainController(){
        serverLisener = new ServerLisener(this);
        avlTree = new AvlTree();
        avlTree.insert(serverLisener.getInternetAddress().toString(), null);
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
            try {
            if(isMain()){
                serverLisener.BroadCastMessege(Dictionary.IAmMain + Dictionary.End);
            }
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    public boolean isMain(){
        return serverLisener.isMain;
    }
    public boolean hasMain(){
        return serverLisener.hasMain;
    }
}
