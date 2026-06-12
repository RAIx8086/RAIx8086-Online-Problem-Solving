// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/merge-two-sorted-lists/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/merge-two-sorted-lists/submissions/1751803359/
//	Format # 2:
//		https://leetcode.com/problems/merge-two-sorted-lists/post-solution/?submissionId=1751803359
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
    private:
        ListNode* makeNewLN(int val) {
            ListNode* newLN = new ListNode(val);
            return newLN;
        }

        void insertIntoMergedList(ListNode** mergedListHead, ListNode** mergedListTail, int val) {

            if ((*mergedListHead) == nullptr) {

                (*mergedListHead) = (*mergedListTail) = makeNewLN(val);

            } else {

                ListNode* newLN = makeNewLN(val);
                (*mergedListTail)->next = newLN;
                (*mergedListTail)       = newLN;

            }

        }

    public:
        ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
            ListNode *c1 = list1, *c2 = list2;
            ListNode *mergedListHead = nullptr, *mergedListTail = nullptr;

            while(c1 != nullptr && c2 != nullptr) {
                
                if (c1->val <= c2->val) {

                    insertIntoMergedList(&mergedListHead, &mergedListTail, c1->val);
                    c1 = c1->next;

                } else {

                    insertIntoMergedList(&mergedListHead, &mergedListTail, c2->val);
                    c2 = c2->next;

                }

            }

            while(c1 != nullptr) {
                insertIntoMergedList(&mergedListHead, &mergedListTail, c1->val);
                c1 = c1->next;
            }

            while(c2 != nullptr) {
                insertIntoMergedList(&mergedListHead, &mergedListTail, c2->val);
                c2 = c2->next;
            }

            return mergedListHead;

        }
};
