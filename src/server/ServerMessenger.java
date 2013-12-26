package server;

import ip.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/23/13
 * Time: 12:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerMessenger implements Runnable {
    public static int port = 1234;
    public static String ip;

    private ServerSocket serverSocket = null;
    private Socket fromClient = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    public boolean exit = false;


    public ServerMessenger(){
        try {
            serverSocket = new ServerSocket(port);
            //System.out.println("Messenger inited");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dispose(){
        try {
            out.close();
            in.close();
            fromClient.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void checkChildrens(){

    }

    public void checkParent(){

    }

    @Override
    public void run() {
        try {
            //System.out.println("Messenger Start");
            new Thread( new ServerScaner(this)).start();

            while (!exit){
                //System.out.println("Messenger wait for messege");
                fromClient = serverSocket.accept();
                ip = fromClient.getLocalAddress().getHostAddress();
                in = new BufferedReader(new InputStreamReader(fromClient.getInputStream()));
                out = new PrintWriter(fromClient.getOutputStream(),true);
                String input;
                String mesege = null;
                while ((input=in.readLine())!=null){
                    System.out.println("Mesenger get:" +input);
                    if(input.equals(Dictionary.End)) break;
                    if(mesege==null) mesege = input;
                    else mesege+=input;
                    //System.out.println(input);
                }

                if(mesege.contains(Dictionary.HiMessege)){
                    out.println(Dictionary.HiMessege + ip);
                    out.println(Dictionary.End);
                    System.out.println("Mesenger send:" + ip );
                }
                if(mesege.contains(Dictionary.Messege)){
                    //System.out.println(mesege);
                    out.println(Dictionary.Confirm);
                    out.println(Dictionary.End);
                    System.out.println("Mesenger confirm" );
                }
//                //System.out.println("end");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            dispose();
        }
    }

    public String[] getSubnetIps(){
        String pattern = "10.9.88.";
        String[] ret = new String[255];
        for(int i = 1; i<256; i++){
            ret[i-1] = pattern + Integer.toString(i);
        }
        return ret;
    }
}
