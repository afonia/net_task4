package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
    boolean isMain = false;
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
                String sentence = new String(receivePacket.getData());
                InetAddress IPfrom = receivePacket.getAddress();

                if(messeges.containsKey(IPfrom)){
                    messege = messeges.get(IPfrom) + sentence;
                    if(messege.contains(Dictionary.End)){
                        String answer = FormAnswer(messege,IPfrom);
                        if(answer!=null){
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
                System.out.println("RECEIVED: " + sentence);
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
            System.out.println("broadcast");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String FormAnswer(String messege,InetAddress ip){
        System.out.println("messege:"+messege);
        if(messege.contains(Dictionary.HiMessege)){
            if(isMain()){
                return Dictionary.HiFromMain;
            }else if(hasMain()){
                return null;
            }else {
                setMain();
                return Dictionary.HiFromMain;
            }
        }
        if(isMain()){
            if(messege.contains(Dictionary.HiMain)){
                AddUnit();
                return null;
            }
        }else {
            if(messege.contains(Dictionary.ByMainServer)){
                if(messege.contains(Dictionary.Mails)){
                    messege.replace(Dictionary.ByMainServer,"");
                    messege.replace(Dictionary.Mails,"");
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
    public boolean isMain(){
        return isMain;
    }
    public boolean hasMain(){
        return isMain;
    }
    public void setMain(){
        System.out.println("set main");
        isMain = true;
    }
    public void AddUnit(){
        System.out.println("Unit addet");
    }
}
