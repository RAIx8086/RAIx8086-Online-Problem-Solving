// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/longest-common-prefix/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/longest-common-prefix/submissions/1750640241/
//	Format # 2:
//		https://leetcode.com/problems/longest-common-prefix/post-solution/?submissionId=1750640241
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {
	
	private static final boolean vDbg = false;

	private static String debugPrintSanitizedStr(String s) {
		if (s == null || s.isEmpty() || s.length() == 0) {
			return "<STR_IS_NULL_OR_EMPTY>";
		}
		return s;
	}

	private static String debugPrintSanitizedStrChar(String s, int sIdx) {
		if (s == null || s.isEmpty() || s.length() == 0) {
			return "<STR_IS_NULL_OR_EMPTY>";
		}
		if (sIdx < s.length()) {
			return ("" + s.charAt(sIdx));
		}
		return "<STR_IDX_BEYOND_RANGE>";
	}

    public static String longestCommonPrefix(String[] strs) {

        int numStr = strs.length;

        String rollingRes = "";

        boolean reachedEndOfOneOrMoreStrs = false;

        for (int j = 0 ; ; j++) {

            Character curChAtJAcrossStrs = null;
            boolean misMatchAtJAcrossStrs = false;

            for (int i = 0 ; i < numStr ; i++) {

				if (vDbg) {
					System.err.println("j = " + j + " | i = " + i + " | " + 
									   "strs[" + i + "] = " + debugPrintSanitizedStr(strs[i]) + 
									   "strs[" + i + "].charAt(" + j + ") = " + debugPrintSanitizedStrChar(strs[i], j));
				}

                if (j >= strs[i].length()) {
					if (vDbg) { System.err.println("Reached End of One or More Strings"); }
                    reachedEndOfOneOrMoreStrs = true;
                    break;
                }

                if (curChAtJAcrossStrs == null) {
                    curChAtJAcrossStrs = strs[i].charAt(j);
                } else {
                    if (strs[i].charAt(j) != curChAtJAcrossStrs) {
						if (vDbg) { System.err.println("Char Mismatch :: strs[" + i + "].charAt(" + j + ") = " +
													   strs[i].charAt(j) + " != " + 
													   "Prev Char at Same Idx in Prev Str (curChAtJAcrossStrs) = " + 
													   curChAtJAcrossStrs); }
                        misMatchAtJAcrossStrs = true;
                        break;
                    }
                }

            }

			if (vDbg) {
				System.err.println("rollingRes = >" + rollingRes + "< | " + 
                                   "curChAtJAcrossStrs = >" + curChAtJAcrossStrs + "< | " + 
                                   "misMatchAtJAcrossStrs = " + misMatchAtJAcrossStrs);
			}

            if (reachedEndOfOneOrMoreStrs || misMatchAtJAcrossStrs) {
                return rollingRes;
            } else {
				rollingRes += curChAtJAcrossStrs;
			}

        }

    }

}

