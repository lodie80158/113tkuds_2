package midterm;

import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int idx;
        Item(String n, int q, int i) { name = n; qty = q; idx = i; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), K = sc.nextInt();
        Item[] arr = new Item[n];
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            arr[i] = new Item(name, qty, i);
        }

        PriorityQueue<Item> pq = new PriorityQueue<>(K, (a, b) -> {
            if (a.qty != b.qty) return a.qty - b.qty; 
            return b.idx - a.idx; 
        });

        for (Item it : arr) {
            if (pq.size() < K) pq.add(it);
            else {
                Item top = pq.peek();
                if (it.qty > top.qty || (it.qty == top.qty && it.idx < top.idx)) {
                    pq.poll();
                    pq.add(it);
                }
            }
        }

        List<Item> res = new ArrayList<>(pq);
        res.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.idx - b.idx;
        });

        for (Item it : res) System.out.println(it.name + " " + it.qty);
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：每個商品插入或比較時維護一個大小為 K 的 min-heap，單次操作 O(log K)，總計 O(n log K)。
 */
