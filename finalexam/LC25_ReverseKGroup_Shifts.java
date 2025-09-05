package finalexam;

import java.util.*;

class N {
    int val;
    N next;
    N(int v) { val = v; }
}

public class LC25_ReverseKGroup_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        while (sc.hasNextInt()) arr.add(sc.nextInt());
        N head = build(arr);
        head = reverseKGroup(head, k);
        print(head);
    }

    static N reverseKGroup(N head, int k) {
        N dummy = new N(0); dummy.next = head;
        N prev = dummy;
        while (true) {
            N node = prev;
            for (int i = 0; i < k && node != null; i++) node = node.next;
            if (node == null) break;
            N cur = prev.next, nxt = cur.next;
            for (int i = 1; i < k; i++) {
                cur.next = nxt.next;
                nxt.next = prev.next;
                prev.next = nxt;
                nxt = cur.next;
            }
            prev = cur;
        }
        return dummy.next;
    }

    static N build(List<Integer> arr) {
        N dummy = new N(0), tail = dummy;
        for (int v : arr) { tail.next = new N(v); tail = tail.next; }
        return dummy.next;
    }

    static void print(N head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " " : ""));
            head = head.next;
        }
        System.out.println();
    }
}

