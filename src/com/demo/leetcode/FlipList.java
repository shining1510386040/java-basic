package com.demo.leetcode;


/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 翻转链表
 * @date 2021/4/23 11:09
 * @see
 */
public class FlipList {

//    题目描述：
//
//    反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//
//    说明:
//            1 ≤ m ≤ n ≤ 链表长度。
//
//    输入: 1->2->3->4->5->NULL, m = 2, n = 4
//    输出: 1->4->3->2->5->NULL

    public static void main(String[] args) {

        ListNode last1 = new ListNode(5, null);
        ListNode last2 = new ListNode(4, last1);
        ListNode last3 = new ListNode(3, last2);
        ListNode last4 = new ListNode(2, last3);
        ListNode head = new ListNode(1, last4);

        System.out.println(head);

        // 指定位置翻转链表
        int m = 2;
        int n = 4;
        ListNode dest = reverseBetween(head, m, n);
        System.out.println(dest);
    }

    private static ListNode reverseBetween(ListNode head, int m, int n) {

        if (m == n){
            return head;
        }
        ListNode newHead = new ListNode();
        newHead.next = head;
        // 工作节点pre
        ListNode pre = newHead;
        // 工作节点移动到m位置
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }

        head = pre.next;
        for (int i = m; i < n; i++) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return newHead.next;
    }

    /**
     * 链表节点
     */
    static class ListNode {
        Integer data;
        ListNode next;

        public ListNode() {

        }

        public ListNode(Integer data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }
}
