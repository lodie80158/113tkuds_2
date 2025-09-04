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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); // 加虛擬頭，簡化邊界處理
        ListNode first = dummy;
        ListNode second = dummy;

        // 先讓 first 前進 n+1 步，使 first 與 second 保持 n 節點間距
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 同步移動 first 和 second，直到 first 到尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // second.next 為需刪除的節點 → 直接跳過
        second.next = second.next.next;

        return dummy.next; // 返回新的頭節點
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 刪除單向鏈表的倒數第 n 個節點。

2. 解法設計（雙指標 / 快慢指標）：
   - 使用兩個指標 first、second。
   - 先讓 first 前進 n+1 步，這樣 first 與 second 之間距離為 n。
   - 同步移動兩指標，直到 first 到尾。
   - 此時 second.next 即倒數第 n 個節點 → 將其刪除。
   - 使用 dummy 節點，方便處理刪除頭節點的情況。

3. 範例：
   - head = [1,2,3,4,5], n = 2
   - first 先走 3 步 → first 在 3，second 在 dummy
   - 同步移動 → first 到尾，second 在 3
   - 刪除 second.next = 4 → 結果 [1,2,3,5]

4. 為什麼可行：
   - 快慢指標確保能一次遍歷鏈表完成操作。
   - 無需計算鏈表長度 → 單遍 O(L)。

時間複雜度：O(L)  (L = 鏈表長度)  
空間複雜度：O(1)
*/
