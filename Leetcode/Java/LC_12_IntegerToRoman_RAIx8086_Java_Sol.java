// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/integer-to-roman/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/integer-to-roman/submissions/1750599532/
//	Format # 2:
//		https://leetcode.com/problems/integer-to-roman/post-solution/?submissionId=1750599532
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(romanSymbols[i]);
                num -= values[i];
            }
        }
        return result.toString();
    }
}
