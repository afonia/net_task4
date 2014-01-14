package avl;

import com.google.gson.annotations.Expose;

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

    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return obj;
    }
}
