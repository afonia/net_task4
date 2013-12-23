package server;

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
    private ServerSocket serverSocket = null;
    private Socket fromClient = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private boolean exit = false;

    public ServerMessenger(){
        try {
            System.out.println("Messenger start init");
            serverSocket = new ServerSocket(port);
            fromClient = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(fromClient.getInputStream()));
            out = new PrintWriter(fromClient.getOutputStream(),true);

            System.out.println("Messenger inited");
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



    public void scan(){

    }

    public void checkChildrens(){

    }

    public void checkParent(){

    }

    public void scanForAtherServerInSubnet(){

    }

    @Override
    public void run() {
        System.out.println("Messenger Start");

        while (!exit){
            try {
                System.out.println("Messenger work");
                Thread.sleep(1000);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
