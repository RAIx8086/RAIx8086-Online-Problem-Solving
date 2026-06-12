// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/3sum-closest/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/3sum-closest/submissions/1751555237/
//	Format # 2:
//		https://leetcode.com/problems/3sum-closest/post-solution/?submissionId=1751555237
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    public int threeSumClosestViaSortAndTwoIndexPointers(int[] nums, int target) {

        if (nums == null || nums.length < 3) {
            return 0;
        }

        if (nums.length == 3) {
            return (nums[0] + nums[1] + nums[2]);
        }

        Arrays.sort(nums);

        int n = nums.length;
        int minDistFromTgt = Integer.MAX_VALUE, minSumVal = Integer.MAX_VALUE;

        for (int i = 0; i < (n - 2); i++) {

            int left = (i + 1), right = (n - 1);

            while (left < right) {

                int curSum = nums[i] + nums[left] + nums[right];

                int curDistFromTgt = Math.abs(target - curSum);

                if (curDistFromTgt < minDistFromTgt) {

                    minDistFromTgt = curDistFromTgt;
                    minSumVal = curSum;

                }

                if (curSum < target) {

                    left++;

                } else {

                    right--;

                }

            }

        }

        return minSumVal;

    }


    public int threeSumClosest(int[] nums, int target) {
        return threeSumClosestViaSortAndTwoIndexPointers(nums, target);
    }
}
