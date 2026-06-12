// ################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/two-sum/
// ################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/two-sum/submissions/1749669408/
//	Format # 2:
//		https://leetcode.com/problems/two-sum/post-solution/?submissionId=1749669408
// ################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################ //

class Solution {

    private static final boolean vDbg = false;

    private static class NumsValIdx {
        public int num, idx;

        public NumsValIdx(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "( N: " + this.num + " | I: " + this.idx + " )";
        }
    }

    public static int[] twoSumViaSortBinSearch(int[] nums, int target) {
        int[] indices = new int[2];
        NumsValIdx[] numsValIdxArr = new NumsValIdx[nums.length];

        for (int i = 0 ; i < nums.length ; i++) {

            numsValIdxArr[i] = new NumsValIdx(nums[i], i);

            if (vDbg) {
    			System.err.println("twoSumViaSortBinSearch :: B4 SORT :: i = " + i + " | nums[" + i + "] = " + nums[i] + 
                                   " | numsValIdxArr[" + i + "] = " + numsValIdxArr[i]);
            }

        }

        Arrays.sort(numsValIdxArr, Comparator.comparingInt(a -> a.num));

        if (vDbg) {
			for (int i = 0 ; i < nums.length ; i++) {
				System.err.println("twoSumViaSortBinSearch :: AFTER SORT :: i = " + i + 
								   " | numsValIdxArr[" + i + "] = " + numsValIdxArr[i]);
			}
        }

        for (int i = 0 ; i < nums.length ; i++) {

            int othNum = (target - numsValIdxArr[i].num);

            int othIdx = Arrays.binarySearch(numsValIdxArr, new NumsValIdx(othNum, -1), 
											 Comparator.comparingInt(a -> a.num));

            if (vDbg) {
    			System.err.println("twoSumViaSortBinSearch :: i = " + i + " | " + 
                                   "numsValIdxArr[" + i + "] = " + numsValIdxArr[i] + 
		    					   " | target = " + target + " | othNum = " + othNum + " | othIdx = " + othIdx);
            }

            if ( othIdx >= 0 && othIdx != i && othIdx < nums.length ) {
                indices[0] = Math.min(numsValIdxArr[i].idx, numsValIdxArr[othIdx].idx);
                indices[1] = Math.max(numsValIdxArr[i].idx, numsValIdxArr[othIdx].idx);

                if (vDbg) {
        			System.err.println("twoSumViaSortBinSearch :: indices[0] = " + indices[0] + " | " + 
                                       "indices[1] = " + indices[1]);
                }

                break;
            }

        }

        return indices;
    }


    public static int[] twoSumViaMapOfLists(int[] nums, int target) {

        int[] indices = new int[2];
		LinkedHashMap<Integer, List<Integer>> numsValIdxMap = new LinkedHashMap<>();

        for (int i = 0 ; i < nums.length ; i++) {

			if (!numsValIdxMap.containsKey(nums[i])) {
				numsValIdxMap.put(nums[i], new ArrayList<Integer>());
			}
			numsValIdxMap.get(nums[i]).add(i);

            if (vDbg) {
    			System.err.println("twoSumViaMapOfLists :: i = " + i + " | nums[" + i + "] = " + nums[i] + 
                                   " | numsValIdxMap.get(" + nums[i] + ") = " + numsValIdxMap.get(nums[i]));
            }

        }
		
		if (vDbg) {
			System.err.println("twoSumViaMapOfLists :: numsValIdxMap = >" + numsValIdxMap + "<");
		}
		
		for (int curNumKey : numsValIdxMap.keySet()) {

			int othNumKey = (target - curNumKey);

			if (vDbg) {
				System.err.println("twoSumViaMapOfLists :: curNumKey = " + curNumKey + " | othNumKey = " + othNumKey + 
								   " | curNumIndices = " + numsValIdxMap.get(curNumKey) + 
								   " | othNumIndices = " + numsValIdxMap.get(othNumKey));
			}

			if (othNumKey == curNumKey) {

				List<Integer> curNumIndices = numsValIdxMap.get(curNumKey);

				if (!curNumIndices.isEmpty() && curNumIndices.size() >= 2) {

					indices[0] = Math.min(curNumIndices.get(0), curNumIndices.get(1));
					indices[1] = Math.max(curNumIndices.get(0), curNumIndices.get(1));

					if (vDbg) {
						System.err.println("twoSumViaMapOfLists :: H1 :: " + 
										   "indices[0] = " + indices[0] + " | " + 
										   "indices[1] = " + indices[1]);
					}

					break;

				}

			} else if (numsValIdxMap.containsKey(othNumKey)) {

				List<Integer> curNumIndices = numsValIdxMap.get(curNumKey);
				List<Integer> othNumIndices = numsValIdxMap.get(othNumKey);

				if (!curNumIndices.isEmpty() && !othNumIndices.isEmpty()) {

					indices[0] = Math.min(curNumIndices.get(0), othNumIndices.get(0));
					indices[1] = Math.max(curNumIndices.get(0), othNumIndices.get(0));

					if (vDbg) {
						System.err.println("twoSumViaMapOfLists :: H2 :: " + 
										   "indices[0] = " + indices[0] + " | " + 
										   "indices[1] = " + indices[1]);
					}

					break;

				}

			}

		}
		
		return indices;
	}

    public static int[] twoSum(int[] nums, int target) {
		////return twoSumViaSortBinSearch(nums, target);
		return twoSumViaMapOfLists(nums, target);
    }
}
