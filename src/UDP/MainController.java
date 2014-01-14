package UDP;

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

    public static void main(String[] args) {
        try {
            new Thread(new MainController()).start();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public MainController(){
        serverLisener = new ServerLisener(this);
        new Thread(serverLisener).start();
    }
    @Override
    public void run() {
        System.out.println("mainController start");
        while (true){
            try {
            if(!hasMain()){

                serverLisener.BroadCastMessege(Dictionary.HiMessege + Dictionary.End);
            }

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    public boolean isMain(){
        return false;
    }
    public boolean hasMain(){
        return false;
    }
}