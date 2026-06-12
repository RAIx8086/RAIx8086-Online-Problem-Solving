// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/4sum/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/4sum/submissions/1751720523/
//	Format # 2:
//		https://leetcode.com/problems/4sum/post-solution/?submissionId=1751720523
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    public List<List<Integer>> fourSumViaIndexPointers(int[] nums, int target) {

        List<List<Integer>> outQuadIntLst = new ArrayList<>();
        Set<String> distinctQuadSet = new LinkedHashSet<String>();

        if (nums == null || nums.length < 4) {
            return outQuadIntLst;
        }

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < (n - 3); i++) {

            for (int j = (i + 1); j < (n - 2); j++) {

                long sum12 = nums[i] + nums[j];
                long remTgt34 = (target - sum12);

                int left = (j + 1), right = (n - 1);

                while (left < right) {

                    long sum34 = nums[left] + nums[right];

                    if (sum34 == remTgt34) {

                        List<Integer> curQuadIntLst = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        Collections.sort(curQuadIntLst);

                        String curQuadNormStrRep = 
                                curQuadIntLst.stream()
                                             .map(String::valueOf) // Convert each Integer to its String representation
                                             .collect(Collectors.joining(",")); // Join the String elements with a comma

                        if (!distinctQuadSet.contains(curQuadNormStrRep)) {

                            distinctQuadSet.add(curQuadNormStrRep);
                            outQuadIntLst.add(curQuadIntLst);

                        } else {

                            // Skip duplicates on left
                            while (left < right && nums[left] == nums[left + 1]) {
                                left++;
                            }

                            // Skip duplicates on right
                            while (left < right && nums[right] == nums[right - 1]) {
                                right--;
                            }

                        }

                        left++;
                        right--;

                    } else if (sum34 < remTgt34) {

                        left++;

                    } else {

                        right--;

                    }

                }

            }

        }

        return outQuadIntLst;

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return fourSumViaIndexPointers(nums, target);
    }
}
