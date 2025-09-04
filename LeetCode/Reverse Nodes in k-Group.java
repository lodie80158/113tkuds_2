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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;
        
        while (true) {
            // 檢查剩餘節點數是否至少 k 個
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break; // 不足 k 個，結束

            // 反轉當前 k 個節點
            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;
            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;

            for (int i = 0; i < k; i++) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // 連接前一組
            prevGroupEnd.next = prev;
            prevGroupEnd = groupStart; // 更新 prevGroupEnd
        }

        return dummy.next;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 將鏈表每 k 個節點反轉，剩餘不足 k 的節點保持順序。

2. 解法設計（迭代 + O(1) 空間）：
   - 使用 dummy 節點方便操作頭節點。
   - prevGroupEnd 指向上一組反轉的尾節點。
   - 找到當前組的第 k 個節點 kth。
       - 若不足 k 個 → 不需反轉，退出。
   - 反轉這 k 個節點：
       - prev 指向下一組開頭 (nextGroupStart)，方便最後連接。
       - 迴圈反轉節點指向。
   - 反轉完成後，連接上一組尾節點。
   - 更新 prevGroupEnd 為剛反轉組的尾節點。
   - 重複直到整個鏈表處理完。

3. 範例：
   - head = [1,2,3,4,5], k = 2
     → 反轉 [1,2] → [2,1,3,4,5]
     → 反轉 [3,4] → [2,1,4,3,5]

4. 為什麼可行：
   - 每次只修改節點指標，不改值。
   - O(1) 額外空間，僅用變數儲存節點指標。
   - 遍歷整個鏈表一次即可完成。

時間複雜度：O(n)  
空間複雜度：O(1)
*/
