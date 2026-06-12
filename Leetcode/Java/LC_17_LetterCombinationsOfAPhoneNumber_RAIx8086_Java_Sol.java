// ################################################################################################################################ //
// Problem URL:
//	https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// ################################################################################################################################ //
// My Submission URL:
//	Format # 1:
//		https://leetcode.com/problems/letter-combinations-of-a-phone-number/submissions/1751669868/
//	Format # 2:
//		https://leetcode.com/problems/letter-combinations-of-a-phone-number/post-solution/?submissionId=1751669868
// ################################################################################################################################ //
// LeetCode Profile Link:
//	https://leetcode.com/u/RAIx86/
// ################################################################################################################################ //

class Solution {


    private LinkedHashMap<Integer, String> initPhoneDigitCharMap() {
        LinkedHashMap<Integer, String> phDgtChMap = new LinkedHashMap<Integer, String>();

        phDgtChMap.put(0, null);  phDgtChMap.put(1, null);
        phDgtChMap.put(2, "abc"); phDgtChMap.put(3, "def");
        phDgtChMap.put(4, "ghi"); phDgtChMap.put(5, "jkl");
        phDgtChMap.put(6, "mno"); phDgtChMap.put(7, "pqrs");
        phDgtChMap.put(8, "tuv"); phDgtChMap.put(9, "wxyz");

        return phDgtChMap;
    }


    private void checkValidInput(String digits) {

        // Iterating through the string once to validate that there's no invalid digits in the string.
        for (int dgtStrIdx = 0 ; dgtStrIdx < digits.length() ; dgtStrIdx++) {

            Character digitCh = digits.charAt(dgtStrIdx);
            int digitNum = (digitCh - '0');

            if (digitNum < 2 || digitNum > 9) {

                String errMsg = "dgtStrIdx = " + dgtStrIdx + " | digitCh = " + digitCh + " | is not a valid digit! Aborting!";
                System.err.println(errMsg);
                throw new RuntimeException(errMsg);

            }

        }

    }


    private void letterCombinationsRecursive(LinkedHashMap<Integer, String> phDgtChMap, List<String> resultCombinations, 
                                             String digits, int dgtStrBgnIdx, String rollingPrefixStr) {

        if (dgtStrBgnIdx > (digits.length() - 1)) {
            return;
        }

        int digitNum = (digits.charAt(dgtStrBgnIdx) - '0');
        String curDigitChars = phDgtChMap.get(digitNum);

        for (int chIdx = 0 ; chIdx < curDigitChars.length() ; chIdx++) {

            if (dgtStrBgnIdx == (digits.length() - 1)) {

                resultCombinations.add(rollingPrefixStr + curDigitChars.charAt(chIdx));

            } else {

                letterCombinationsRecursive(phDgtChMap, resultCombinations,
                                            digits, (dgtStrBgnIdx + 1), rollingPrefixStr + curDigitChars.charAt(chIdx));

            }
        }

    }


    private List<String> letterCombinationsRecursiveWrapper(String digits) {

        LinkedHashMap<Integer, String> phDgtChMap = initPhoneDigitCharMap();
        List<String> resultCombinations = new ArrayList<String>();

        // checkValidInput(digits);

        letterCombinationsRecursive(phDgtChMap, resultCombinations, digits, 0, "");

        return resultCombinations;
    }


    public List<String> letterCombinations(String digits) {
        return letterCombinationsRecursiveWrapper(digits);
    }


}
