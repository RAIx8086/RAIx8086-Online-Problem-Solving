// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/valid-parentheses/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/valid-parentheses/submissions/1751792221/
//	Format # 2:
//		https://leetcode.com/problems/valid-parentheses/post-solution/?submissionId=1751792221
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    public boolean solveIsValid(String s) {

        Stack<Character> chStack = new Stack<Character>();

        for (int sIdx = 0 ; sIdx < s.length() ; sIdx++) {
            Character curStrCh = s.charAt(sIdx);

            switch (curStrCh) {

                case '{':
                case '[':
                case '(':
                    chStack.push(curStrCh);
                    break;

                case '}':
                case ']':
                case ')':
                    Character expChStackTop = ( curStrCh == '}' ? '{' : ( curStrCh == ']' ? '[' : '(' ) );

                    if (chStack.isEmpty()) {
                        return false;
                    }

                    if (chStack.peek() != expChStackTop) {
                        return false;
                    }

                    chStack.pop();
                    break;

                default:
                    String errMsg = "Unrecognized / Invalid String Char :: sIdx = " + sIdx + " | curStrCh = >" + curStrCh + "<";
                    System.err.println(errMsg);
                    throw new RuntimeException(errMsg);

            }

        }

        if (!chStack.isEmpty()) {
            return false;
        }

        return true;

    }

    public boolean isValid(String s) {
        return solveIsValid(s);
    }
}
