// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/palindrome-number/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/palindrome-number/submissions/1750492167/
//	Format # 2:
//		https://leetcode.com/problems/palindrome-number/submissions/1750492167/
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {
    private String reverseStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    private boolean isPalindromeViaStrAndRevStr(int x) {
        String xStr = String.valueOf(x);
        String revXStr = reverseStr(xStr);
        return (xStr.equals(revXStr));
    }

    private boolean palindromeCheckForStr(String str) {
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    private boolean isPalindromeViaStrAndPalCheck(int x) {
        return palindromeCheckForStr(String.valueOf(x));
    }

    private int reverseInt(int x) {
        int revX = 0;
        while (x > 0) {
            revX = revX * 10 + x % 10;
            x = x / 10;
        }
        return revX;
    }

    private boolean isPalindromeDirOnInt(int x) {
        if ( x < 0) {
            return false;
        }
        
        return (x == reverseInt(x));
    }

    public boolean isPalindrome(int x) {
        // return isPalindromeViaStrAndRevStr(x);
        // return isPalindromeViaStrAndPalCheck(x);
        return isPalindromeDirOnInt(x);
    }
}
