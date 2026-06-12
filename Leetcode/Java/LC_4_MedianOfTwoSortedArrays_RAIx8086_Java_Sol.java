// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/median-of-two-sorted-arrays/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/1749822511/
//	Format # 2:
//		https://leetcode.com/problems/median-of-two-sorted-arrays/post-solution/?submissionId=1749822511
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

	private static final boolean vDbg = false;

    private class MergedArrState {
        public int mergeIdxN1, mergeIdxN2;
        public int numMergedElems, immPrevMergedArrId;

        public MergedArrState() {
			this.initResetState();
        }

		public void initResetState() {
            this.mergeIdxN1 = this.mergeIdxN2 = this.numMergedElems = 0;
            this.immPrevMergedArrId = -1;
		}

        private void updateStateWhileMergingNext(int[] nums1, int[] nums2) {

            if (this.mergeIdxN2 >= nums2.length) {

				if (vDbg) {
					System.err.println("Merging nums1[" + this.mergeIdxN1 + "], " + 
									   "since nums2 index: mergeIdxN2 = >" + this.mergeIdxN2 + "< is OOB of nums2!");
				}

                this.mergeIdxN1++;
                this.immPrevMergedArrId = 1;
                this.numMergedElems++;

            } else if (this.mergeIdxN1 >= nums1.length) {

				if (vDbg) {
					System.err.println("Merging nums2[" + this.mergeIdxN2 + "], " + 
									   "since nums2 index: mergeIdxN1 = >" + this.mergeIdxN1 + "< is OOB of nums1!");
				}

                this.mergeIdxN2++;
                this.immPrevMergedArrId = 2;
                this.numMergedElems++;

            } else if (nums1[this.mergeIdxN1] <= nums2[this.mergeIdxN2]) {

				if (vDbg) {
					System.err.println("Merging nums1[" + this.mergeIdxN1 + "], " + 
									   "since nums1[" + this.mergeIdxN1 + "] <= nums2[" + this.mergeIdxN2 + "]");
				}

                this.mergeIdxN1++;
                this.immPrevMergedArrId = 1;
                this.numMergedElems++;

            } else {

				if (vDbg) {
					System.err.println("Merging nums2[" + this.mergeIdxN2 + "], " + 
									   "since nums1[" + this.mergeIdxN1 + "] > nums2[" + this.mergeIdxN2 + "]");
				}

                this.mergeIdxN2++;
                this.immPrevMergedArrId = 2;
                this.numMergedElems++;

            }

        }

        public double iterateAndMerge(int[] nums1, int[] nums2) {

            double finalMidValRes = 0D;
            int totArrSize = (nums1.length + nums2.length);
            int firstMidStopIdxInMergedArr = ( (totArrSize % 2 == 0) 
                                                    ? ( (totArrSize / 2) - 1 )
                                                    : ( (int) (Math.floor(totArrSize / 2)) ) );

            while (this.mergeIdxN1 < nums1.length || this.mergeIdxN2 < nums2.length) {

                this.updateStateWhileMergingNext(nums1, nums2);

                if (this.numMergedElems > firstMidStopIdxInMergedArr) {

                    double midVal1 = ( (this.immPrevMergedArrId == 1) 
                                            ? ( (double) (nums1[this.mergeIdxN1 - 1]) ) 
                                            : ( (double) (nums2[this.mergeIdxN2 - 1]) ) );

                    if (totArrSize % 2 == 0) {
                        this.updateStateWhileMergingNext(nums1, nums2);
                        double midVal2 = ( (this.immPrevMergedArrId == 1) 
                                                ? ( (double) (nums1[this.mergeIdxN1 - 1]) ) 
                                                : ( (double) (nums2[this.mergeIdxN2 - 1]) ) );

                        finalMidValRes = ( (midVal1 + midVal2) / 2 );
						if (vDbg) { System.err.println("H4 :: finalMidValRes = " + finalMidValRes); }
                        break;
                    } else {
                        finalMidValRes = midVal1;
						if (vDbg) { System.err.println("H5 :: finalMidValRes = " + finalMidValRes); }
                        break;
                    }

                }

            }

            return finalMidValRes;

        }

    }
	
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0 || nums2.length == 0) {

            if (nums1.length == 0 && nums2.length == 0) {
				if (vDbg) { System.err.println("H1 :: Returning Zero"); }
                return 0D;
            }

            int[] nonEmptyNumsArr;

            if (nums1.length == 0) {
				if (vDbg) { System.err.println("nonEmptyNumsArr = nums2"); }
                nonEmptyNumsArr = nums2;
            } else {
				if (vDbg) { System.err.println("nonEmptyNumsArr = nums1"); }
                nonEmptyNumsArr = nums1;
            }

			double finMidVal = 0D;

            if (nonEmptyNumsArr.length % 2 == 0) {
                int midIdx1 = ((nonEmptyNumsArr.length / 2) - 1);
                int midIdx2 =  (nonEmptyNumsArr.length / 2);
                finMidVal = ( ( (double) (nonEmptyNumsArr[midIdx1]) + (double) (nonEmptyNumsArr[midIdx2]) ) / 2);
				if (vDbg) { System.err.println("H2 :: finMidVal = " + finMidVal); }
            } else {
                int midIdx = ( (int) (Math.floor(nonEmptyNumsArr.length / 2)) );
                finMidVal = ( (double) (nonEmptyNumsArr[midIdx]) );
				if (vDbg) { System.err.println("H3 :: finMidVal = " + finMidVal); }
            }

			return finMidVal;

        }

        MergedArrState mergedArrState = new MergedArrState();
        return mergedArrState.iterateAndMerge(nums1, nums2);
    }
}
