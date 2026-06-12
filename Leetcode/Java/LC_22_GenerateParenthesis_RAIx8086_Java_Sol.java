// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/generate-parentheses/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/generate-parentheses/submissions/1770649745/
//	Format # 2:
//		https://leetcode.com/problems/generate-parentheses/post-solution/?submissionId=1770649745
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    // Common Helper Enum - to switch between Algorithms
    private static enum AlgoChoice {
        DYNPROG("DYNPROG"),
        RECBACK("RECBACK");

        private final String displayName;

        AlgoChoice(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return this.displayName;
        }
    }


    // Approach 1: Recursion with Backtracking
    private List<String> solveGenParViaRecBacktrack(int n) {
        List<String> result = new ArrayList<>();
        solveGenParRec(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void solveGenParRec(List<String> result, StringBuilder current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        if (open < max) {
            current.append('(');
            solveGenParRec(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            solveGenParRec(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1);
        }
    }

    // Approach 2: Dynamic Programming
    private List<String> solveGenParViaDynProg(int n) {
        // dp[i] = all combinations of well-formed parentheses with i pairs
        List<List<String>> dp = new ArrayList<>();
        dp.add(Collections.singletonList("")); // base case: n=0

        for (int i = 1; i <= n; i++) {
            List<String> current = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String left : dp.get(j)) {
                    for (String right : dp.get(i - 1 - j)) {
                        current.add("(" + left + ")" + right);
                    }
                }
            }
            dp.add(current);
        }
        return dp.get(n);
    }

    public List<String> solveGenerateParenthesis(int n) {
        // AlgoChoice algoChoice = AlgoChoice.DYNPROG;
        AlgoChoice algoChoice = AlgoChoice.RECBACK;
        switch (algoChoice) {
            case AlgoChoice.DYNPROG:
                return solveGenParViaDynProg(n);
            case AlgoChoice.RECBACK:
            default:
                return solveGenParViaRecBacktrack(n);
        }
    }

    public List<String> generateParenthesis(int n) {
        return solveGenerateParenthesis(n);
    }
}
