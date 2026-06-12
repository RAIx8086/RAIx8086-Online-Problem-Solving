// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/4sum/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/1751760771/
//	Format # 2:
//		https://leetcode.com/problems/remove-nth-node-from-end-of-list/post-solution/?submissionId=1751760771
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

    public ListNode removeNthFromEndViaNaive(ListNode head, int n) {

        if (head == null || head.next == null) {

            if ((head == null && n != 0) || (head.next == null && n != 1)) {
                String errMsg = "head" + (head == null ? "" : ".next") + " is null";
                errMsg += "but n = " + n + " | Unexpected! Invalid Input! Aborting!";
                System.err.println(errMsg);
                throw new RuntimeException(errMsg);
            }

            if (head.next == null && n == 1) {
                head = null;
            }

            return head;

        }

        int totalNumNodes = 0;
        ListNode curLN = head;

        while(curLN != null) {
            totalNumNodes++;
            curLN = curLN.next;
        }

        if (n > totalNumNodes) {
            String errMsg = "totalNumNodes = " + totalNumNodes + " | but n = " + n + " | Unexpected! Invalid Input! Aborting!";
            System.err.println(errMsg);
            throw new RuntimeException(errMsg);
        }

        int curPos = 1, stopPos = (totalNumNodes - n);
        curLN = head;
        while(curLN.next != null) {
            if (curPos >= stopPos) {
                break;
            }
            curPos++;
            curLN = curLN.next;
        }

        if (curLN == head && curPos > stopPos) {
            head = head.next;
        } else {
            curLN.next = (curLN.next).next;
        }

        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNthFromEndViaNaive(head, n);
    }
}
