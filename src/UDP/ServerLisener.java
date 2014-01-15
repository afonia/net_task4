package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 1/14/14
 * Time: 10:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerLisener implements Runnable{
    DatagramSocket serverSocket = null;
    MainController parent;
    boolean isMain = true;
    boolean hasMain = true;
    public ServerLisener(MainController parent){
        try {
            this.parent = parent;
            serverSocket = new DatagramSocket(Dictionary.Port);
        } catch (SocketException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void run() {
        System.out.println("ServerLisener start");
        try {

            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            HashMap<InetAddress,String> messeges = new HashMap<InetAddress, String>();
            String messege;
            while(true)
            {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("server wait");
                serverSocket.receive(receivePacket);
                if(receivePacket.getAddress().equals(getInternetAddress())) continue;

                String sentence = new String(receivePacket.getData());
                InetAddress IPfrom = receivePacket.getAddress();

                if(messeges.containsKey(IPfrom)){
                    messege = messeges.get(IPfrom) + sentence;
                    if(messege.contains(Dictionary.End)){
                        String answer = FormAnswer(messege,IPfrom);
                        if(answer!=null){
                            answer+=Dictionary.End;
                            if(isMain) answer+=Dictionary.ByMainServer;
                            sendData = answer.getBytes();//уязвимое место, нужно протестить
                            DatagramPacket sendPacket =
                                    new DatagramPacket(sendData, sendData.length, IPfrom, Dictionary.Port);
                            serverSocket.send(sendPacket);
                        }
                        messeges.remove(IPfrom);
                    }else{
                        messeges.remove(IPfrom);
                        messeges.put(IPfrom,messege);
                    }
                }else if(sentence.contains(Dictionary.End)){
                    String answer = FormAnswer(sentence,IPfrom);
                    if(answer!=null){
                        answer+=Dictionary.End;
                        sendData = answer.getBytes();//уязвимое место, нужно протестить
                        DatagramPacket sendPacket =
                                new DatagramPacket(sendData, sendData.length, IPfrom, Dictionary.Port);
                        serverSocket.send(sendPacket);
                    }
                }else {
                    messeges.put(IPfrom,sentence);
                }
                //System.out.println("RECEIVED: " + sentence);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void BroadCastMessege(String messege) {
        try {
            byte[] sendData = messege.getBytes();
            InetAddress brodcastIP = InetAddress.getByName("192.168.0.255");
            System.out.println(brodcastIP);
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, brodcastIP, Dictionary.Port);
            serverSocket.send(sendPacket);
            System.out.println("broadcast:"+messege);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String FormAnswer(String messege,InetAddress ip){
        System.out.println(" from"+ip+" messege:"+messege);

        if(parent.isMain()){
            if(messege.contains(Dictionary.IAmMain)){
                parent.broadcastNum = -20;
                return Dictionary.HiMain;
            }
            if(messege.contains(Dictionary.HiMain)){
                parent.addToAvl(ip.getHostAddress());
                return null;
            }
        }else {
            if(messege.contains(Dictionary.ByMainServer)){
                if(messege.contains(Dictionary.AVLUbdates)){
                    messege = messege.replace(Dictionary.ByMainServer,"");
                    messege = messege.replace(Dictionary.AVLUbdates,"");
                    messege = messege.substring(messege.indexOf(Dictionary.End));
                    parent.updateAVL(messege);
                    return null;
                }
                if(messege.contains(Dictionary.MessegeUbtates)){
                    messege = messege.replace(Dictionary.ByMainServer,"");
                    messege = messege.replace(Dictionary.MessegeUbtates,"");
                    messege = messege.substring(messege.indexOf(Dictionary.End));
                    parent.updateMails(messege);
                    return null;
                }
                return null;
            }else{
                return null;
            }
        }
        if(messege.contains(Dictionary.AreYouHere)){
            return Dictionary.Confirm;
        }
        if(messege.contains(Dictionary.Unsopproted)) return null;
        return Dictionary.Unsopproted;
    }

    public InetAddress getInternetAddress(){
        try {
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            for (; n.hasMoreElements();)
            {
                NetworkInterface e = n.nextElement();
                if(!e.getName().toString().equals("wlan0")) continue;
                Enumeration<InetAddress> a = e.getInetAddresses();
                for (; a.hasMoreElements();)
                {
                    InetAddress addr = a.nextElement();
                    if (addr instanceof Inet4Address)
                        //System.out.println(""+addr.getHostAddress());
                    return addr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
    public boolean isMain(){
        return isMain;
    }
    public boolean hasMain(){
        return hasMain;
    }
    public void setMain(){
        System.err.println("set main");
        isMain = true;
    }
    public void IgetMain(){
        System.err.println(" I found  main");
        hasMain = true;
    }
    public void AddUnit(){
        System.out.println("Unit addet");
    }
}
