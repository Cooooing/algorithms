package Leetcode;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 */
public class add_two_numbers_2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 暴力
     */
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode temp = result;
        int carry = 0;
        int sum = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Value = l1 != null ? l1.val : 0;
            int l2Value = l2 != null ? l2.val : 0;
            sum = l1Value + l2Value + carry;
            carry = sum / 10;
            ListNode next = new ListNode(sum % 10);
            temp.next = next;
            temp = next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return result.next;
    }
}
