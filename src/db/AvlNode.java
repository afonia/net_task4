package db;

import base64encode.Base64Coder.Base64Coder;
import com.google.gson.annotations.Expose;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** Here is the AVL-Node class for Completenesse **/
public class AvlNode implements Serializable {
    @Expose
    public AvlNode left;
    @Expose
    public AvlNode right;
    public AvlNode parent;
    @Expose
    public int key;
    @Expose
    public int balance;
    @Expose
    public boolean main = false;
    @Expose
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
