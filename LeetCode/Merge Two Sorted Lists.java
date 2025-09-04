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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立 dummy 節點方便操作，最終返回 dummy.next
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // 遍歷兩個鏈表，依次選擇較小的節點接到 curr
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // 如果其中一個鏈表還有剩餘，直接接到後面
        if (list1 != null) curr.next = list1;
        if (list2 != null) curr.next = list2;

        return dummy.next;
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 合併兩個已排序鏈表，使結果仍為排序鏈表。

2. 解法設計（迭代法）：
   - 建立 dummy 節點，curr 指向當前操作節點。
   - 遍歷 list1 與 list2：
       - 比較兩個節點值，將較小的接到 curr，移動該鏈表指針。
       - curr 向前移動。
   - 遍歷完後，將剩餘節點（非空鏈表）接到 curr.next。
   - 返回 dummy.next（跳過 dummy）。

3. 範例：
   - list1 = [1,2,4], list2 = [1,3,4]
   - 合併過程：
     1 → 1 → 2 → 3 → 4 → 4

4. 為什麼可行：
   - 每次選擇較小節點，保證結果鏈表有序。
   - 單次遍歷，時間複雜度 O(m+n)，空間 O(1)

時間複雜度：O(m+n)  
空間複雜度：O(1)
*/
