class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;

        // 先讓 first 前進 n+1 步
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 同步移動，直到 first 到尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 刪除第 n 個節點
        second.next = second.next.next;

        return dummy.next;
    }
}
