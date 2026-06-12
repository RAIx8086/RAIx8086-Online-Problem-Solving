// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/swap-nodes-in-pairs/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/swap-nodes-in-pairs/submissions/1752572570/
//	Format # 2:
//		https://leetcode.com/problems/swap-nodes-in-pairs/post-solution/?submissionId=1752572570
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

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
    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        int k = 2, numNodes = 1;
        ListNode prvNodeM2 = null, prvNodeM1 = null, curNode = head;

        while (curNode != null) {

            if (numNodes % k == 0) {

                prvNodeM1.next = curNode.next;
                curNode.next = prvNodeM1;
                if (prvNodeM1 == head || prvNodeM2 == null) {
                    head = curNode;
                } else {
                    prvNodeM2.next = curNode;
                }
                curNode = prvNodeM1.next;

            } else {

                prvNodeM2 = prvNodeM1;
                prvNodeM1 = curNode;
                curNode = curNode.next;

            }

            numNodes++;

        }

        return head;

    }
}
