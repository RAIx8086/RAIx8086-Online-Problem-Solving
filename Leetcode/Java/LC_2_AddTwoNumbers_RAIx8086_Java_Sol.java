// ################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/add-two-numbers
// ################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/add-two-numbers/submissions/1749700610/
//	Format # 2:
//		https://leetcode.com/problems/add-two-numbers/post-solution/?submissionId=1749700610
// ################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################ //

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

    private class SumAndCarry {
        public int adjSum, nextCarry;

        public SumAndCarry(int prevCarry, int v1, int v2) {
            this.calcSumAndCarry(prevCarry, v1, v2);
        }

        public void calcSumAndCarry(int prevCarry, int v1, int v2) {
            int totSum = (prevCarry + v1 + v2);
            this.adjSum = ( (totSum >= 10) ? (totSum % 10) : totSum);
            this.nextCarry = ( (totSum >= 10) ? 1 : 0);
        }
    }

    private class SumLNInsertHelper {
        public ListNode sumLNHead, sumLNTail;

        public SumLNInsertHelper() {
            this.sumLNHead = this.sumLNTail = null;
        }

        public SumLNInsertHelper(ListNode sumLNHead, ListNode sumLNTail) {
            this.sumLNHead = sumLNHead;
            this.sumLNTail = sumLNTail;
        }

        public void insertSumData(int val) {
            ListNode newSumLN = new ListNode(val);
            if (this.sumLNHead == null) {
                this.sumLNHead = this.sumLNTail = newSumLN;
            } else {
                this.sumLNTail.next = newSumLN;
                this.sumLNTail = newSumLN;
            }
        }
    }

    public ListNode addTwoNumbers(ListNode L1, ListNode L2) {
        ListNode curL1 = L1, curL2 = L2;

        SumAndCarry sumAndCarry = new SumAndCarry(0, 0, 0);
        SumLNInsertHelper sumLNInsertHelperObj = new SumLNInsertHelper();

        while (curL1 != null && curL2 != null) {
            sumAndCarry.calcSumAndCarry(sumAndCarry.nextCarry, curL1.val, curL2.val);
            sumLNInsertHelperObj.insertSumData(sumAndCarry.adjSum);
            curL1 = curL1.next;
            curL2 = curL2.next;
        }

        while (curL1 != null) {
            sumAndCarry.calcSumAndCarry(sumAndCarry.nextCarry, curL1.val, 0);
            sumLNInsertHelperObj.insertSumData(sumAndCarry.adjSum);
            curL1 = curL1.next;
        }

        while (curL2 != null) {
            sumAndCarry.calcSumAndCarry(sumAndCarry.nextCarry, 0, curL2.val);
            sumLNInsertHelperObj.insertSumData(sumAndCarry.adjSum);
            curL2 = curL2.next;
        }

        if (sumAndCarry.nextCarry != 0) {
            sumLNInsertHelperObj.insertSumData(sumAndCarry.nextCarry);
        }

        return sumLNInsertHelperObj.sumLNHead;
    }
}
