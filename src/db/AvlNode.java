package db;

/** Here is the AVL-Node class for Completenesse **/
public class AvlNode {
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
