package BinaryTree;

/**
 * 二叉树结点
 */
public class Node {
    public Integer data;
    public Node left;
    public Node right;

    public Node() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public Node(int value) {
        this.data = value;
        this.left = null;
        this.right = null;
    }
}
