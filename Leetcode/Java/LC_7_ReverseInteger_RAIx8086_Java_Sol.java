// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/reverse-integer/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/reverse-integer/submissions/1750423078/
//	Format # 2:
//		https://leetcode.com/problems/zigzag-conversion/post-solution/?submissionId=1750423078
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    private String reverseStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    private String reverseNumStr(String numStr) {

        int numStrBgnIdx = 0;

        if (numStr.charAt(0) == '-') {
            numStrBgnIdx = 1;
        }

        String revNumStr = reverseStr(numStr.substring(numStrBgnIdx, numStr.length()));

        if (numStrBgnIdx == 1) {
            revNumStr = "-" + revNumStr;
        }

        return revNumStr;

    }

    public int reverse(int x) {
        String xStr = String.valueOf(x);
        String revXStr = reverseNumStr(xStr);
        long revXStrLong = Long.parseLong(revXStr);
        if (revXStrLong < Integer.MIN_VALUE || revXStrLong > Integer.MAX_VALUE) {
            return 0;
        } else {
            return ( (int) (revXStrLong) );
        }
    }
}
