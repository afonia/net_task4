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

    public MainController(){
        serverLisener = new ServerLisener(this);
        new Thread(serverLisener).start();
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
