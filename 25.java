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
        // 1 2 (3) 4 5 reverse (1,2) return 2, 1 point to (RKG(3,4,5))
        // 2 1 3 4 (5) reverse(3,4), return 4, 3 point to (RKG(5))
        // 2 1 4 3 5 return 5 
        if (head ==null) return head;
        ListNode pointer = head;
        int i = k;
        while (i>1) {
            pointer=pointer.next;
            if (pointer==null) return head;
            i--;
        }
        ListNode newHead = pointer;
        ListNode nextHead = pointer.next;
        newHead.next = null;
        reverse(head);
        head.next = reverseKGroup(nextHead, k);
        return newHead;
    }
    private void reverse(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr!=null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }
}