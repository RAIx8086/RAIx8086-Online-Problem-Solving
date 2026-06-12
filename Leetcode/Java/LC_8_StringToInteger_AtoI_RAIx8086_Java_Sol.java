// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/string-to-integer-atoi/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/string-to-integer-atoi/submissions/1750461539/
//	Format # 2:
//		https://leetcode.com/problems/zigzag-conversion/post-solution/?submissionId=1750461539
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

import java.math.BigInteger;

class Solution {

    private int sanitizeBigIntWithBoundsCheck(BigInteger bigInt) {

        if (bigInt.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            return Integer.MIN_VALUE;
        }

        if (bigInt.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            return Integer.MAX_VALUE;
        }

        return bigInt.intValue();
    }

    public int myAtoi(String s) {

        s = s.trim();

        if (s == null || s.isEmpty() || s.length() == 0) {
            return 0;            
        }

        int bgnIdx = 0;
        boolean negSign = false;

        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            bgnIdx = 1;
            if (s.charAt(0) == '-') {
                negSign = true;
            }
        }

        String outNumStr = "";
        boolean leadingNonZeroDigitEncountered = false;

        for (int i = bgnIdx ; i < s.length() ; i++) {

            int curChNum = (s.charAt(i) - '0');

            if (curChNum < 0 || curChNum > 9) {
                break;
            }

            if (curChNum != 0 && !leadingNonZeroDigitEncountered) {
                leadingNonZeroDigitEncountered = true;
            }

            if (curChNum == 0 && !leadingNonZeroDigitEncountered) {
                continue;
            }

            outNumStr += ("" + curChNum);

        }

        if (outNumStr == null || outNumStr == "" || outNumStr.isEmpty() || outNumStr.length() == 0) {
            outNumStr = "0";
        } else if (negSign) {
            outNumStr = ("-" + outNumStr);
        }

        BigInteger outNumStrBigInt = new BigInteger(outNumStr);
        return sanitizeBigIntWithBoundsCheck(outNumStrBigInt);

    }

}
