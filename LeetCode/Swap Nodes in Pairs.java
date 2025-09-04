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
    public ListNode swapPairs(ListNode head) {
        // 建立 dummy 節點方便操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // 標記要交換的兩個節點
            ListNode first = prev.next;
            ListNode second = first.next;

            // 交換節點
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // prev 移到下一對節點前
            prev = first;
        }

        return dummy.next;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 每兩個相鄰節點交換，返回新的頭節點。

2. 解法設計（迭代法）：
   - 用 dummy 節點指向 head，方便處理頭節點可能被交換的情況。
   - 用 prev 指向當前要交換對的前一個節點。
   - 標記 first 和 second 節點。
   - 調整指標完成交換：
       1. first.next 指向 second 的下一個節點
       2. second.next 指向 first
       3. prev.next 指向 second
   - 移動 prev 到 first，準備下一對節點。

3. 範例：
   - head = [1,2,3,4]
   - 交換 1 和 2 → [2,1,3,4]
   - 交換 3 和 4 → [2,1,4,3]

4. 為什麼可行：
   - 只修改節點指標，不改值。
   - 遍歷整個鏈表一次即可完成交換。

時間複雜度：O(n)  
空間複雜度：O(1)
*/
