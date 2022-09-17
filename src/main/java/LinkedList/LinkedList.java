package LinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * 单链表相关
 */
public class LinkedList {

    /**
     * 判断一个链表是否有环，并返回环的入口
     * 使用快慢指针实现
     */
    public static Node findLoopPort2(Node head) {
        Node p1 = head.next;
        Node p2 = head.next;
        while (p1.next != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            // 有环，会在环中某节点相遇
            if (p1 == p2) {
                break;
            }
        }
        // 无环 返回null
        if (p1.next == null || p2.next.next == null) {
            return null;
        }
        // 有环，计算环长度
        int count = 1;
        p1 = p1.next;
        while (p1 != p2) {
            p1 = p1.next;
            count++;
        }
        // 有环，两指针分别从起点和相遇点触发，最后会在环入口相遇
        p1 = head.next;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        System.out.println("环的长度：" + count);
        return p1;
    }

    /**
     * 判断一个链表是否有环，并返回环的入口
     * 使用 List集合 存储节点，第一次出现重复节点即为环的入口
     */
    public static Node findLoopPort1(Node head) {
        Node next = head.next;
        List<Node> list = new ArrayList<Node>();
        while (null != next) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == next) {
                    System.out.println("环的长度：" + (list.size() - i));
                    return next;
                }
            }
            list.add(next);
            next = next.next;
        }
        return null;
    }
}
