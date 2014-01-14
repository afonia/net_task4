package UDP;

import server.Server;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/23/13
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        try {
            new Thread(new MainController()).start();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}