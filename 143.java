/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode head2 = reverseList(slow.next);
        slow.next = null;
        ListNode curr1 = head;
        ListNode curr2 = head2;
        while (curr1 != null && curr2 != null) {
            ListNode next1 = curr1.next;
            ListNode next2 = curr2.next;
            curr1.next = curr2;
            curr2.next = next1;
            curr1 = next1;
            curr2 = next2;
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head.next == null)
            return head;
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}