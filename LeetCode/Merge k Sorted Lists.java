import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 建立最小堆，根據節點值排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 將每個非空鏈表的頭節點加入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll(); // 取最小節點
            curr.next = minNode;
            curr = curr.next;

            if (minNode.next != null) {
                pq.offer(minNode.next); // 將下一個節點加入堆
            }
        }

        return dummy.next;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 合併 k 個已排序鏈表，返回排序後的單一鏈表。

2. 解法設計（最小堆 / PriorityQueue）：
   - 將每個鏈表的頭節點加入最小堆。
   - 每次從堆取出最小節點，接到結果鏈表尾部。
   - 如果該節點有下一個節點，加入堆繼續比較。
   - 重複直到堆空。

3. 範例：
   - lists = [[1,4,5],[1,3,4],[2,6]]
   - 堆過程：
     - 取 1 → 接到結果
     - 下一節點 4 入堆
     - 取 1 → 接到結果
     - 下一節點 3 入堆
     ...
   - 最終結果：1->1->2->3->4->4->5->6

4. 為什麼可行：
   - 堆保證每次選取最小節點，結果鏈表自動有序。
   - 使用堆，每次操作 O(log k)，比兩兩合併更高效。

時間複雜度：O(N log k)，N 為所有節點總數  
空間複雜度：O(k) (堆大小)
*/
