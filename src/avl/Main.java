package avl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

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
        //AvlNode avlN = avlTree.unserialize("sadasd");
        avlTree.insert("th2", null);
        avlTree.insert("th3", null);
        avlTree.insert("th4", null);
        avlTree.insert("th5", null);
        avlTree.insert("th6", null);
        avlTree.insert("th7", null);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String js = gson.toJson(avlTree);
        avlTree.debug(avlTree.root);
        //System.out.println(avlTree.root.main);
        AvlTree avlN = gson.fromJson(js, AvlTree.class);
        avlN.returnParent();
        // avlTree.removeByIp("th5");

      //  avlTree.debug(avlTree.root);
       avlTree.debug(avlN.root);
    //  System.out.println(avlN.root.ip);
    }
}
