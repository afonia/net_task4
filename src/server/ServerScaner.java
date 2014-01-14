package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/25/13
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServerScaner implements Runnable{
    public String[] serverIPs;
    ServerMessenger parent;
    public static Server server;



    public ServerScaner(ServerMessenger parent,Server server){
        this.parent = parent;
        serverIPs = parent.getSubnetIps();
        ServerScaner.server = server;
    }

    @Override
    public void run() {
        //System.out.println("Scan server run");
        while (!parent.exit){
            scan();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void scan(){
        //System.out.println("Start scan");
        while (parent.exit){
            for (String s: serverIPs){
                String mesege =  scanIp(s);
                if(mesege!= null){
                    if(mesege.contains(Dictionary.HiMessege)){
                        //System.out.println(mesege);
    //                    SendMessegeToServer(mesege.split(":")[1],"test");
                    }
                    if (mesege.contains(Dictionary.Confirm)){
                        //System.out.println(mesege);
                    }
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }



    public static String scanIp(String ip){
        ip = "127.0.0.1";
        //System.out.println("Scaning "+ ip);
        String ret = null;
        try {
            Socket fromserver = new Socket(InetAddress.getByName(ip),ServerMessenger.port);
            BufferedReader in  = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
            PrintWriter out = new PrintWriter(fromserver.getOutputStream(),true);
            out.println(Dictionary.HiMessege + ip);
            out.println(Dictionary.End);
            System.out.println("Scan send ip");
            String input;
            while ((input = in.readLine())!=null){
                if(input.equals(Dictionary.End)) break;
                System.out.println("Scan get:" + input);
                //System.out.println("client:"+input);
                ret = ret + input;
            }
            server.ExecuteMessege(input);
//            if(input!=null) sumbit(ip);
            //System.out.println("Scaning stop:" + ip);
            fromserver.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static boolean hasConnectionWith(String ip){
        if(scanIp(ip)!= null) return true;
        else return false;
    }

    public static boolean SendMessegeToServer(String ip, String Messege){
        if(!hasConnectionWith(ip)) return false;
        boolean ret = false;
        try {
            Socket fromserver = new Socket(InetAddress.getByName(ip),ServerMessenger.port);
            BufferedReader in  = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
            PrintWriter out = new PrintWriter(fromserver.getOutputStream(),true);
            out.println(Dictionary.Messege + Messege);
            out.println(Dictionary.End);
            System.out.println("Scner send mesege:" + Messege);
            String input;
            while ((input = in.readLine())!=null){
                //System.out.println(input);
                if(input.equals(Dictionary.End)) break;
                if(input == Dictionary.Confirm) ret = true;
                System.out.println("Scaner get:"+input);
            }
            fromserver.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }


    private void sumbit(String ip){
       //System.out.println("register: " + ip);
    }

}
