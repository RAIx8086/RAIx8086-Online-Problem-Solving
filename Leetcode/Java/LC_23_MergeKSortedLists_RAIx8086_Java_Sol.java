// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/merge-k-sorted-lists/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/merge-k-sorted-lists/submissions/1751852806/
//	Format # 2:
//		https://leetcode.com/problems/merge-k-sorted-lists/post-solution/?submissionId=1751852806
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

    // Approach - 1: "Naive": Find Minimum Or Sort Each Time - Helper Method
    private int findMinIdxAmongCurPtrs(final ListNode[] curPtrs) {

        int minIdx = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;

        for (int lstIdx = 0 ; lstIdx < curPtrs.length ; lstIdx++) {

            if (curPtrs[lstIdx] != null && curPtrs[lstIdx].val < minVal) {

                minVal = curPtrs[lstIdx].val;
                minIdx = lstIdx;

            }

        }

        return minIdx;

    }

    // Approach - 1: "Naive": Find Minimum Or Sort Each Time
    // TC:
    //      O(n * k) for Finding Minimum Each Time _OR_ O(n * k * log(k)) for Sorting each time.
    //          where 'n' is the number of total elements in all lists, and 'k' is the number of lists.
    // SC: 
    //      O(1) - no extra data structures used
    // Passes all Leetcode Test-Cases / Constraints but is quite slow.
    public ListNode mergeKListsViaNaiveFindMinOrSortEachTime(ListNode[] lists, boolean sortEachTime) {

        ListNode[] curPtrs = Arrays.copyOf(lists, lists.length);
        ListNode mergedListHead = null, mergedListTail = null;

        while (true) {

            int minIdx;

            if (sortEachTime) {

                Arrays.sort(curPtrs, Comparator.nullsLast(Comparator.comparingInt(n -> n.val)));
                minIdx = ( (curPtrs.length == 0 || curPtrs[0] == null) ? Integer.MIN_VALUE : 0 );

            } else {

                minIdx = findMinIdxAmongCurPtrs(curPtrs);

            }

            if (minIdx < 0) {
                break;
            }

            ListNode newLNToBeInserted = new ListNode(curPtrs[minIdx].val);

            if (mergedListHead == null) {

                mergedListHead = mergedListTail = newLNToBeInserted;

            } else {

                mergedListTail.next = newLNToBeInserted;
                mergedListTail      = newLNToBeInserted;

            }

            curPtrs[minIdx] = curPtrs[minIdx].next;

        }

        return mergedListHead;

    }


    // Approach - 2: Using Min Heap via Priority Queue
    // TC:
    //      O(n * log k)
    //          where 'n' is the number of total elements in all lists, and 'k' is the number of lists.
    // SC:
    //      O (k) for the Min-Heap Prio-Q of size 'k'
    // Passes all Leetcode Test-Cases / Constraints & is much faster.
    public ListNode mergeKListsViaMinHeapPrioQ(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // Initialize heap with first node of each list
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {

            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            if (minNode.next != null) {
                pq.offer(minNode.next);
            }

        }

        return dummy.next;

    }


    // Approach - 3: "Divide & Conquer" in "Merge Sort" style - Helper Method
    private ListNode mergeDivNConRec(ListNode[] lists, int left, int right) {

        if (left == right) {
            return lists[left];
        }

        int mid = left + (right - left) / 2;
        ListNode l1 = mergeDivNConRec(lists, left, mid);
        ListNode l2 = mergeDivNConRec(lists, mid + 1, right);

        return mergeTwoLists(l1, l2);

    }

    // Approach - 3: "Divide & Conquer" in "Merge Sort" style - Helper Method
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
        }

        if (l2 != null) {
            tail.next = l2;
        }

        return dummy.next;

    }

    // Approach - 3: "Divide & Conquer" in "Merge Sort" style
    // TC:
    //      O(n * log k)
    //          where 'n' is the number of total elements in all lists, and 'k' is the number of lists.
    // SC:
    //      O (log k) for the Recursion Depth (or Explicit Stack in-case of Non-Recursive / Iterative)
    // Passes all Leetcode Test-Cases / Constraints & is much faster.
    public ListNode mergeKListsViaDivNConRec(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        return mergeDivNConRec(lists, 0, lists.length - 1);

    }


    public ListNode mergeKLists(ListNode[] lists) {

        // return mergeKListsViaNaiveFindMinOrSortEachTime(lists, false);
        // return mergeKListsViaNaiveFindMinOrSortEachTime(lists, true);
        // return mergeKListsViaMinHeapPrioQ(lists);
        return mergeKListsViaDivNConRec(lists);

    }

}
