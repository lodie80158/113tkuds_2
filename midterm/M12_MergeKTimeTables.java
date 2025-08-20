package midterm;

import java.util.*;

public class M12_MergeKTimeTables {
    static class Node implements Comparable<Node> {
        int time, listIdx, idxInList;
        Node(int t, int l, int i) { time = t; listIdx = l; idxInList = i; }
        public int compareTo(Node o) { return this.time - o.time; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) arr[j] = sc.nextInt();
            lists.add(arr);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            if (lists.get(i).length > 0) pq.add(new Node(lists.get(i)[0], i, 0));
        }

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            merged.add(cur.time);
            int nextIdx = cur.idxInList + 1;
            if (nextIdx < lists.get(cur.listIdx).length) {
                pq.add(new Node(lists.get(cur.listIdx)[nextIdx], cur.listIdx, nextIdx));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size() - 1 ? "\n" : " "));
        }
    }
}

/*
 * Time Complexity: O(N log K)
 * - N 為所有列表總長度
 * - 每個元素入列和出列 O(log K)
 * - 總體 O(N log K)
 * Space Complexity: O(K + N)
 */
