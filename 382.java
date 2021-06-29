/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    ListNode head;
    Random rand;

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be
     *             not null, so it contains at least one node.
     */
    public Solution(ListNode head) {
        this.head = head;
        rand = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode curr = head;
        int res = curr.val;
        int count = 1;
        while (curr.next != null) {
            count++;
            curr = curr.next;
            int num = rand.nextInt(count);
            if (num == 0)
                res = curr.val;
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(head); int param_1 = obj.getRandom();
 */