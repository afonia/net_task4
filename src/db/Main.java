package db;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/23/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        AvlTree avlTree = new AvlTree();
        avlTree.insert("th1", null);
        AvlNode avlN = avlTree.unserialize("sadasd");
      //  avlTree.insert("th2", null);
      //  avlTree.insert("th3", null);
     //   avlTree.insert("th4", null);
     //   avlTree.insert("th5", null);
      //  avlTree.insert("th6", null);
      //  avlTree.insert("th7", null);
       // Gson json = new Gson();
       // String js = json.toJson(avlTree.root);
       // avlTree.removeByIp("th5");
        //System.out.println(js);
        System.out.println(avlN.ip);
    }
}
