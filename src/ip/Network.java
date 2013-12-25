package ip;

import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.math.*;


public class Network {
	
	public void whoLost() throws Exception {
		HashMap<String, String> devises = new HashMap<String, String>();
		String[] subnetIps = this.getSubnetIps(getNetworkMask(), getHostIp());
		while (true) {
		
			for (int i = 0; i < subnetIps.length; i++) {
				if(ping(subnetIps[i])){
					System.err.println("registred devise ip:"+subnetIps[i]+" mac:"+getMackAddress(subnetIps[i]));
					devises.put( subnetIps[i],getMackAddress(subnetIps[i]));
				}
				else if(devises.containsKey(subnetIps[i])){
					System.err.println("This devise lost net:" + devises.get(subnetIps[i]));
				}
			}
			
		}
	}

    public String[] getSubnetIps(){
        try {
            return getSubnetIps(getNetworkMask(),getHostIp());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getSubnetIps(short subMask,byte[] hostIp) {
		
		byte[] mask = new byte[hostIp.length];
		byte[] subNet = new byte[hostIp.length];
		for (int i = 0; i < subMask/8; i++) {
			mask[i] = (byte)0xFF;
			//System.out.println(Integer.toBinaryString( (int)mask[i]));
			if(i+1>=subMask/8){
				
				mask[i+1]= (byte) (0xFF - Math.pow(2,subMask - (i+1)*8) + 0x01);
				//System.out.println(subMask -(i+1)*8);
				//System.out.println(Integer.toBinaryString( (int)mask[i+1]));
			}
		}
		
		for (int i = 0; i < mask.length; i++) {
			subNet[i] = (byte) (mask[i]&hostIp[i]);
		}
		
		String[] ret = new String[(int) Math.pow(2, hostIp.length*8 - subMask) ];
		System.out.println("this net can contain this ips:");
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < subNet.length; j++) {
				//System.out.println(new Integer(subNet[j] & 0xFF));
				if(j==0){
					ret[i] = new Integer(subNet[j] & 0xFF).toString() ;
				}else{
					ret[i] =ret[i] + new Integer(subNet[j] & 0xFF).toString();
				}
				if(j+1<subNet.length) ret[i] = ret[i] + ".";
						//ret[i] =ret[i] + new Integer(subNet[j] & 0xFF).toString() + ".";
				//System.out.println("");
				if(subNet[j] == 0xFF){
					subNet[j-1] = (byte) (subNet[j-1] + 0x01);
					subNet[j] = 0x00;
				}
			}
			subNet[subNet.length -1] = (byte)(subNet[subNet.length -1] + 1);
			//System.out.println(Integer.toBinaryString( (int)subNet[subNet.length -1] + 1));
			System.out.println(ret[i]);
		}
		
		
		return ret;
	}
	
	public boolean ping(String ip) throws Exception {
		InetAddress inet = InetAddress.getByName(ip);
	    System.out.println("Sending Ping Request to " + ip);
	    if (inet.isReachable(1500)){
	        return true;
	    }
	    else{
	        return false;
	    }
	}
	
	public byte[] getHostIp() throws Exception {
		InetAddress inet = InetAddress.getLocalHost();
		System.err.println("My ip:"+inet.getHostAddress());
		return inet.getAddress();		
	}


	
	public short getNetworkMask() throws SocketException, Exception{
		
		InetAddress localHost = Inet4Address.getByName("10.9.88.209");
        System.out.println(localHost);
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
//        System.out.println(networkInterface);
		return networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
		
//		Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
//		while (en.hasMoreElements()) {
//			 
//            NetworkInterface ni = en.nextElement();
//            List<InterfaceAddress> inAdd = ni.getInterfaceAddresses();
//            for (InterfaceAddress ia : inAdd) {
//                
//                //returns Inet Address
//                System.out.println("Inet Address "+ia.getAddress());
//                
//                //returns Inet Address for the broadcast address
//                System.out.println("Inet Address for the broadcast address"+ia.getBroadcast());
//                
//                //returns network prefix length
//                System.out.println("network prefix length "+ia.getNetworkPrefixLength());
//            }
//		}
	}
	
	public String getMackAddress(String ipAddr) throws  Exception {
		
		InetAddress addr = InetAddress.getByName(ipAddr);
	    NetworkInterface ni = NetworkInterface.getByInetAddress(addr);
	    if (ni == null)
	        return null;

	    byte[] mac = ni.getHardwareAddress();
	    if (mac == null)
	        return null;

	    StringBuilder sb = new StringBuilder(18);
	    for (byte b : mac) {
	        if (sb.length() > 0)
	            sb.append(':');
	        sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	}
	
}
