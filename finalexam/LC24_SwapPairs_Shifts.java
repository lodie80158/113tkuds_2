package finalexam;

import java.util.*;

class LN {
    int val;
    LN next;
    LN(int v) { val = v; }
}

public class LC24_SwapPairs_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> arr = new ArrayList<>();
        while (sc.hasNextInt()) arr.add(sc.nextInt());
        LN head = build(arr);
        head = swapPairs(head);
        print(head);
    }

    static LN swapPairs(LN head) {
        LN dummy = new LN(0); dummy.next = head;
        LN prev = dummy;
        while (head != null && head.next != null) {
            LN a = head, b = head.next;
            prev.next = b; a.next = b.next; b.next = a;
            prev = a; head = a.next;
        }
        return dummy.next;
    }

    static LN build(List<Integer> arr) {
        LN dummy = new LN(0), tail = dummy;
        for (int v : arr) { tail.next = new LN(v); tail = tail.next; }
        return dummy.next;
    }

    static void print(LN head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " " : ""));
            head = head.next;
        }
        System.out.println();
    }
}

