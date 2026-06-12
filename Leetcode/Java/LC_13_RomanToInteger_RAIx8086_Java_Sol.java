// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/roman-to-integer/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/roman-to-integer/submissions/1750601107/
//	Format # 2:
//		https://leetcode.com/problems/roman-to-integer/post-solution/?submissionId=1750601107
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        // Iterate from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            int currentValue = romanMap.get(currentChar);

            if (currentValue < prevValue) {
                result -= currentValue; // Subtractive case
            } else {
                result += currentValue; // Additive case
            }
            prevValue = currentValue;
        }
        return result;        
    }
}
