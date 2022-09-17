package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树相关
 */
public class BinaryTree {

    /**
     * 获取树的深度
     */
    public static int getDepth(Node head) {
        if (head == null) {
            return 0;
        }
        int l = getDepth(head.left);
        int r = getDepth(head.right);
        return Math.max(l + 1, r + 1);
    }

    /**
     * 获取树的宽度
     */
    public static int getWidth(Node head) {
        if (head == null) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);
        while (!queue.isEmpty()) {
            // 当前层的宽度
            int width = queue.size();
            // 当前层全部出队，并将下层子节点全部入队
            for (int i = 0; i < width; i++) {
                Node temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            // 更新max
            max = Math.max(max, width);
        }
        return max;
    }

    /**
     * 先序遍历（递归）
     */
    public static void PreorderTraversal(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.data);
        PreorderTraversal(head.left);
        PreorderTraversal(head.right);
    }

    /**
     * 中序遍历（递归）
     */
    public static void MiddleTraversal(Node head) {
        if (head == null) {
            return;
        }
        MiddleTraversal(head.left);
        System.out.print(head.data);
        MiddleTraversal(head.right);
    }

    /**
     * 后序遍历（递归）
     */
    public static void PostorderTraversal(Node head) {
        if (head == null) {
            return;
        }
        PostorderTraversal(head.left);
        PostorderTraversal(head.right);
        System.out.print(head.data);
    }

    /**
     * 层次遍历（使用队列实现）
     */
    public static void LevelTraversal(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }
}
