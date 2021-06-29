/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((a, b) -> (a.val - b.val));
        for (ListNode n : lists) {
            if (n != null) {
                pq.offer(n);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while (!pq.isEmpty()) {
            ListNode next = pq.poll();
            prev.next = next;
            next = next.next;
            if (next != null)
                pq.offer(next);
            prev = prev.next;
        }
        return dummy.next;
    }
}