import LinkedList.Node;
import org.junit.Test;
import LinkedList.LinkedList;
import java.util.*;

public class LinkedListTest {
    @Test
    public void linkedList() {
        Node head = getRingLinkedList();
        Node res = null;
        System.out.println("-----findLoopPort1-----");
        res = LinkedList.findLoopPort1(head);
        System.out.println("返回的节点："+res);
        System.out.println("返回的节点数据："+res.data);
        System.out.println("-----findLoopPort2-----");
        res = LinkedList.findLoopPort2(head);
        System.out.println("返回的节点："+res);
        System.out.println("返回的节点数据："+res.data);

    }

    /**
     * 生成环链表（入口随机）
     * @return 返回链表头节点
     */
    private Node getRingLinkedList(){
        Node head = new Node();
        Node next = head;
        Random random = new Random();
        int rand = random.nextInt(100);
        Node r = null;
        for (int i = 0; i < 100; i++) {
            next.next = new Node(random.nextInt(50));
//            next.next = new Node(i);
            next = next.next;
            if (i == rand) {
                r = next;
            }
        }
        next.next = r;
        System.out.println("环入口："+r);
        System.out.println("环入口数据："+r.data);
//        next = head.next;
//        while (next != null) {
//            System.out.println(next.data);
//            next = next.next;
//        }
        return head;
    }
}
