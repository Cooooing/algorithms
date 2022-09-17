package BinaryTree;

import java.util.HashMap;
import java.util.HashSet;

public class Expend {

    /*
    折纸问题
    请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
    此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
    如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
    给定一个输入参数N,代表纸条都从下边向上方连续对折N次。
    请从上到下打印所有折痕的方向。
    例如：N=1时，打印：down N=2时，打印：down down up
     */
    public static void origami(int N) {
        origamiProcess(1, N, true);
    }

    /**
     * 折纸过程：当前折痕再上一次折痕上下方各有一条折痕，且上方是凹，下方是凸
     * 类似二叉树
     * i是结点的层数，N是总层数，down==true是凹，down==false是凸
     */
    public static void origamiProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        origamiProcess(i + 1, N, true);
        System.out.print(down ? "down " : "up ");
        origamiProcess(i + 1, N, false);
    }

    /**
     * 获取二叉树中两节点的最低公共父节点
     */
    public static Node getPublic(Node head, Node n1, Node n2) {
        // 记录每个节点的父节点
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        // 只有头节点的父节点是自己，便于分辨头节点
        map.put(head, head);
        getPublicProcess(head, map);
        // 记录n1所有的父节点
        HashSet<Node> set = new HashSet<Node>();
        set.add(head);
        Node cur = n1;
        while (cur != map.get(cur)) {
            set.add(cur);
            cur = map.get(cur);
        }
        // n2不在n1所在的二叉树中，返回null
        if (map.get(n2) == null) {
            return null;
        }
        // 遍历n2所有的父节点直至与n1有重合
        cur = n2;
        while (!set.contains(cur)) {
            cur = map.get(cur);
        }
        return cur;
    }

    /**
     * 遍历二叉树，记录每个节点的父节点
     */
    private static void getPublicProcess(Node head, HashMap<Node, Node> map) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            map.put(head.left, head);
        }
        if (head.right != null) {
            map.put(head.right, head);
        }
        getPublicProcess(head.left, map);
        getPublicProcess(head.right, map);
    }

    /**
     * 获取二叉树中两节点的最低公共父节点（递归）
     * 两种情况：
     * 1. n1是n2的父节点 或 n2是n1的父节点
     * 2. n1和n2不互为父节点，需要往上找
     */
    public static Node getPublic_(Node head, Node n1, Node n2) {
        // 遇到空节点返回null，没有最低公共父节点。遇到n1或n2则返回本身。
        if (head == null || n1 == head || n2 == head) {
            return head;
        }
        Node left = getPublic_(head.left, n1, n2);
        Node right = getPublic_(head.right, n1, n2);
        // 左右子节点找到的最小公共父节点都不为空，即当前节点就是最小公共父节点，返回当前节点
        if (left != null && right != null) {
            return head;
        }
        // 左右子节点找到的最小公共父节点其中一个为空，则返回不为空的
        return left != null ? left : right;
    }
}
