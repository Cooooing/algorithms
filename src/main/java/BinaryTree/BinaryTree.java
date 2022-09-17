package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
     * 先序遍历（循环）
     */
    public static void PreorderTraversal_(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.data);
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
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
     * 中序遍历（循环）
     */
    public static void MiddleTraversal_(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.data);
                head = head.right;
            }
        }
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
     * 后序遍历（循环1，两个栈）
     * 按中右左的顺序压入栈2，再依次出栈。即是左右中（后序）的顺序
     */
    public static void PostorderTraversal_(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<Node>();
        Stack<Node> stack2 = new Stack<Node>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            head = stack1.pop();
            stack2.push(head);
            if (head.left != null) {
                stack1.push(head.left);
            }
            if (head.right != null) {
                stack1.push(head.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data);
        }
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
