class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] count = new int[128];        // ASCII counts inside the window
        int distinct = 0, left = 0, result = 0;
        char[] c = s.toCharArray();

        for (int right = 0; right < c.length; right++) {
            if (count[c[right]]++ == 0) distinct++;      // a new char enters the window

            while (distinct > 2) {                        // too many -> shrink from the left
                if (--count[c[left]] == 0) distinct--;    // a char fully left the window
                left++;
            }
            result = Math.max(result, right - left + 1);  // window is valid here
        }
        return result;
    }
}