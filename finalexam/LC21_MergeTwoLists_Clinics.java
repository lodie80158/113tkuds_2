package finalexam;

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LC21_MergeTwoLists_Clinics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        ListNode l1 = build(sc, n);
        ListNode l2 = build(sc, m);
        ListNode merged = mergeTwoLists(l1, l2);
        print(merged);
    }

    static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a; a = a.next;
            } else {
                tail.next = b; b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null ? a : b);
        return dummy.next;
    }

    static ListNode build(Scanner sc, int n) {
        ListNode dummy = new ListNode(0), tail = dummy;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        return dummy.next;
    }

    static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " " : ""));
            head = head.next;

