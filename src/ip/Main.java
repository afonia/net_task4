package ip;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Network nt = new Network();
			nt.whoLost();
		
//		String ip = "192.168.0.1";
//	    InetAddress inet;
//		
//			inet = InetAddress.getByName(ip);
//		
//	    System.out.println("Sending Ping Request to " + ip);
//	    if (inet.isReachable(5000)){
//	        System.out.println(ip+" is reachable");
//	    }
//	    else{
//	        System.out.println(ip+" is not reachable");
//	    }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}
