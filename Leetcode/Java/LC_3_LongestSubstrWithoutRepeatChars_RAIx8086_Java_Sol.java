// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/longest-substring-without-repeating-characters/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1749738520/
//	Format # 2:
//		https://leetcode.com/problems/longest-substring-without-repeating-characters/post-solution/?submissionId=1749738520
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    private void removeFromSetUntilDupCh(LinkedHashSet<Character> rollingDistinctCharSet, Character dupCh) {

        Iterator<Character> it = rollingDistinctCharSet.iterator();
        while (it.hasNext()) {
            Character curChSetElem = it.next();
            it.remove(); // Safe removal using Iterator
            if (curChSetElem.equals(dupCh)) {
                break;
            }
        }

        rollingDistinctCharSet.add(dupCh);
    }

    public int lengthOfLongestSubstring(String s) {

        if (s.length() <= 1) {
            return s.length();
        }

        int longestSubStrLen = 0;
        LinkedHashSet<Character> rollingDistinctCharSet = new LinkedHashSet<Character>();

        for (int i = 0 ; i < s.length() ; i++) {

            Character curSCh = s.charAt(i);

            if (!rollingDistinctCharSet.contains(curSCh)) {
                rollingDistinctCharSet.add(curSCh);
            } else {
                longestSubStrLen = Math.max(longestSubStrLen, rollingDistinctCharSet.size());
                removeFromSetUntilDupCh(rollingDistinctCharSet, curSCh);
            }

            if (i == (s.length() - 1)) {
                longestSubStrLen = Math.max(longestSubStrLen, rollingDistinctCharSet.size());
            }

        }

        return longestSubStrLen;

    }

}
