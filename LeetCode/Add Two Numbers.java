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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 建立虛擬頭節點，方便返回結果
        ListNode curr = dummy; // 用來逐步構建新的鏈結串列
        int carry = 0; // 記錄進位

        // 只要 l1 或 l2 還沒走完，就繼續計算
        while (l1 != null || l2 != null) {
            int val1 = (l1 != null) ? l1.val : 0; // 取 l1 當前數字，若為 null 則視為 0
            int val2 = (l2 != null) ? l2.val : 0; // 取 l2 當前數字，若為 null 則視為 0

            int sum = val1 + val2 + carry; // 計算總和（包含進位）
            carry = sum / 10; // 更新進位

            curr.next = new ListNode(sum % 10); // 建立新節點存放該位數字
            curr = curr.next; // 移動指標到下一個節點

            if (l1 != null) l1 = l1.next; // 移動 l1
            if (l2 != null) l2 = l2.next; // 移動 l2
        }

        // 如果最後還有進位，補上一個節點
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummy.next; // 返回真正的頭節點
    }
}

/*
解題思路：
這題的核心就是「模擬小學加法的手算過程」。
1. 兩個數字以鏈結串列的形式存放，低位在前，高位在後。
2. 逐位相加，記錄 carry（進位）。
3. 若鏈結串列長度不同，就把不存在的位數當作 0。
4. 建立新鏈結串列，將每次的 (sum % 10) 存入。
5. 如果最後還有 carry，就再補上一個節點。

時間複雜度：O(max(m, n))，m 和 n 分別是 l1 和 l2 的長度。
空間複雜度：O(max(m, n))，因為需要建立新的結果鏈結串列。
*/
