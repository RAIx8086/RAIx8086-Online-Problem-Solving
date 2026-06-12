// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/3sum/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/3sum/submissions/1750773357/
//	Format # 2:
//		https://leetcode.com/problems/3sum/post-solution/?submissionId=1750773357
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    private String genNormStrRepForCouplet(int i, int j) {
        int a = Math.min(i, j);
        int b = Math.max(i, j);

        return "( " + a + " | " + b + " )";
    }

    private String genNormStrRepForTriplet(int i, int j, int k) {
        int a = Math.min(i, Math.min(j, k));
        int c = Math.max(i, Math.max(j, k));
        int b;

        if ( (a == j && c == k) || (a == k && c == j) ) {
            b = i;
        } else if ( (a == i && c == k) || (a == k && c == i) ) {
            b = j;
        } else {
            b = k;
        }

        return "( " + a + " | " + b + " | " + c + " )";
    }

    private void addDistinctZeroSumTriplet(Set<String> distinctTripletSet, 
                                           List<List<Integer>> distinctTripletLst,
                                           int iVal, int jVal, int kVal) {
        
        String tripletSetKey = genNormStrRepForTriplet(iVal, jVal, kVal);

        if (!distinctTripletSet.contains(tripletSetKey)) {
            distinctTripletSet.add(tripletSetKey);

            List<Integer> curTripletLst = new ArrayList<Integer>();
            curTripletLst.add(iVal); curTripletLst.add(jVal); curTripletLst.add(kVal);
            distinctTripletLst.add(curTripletLst);
        }

    }

    // 309 (out of 314) Test-Cases PASS; Remaining experience TLEs (as expected)
    private List<List<Integer>> threeSumViaBruteForceAndSet(int[] nums) {
    
        int nL = nums.length;
        List<List<Integer>> distinctTripletLst = new ArrayList<List<Integer>>();
        Set<String> distinctTripletSet = new LinkedHashSet<String>();

        for (int i = 0 ; i < nL ; i++) {

            for (int j = (i + 1) ; j < nL ; j++) {

                // if (j == i) { continue; }

                for (int k = (j + 1) ; k < nL ; k++) {

                    // if (k == j || k == i) { continue; }

                    if ( (nums[i] + nums[j] + nums[k]) == 0 ) {
                        addDistinctZeroSumTriplet(distinctTripletSet, distinctTripletLst, nums[i], nums[j], nums[k]);
                    }

                }

            }

        }

        return distinctTripletLst;

    }


    private static int arrBinarySearch(int[] arr, int x, boolean findFirst) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                result = mid;
                // If finding first, move left; otherwise, move right
                if (findFirst) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }


    // 309 (out of 314) Test-Cases PASS; Remaining experience TLEs (as expected)
    private List<List<Integer>> threeSumViaSortAndBinSearch(int[] nums) {

        int nL = nums.length;
        List<List<Integer>> distinctTripletLst = new ArrayList<List<Integer>>();

        Set<String> distinctCoupletSet = new LinkedHashSet<String>();
        Set<String> distinctTripletSet = new LinkedHashSet<String>();

        Arrays.sort(nums);

        for (int i = 0 ; i < nL ; i++) {

            for (int j = (i + 1) ; j < nL ; j++) {

                String coupletSetKey = genNormStrRepForCouplet(nums[i], nums[j]);

                if (distinctCoupletSet.contains(coupletSetKey)) {
                    continue;
                }

                distinctCoupletSet.add(coupletSetKey);

                int reqThirdNumVal    = (-1 * (nums[i] + nums[j]) );
                int reqThirdNumBgnIdx = arrBinarySearch(nums, reqThirdNumVal, true);

                if (reqThirdNumBgnIdx >= 0 && reqThirdNumBgnIdx < nL) {

                    for (int k = reqThirdNumBgnIdx ; k < nL && nums[k] == reqThirdNumVal ; k++) {

                        if (k != i && k != j) {

                            distinctCoupletSet.add(genNormStrRepForCouplet(nums[j], nums[k]));
                            distinctCoupletSet.add(genNormStrRepForCouplet(nums[i], nums[k]));

                            addDistinctZeroSumTriplet(distinctTripletSet, distinctTripletLst, 
                                                      nums[i], nums[j], nums[reqThirdNumBgnIdx]);

                        }

                    }

                }

            }

        }

        return distinctTripletLst;

    }


    public List<List<Integer>> threeSumViaTwoIndexPointers(int[] nums) {

        List<List<Integer>> output = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return output;
        }

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < (n - 2); i++) {

            // Early termination: nums[i] > 0 means further sums > 0
            if (nums[i] > 0) {
                break;
            }

            // Skip duplicate i's
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = (i + 1), right = (n - 1);
            int target = (-1 * nums[i]);

            while (left < right) {

                int sum2 = nums[left] + nums[right];

                if (sum2 == target) {

                    output.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates on left
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // Skip duplicates on right
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum2 < target) {

                    left++;

                } else {

                    right--;

                }

            }

        }

        return output;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        // return threeSumViaBruteForceAndSet(nums);
        // return threeSumViaSortAndBinSearch(nums);
        return threeSumViaTwoIndexPointers(nums);
    }

}
