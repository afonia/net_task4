package mail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.ArrayUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 1/15/14
 * Time: 12:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        EmailManager emailManager = new EmailManager();
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест1", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест2", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест3", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест4", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест5", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест6", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
        emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест7", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));
       // emailManager.emails.add(new Email("afonderkin@gmail.com", "мой тест8", "супер босс", "smtp.mail.ru", "robtest1@mail.ru", "robtest123"));

        emailManager.createSendListInMain(new String[]{"10.0.0.0" , "10.0.0.1"});

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String js = gson.toJson(emailManager);
        //System.out.println(js);

        EmailManager emailManager2 = gson.fromJson(js, EmailManager.class);
       // ArrayList<Integer> inter = new ArrayList<Integer>();
       // inter = (ArrayList)emailManager2.mapSend.get("10.0.0.0");
        //int[] st = new int[inter.size()];
       // st =  inter.toArray(st);
       // int[] intArray = ArrayUtils.toPrimitive((ArrayList))
      //  System.out.println(emailManager2.emails.get(0).getClass());
       // emailManager2.mapSend.put("10.0.0.0", new int[]{5, 6 ,7 ,8});
       emailManager2.sendEmails("10.0.0.0");
        //System.out.println("new");
      //  emailManager.sendEmails("10.0.0.1");
      //  List<Integer> test0 = Arrays.asList(1, 1);
       // List<Integer> test1 = Arrays.asList(1,1);
     /*   int[] a = {1,2,3};
        Hashtable<String,Object> map = new Hashtable<String,Object>();
        map.put("ip", a) ;
        int[] b = (int[])map.get("ip");
        System.out.println(b[0]);
    */
    }
}