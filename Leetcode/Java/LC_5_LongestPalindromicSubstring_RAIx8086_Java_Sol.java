// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/longest-palindromic-substring/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/longest-palindromic-substring/submissions/1750288963/
//	Format # 2:
//		https://leetcode.com/problems/longest-palindromic-substring/post-solution/?submissionId=1750288963
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //


class Solution {

    // Approach - 1: Naive Brute Force
    private boolean isPalindrome(String str) {
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    private String longestPalindromeViaNaiveBruteForce(String s) {
        if (s == null || s.length() < 1) return "";
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (isPalindrome(sub) && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        return longest;
    }


    // Approach - 2: Dynamic Programming
    public String longestPalindromeViaDynamicProgramming(String s) {
        if (s == null || s.length() < 1) return "";

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;

        // Single char palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j] && len > maxLen) {
                    start = i;
                    maxLen = len;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }


    // Approach - 3: Expand Around Center
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // length of palindrome
    }

    private String longestPalindromeViaExpandAroundCenter(String s) {
        
        if (s == null || s.length() < 1) return "";
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome
            int len1 = expandAroundCenter(s, i, i);

            // Even length palindrome
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int len = Math.max(len1, len2);
            
            if (len > (end - start)) {
                start = (i - (len - 1) / 2);
                end = (i + len / 2);
            }

        }
        
        return s.substring(start, end + 1);

    }


    // Approach - 4: Manacher's Algorithm
    private String preprocessForManacher(String s) {
        StringBuilder sb = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            sb.append("#").append(c);
        }
        sb.append("#$");
        return sb.toString();
    }

    public String longestPalindromeViaManachersAlgorithm(String s) {
        if (s == null || s.length() == 0) return "";

        // Transform S to avoid even/odd length issues
        String t = preprocessForManacher(s);
        int n = t.length();
        int[] p = new int[n];
        int center = 0, right = 0;

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;
            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            }

            // Expand around center
            while (t.charAt(i + (1 + p[i])) == t.charAt(i - (1 + p[i]))) {
                p[i]++;
            }

            // Adjust center and right boundary
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        // Find the longest palindrome
        int maxLen = 0, centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2; // Map back to original string
        return s.substring(start, start + maxLen);
    }


    public String longestPalindrome(String s) {
        // return longestPalindromeViaNaiveBruteForce(s);
        // return longestPalindromeViaDynamicProgramming(s);
        // return longestPalindromeViaExpandAroundCenter(s);
        return longestPalindromeViaManachersAlgorithm(s);
    }

}
