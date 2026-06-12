// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/zigzag-conversion/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/zigzag-conversion/submissions/1750408413/
//	Format # 2:
//		https://leetcode.com/problems/zigzag-conversion/post-solution/?submissionId=1750408413
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {

    private static final boolean vDbg = false;

    public static String convert(String s, int numRows) {

        List<String> encodedGrid = new ArrayList<String>();

        for (int rowIdx = 0 ; rowIdx < numRows ; rowIdx++) {
            encodedGrid.add(rowIdx, new String(""));
        }

        int colIdx = 0;
        boolean zigZagEnabled = false;

        for (int strIdx = 0, rowIdx = 0 ; strIdx < s.length() ; strIdx++, rowIdx = ((rowIdx + 1) % numRows)) {

            Character curStrCh = s.charAt(strIdx);

			if (vDbg) {
				System.err.println("strIdx = " + strIdx + " | curStrCh = " + curStrCh + 
								   " | encodedGrid = >" + encodedGrid + "<");
			}

            int effRowIdx = rowIdx;

            if (zigZagEnabled) {
                effRowIdx = ((numRows - 1) - (rowIdx + 1));
				effRowIdx = (effRowIdx < 0 ? 0 : effRowIdx);
                if (effRowIdx == 0) {
                    rowIdx = effRowIdx;
                    zigZagEnabled = false;
                }
            }

            String existingStr = ((encodedGrid.get(effRowIdx) == null) ? "" : encodedGrid.get(effRowIdx));
            encodedGrid.set(effRowIdx, existingStr + "" + curStrCh);

            if (rowIdx == (numRows - 1)) {
                zigZagEnabled = true;
                colIdx++;
            }

        }

        if (vDbg) {
            System.err.println("INIT: encodedGrid = >" + encodedGrid + "<");
        }

        String encodedStr = "";

        for (int i = 0 ; i < encodedGrid.size() ; i++) {
            for (int j = 0 ; j < encodedGrid.get(i).length() ; j++) {
                encodedStr += ("" + encodedGrid.get(i).charAt(j));
            }
        }

        return encodedStr;

    }

}
