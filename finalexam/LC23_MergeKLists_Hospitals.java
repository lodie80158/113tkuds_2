package finalexam;

import java.util.*;

class Node {
    int val;
    Node next;
    Node(int x) { val = x; }
}

public class LC23_MergeKLists_Hospitals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        List<Node> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Node dummy = new Node(0), tail = dummy;
            while (true) {
                int v = sc.nextInt();
                if (v == -1) break;
                tail.next = new Node(v);
                tail = tail.next;
            }
            lists.add(dummy.next);
        }
        Node merged = mergeKLists(lists.toArray(new Node[0]));
        print(merged);
    }

    static Node mergeKLists(Node[] lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (Node n : lists) if (n != null) pq.offer(n);
        Node dummy = new Node(0), tail = dummy;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            tail.next = cur; tail = tail.next;
            if (cur.next != null) pq.offer(cur.next);
        }
        return dummy.next;
    }

    static void print(Node head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " " : ""));
            head = head.next;
        }
        System.out.println();
    }
}

