package db;

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
        avlTree.insert("th2", null);
        avlTree.insert("th3", null);
        avlTree.insert("th4", null);
        avlTree.insert("th5", null);
        avlTree.insert("th6", null);
        avlTree.insert("th7", null);
        System.out.println(avlTree.root.right.left.ip);
    }
}
