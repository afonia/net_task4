package server;

import java.io.BufferedReader;
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

    @Override
    public void run() {

    }
}
