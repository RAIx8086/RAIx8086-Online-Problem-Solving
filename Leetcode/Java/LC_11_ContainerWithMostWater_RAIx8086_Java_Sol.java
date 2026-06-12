// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/container-with-most-water/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/container-with-most-water/submissions/1751379620/
//	Format # 2:
//		https://leetcode.com/problems/container-with-most-water/post-solution/?submissionId=1751379620
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {
    public int maxArea(int[] height) {

        int maxAreaRes = Integer.MIN_VALUE;

        for (int lftIdx = 0, rgtIdx = (height.length - 1) ; lftIdx < height.length && rgtIdx > lftIdx ;) {

            int curHeight = Math.min(height[lftIdx], height[rgtIdx]);
            int curWidth  = (rgtIdx - lftIdx);

            int curArea  = (curWidth * curHeight);
            maxAreaRes = Math.max(maxAreaRes, curArea);

            if (curHeight == height[lftIdx]) {
                lftIdx++;
            } else {
                rgtIdx--;
            }

        }

        return maxAreaRes;

    }
}
