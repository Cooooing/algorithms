package LinkedList;

/**
 * 单链表结点
 */
public class Node {
    public Integer data;
    public Node next;

    public Node() {
        this.data = null;
        this.next = null;
    }

    public Node(int value) {
        this.data = value;
        this.next = null;
    }
}

