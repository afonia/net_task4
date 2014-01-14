package db;

import base64encode.Base64Coder.Base64Coder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** Here is the AVL-Node class for Completenesse **/
public class AvlNode implements Serializable {
    public AvlNode left;
    public AvlNode right;
    public AvlNode parent;
    public int key;
    public int balance;
    public String ip;

    public AvlNode(int k, String ip) {
        left = right = parent = null;
        balance = 0;
        key = k;
        this.ip = ip;
    }
    public String toString() {
        return "" + key;
    }

}
