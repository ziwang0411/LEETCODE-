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
    public int[] nextLargerNodes(ListNode head) {
        if (head==null) return new int[0];
        
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr!=null) {
            list.add(curr.val);
            curr = curr.next;
        }
        int n = list.size();
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i>=0; i--) {
             while (!stack.isEmpty() && stack.peek()<=list.get(i)) {
                 stack.pop();
             }
            if (stack.isEmpty()) res[i] = 0;
            else res[i] = stack.peek();
            stack.push(list.get(i));
        }
        return res;
    }
}