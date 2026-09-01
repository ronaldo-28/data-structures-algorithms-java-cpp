class Solution {
    public int equalDigitFrequency(String s) {
        final int n = s.length();
        byte[] bytes = s.getBytes();
        int[] digits = new int[n];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            digits[i] = bytes[i] - '0';
        }
        
        for (int start = 0; start < n; ++start) {
            findUniqueSubstringsEqualFreq(digits, start, set);
        }

        return set.size();
    }

    private void findUniqueSubstringsEqualFreq(int[] digits, int start, Set<Integer> set) {
        final int n = digits.length;
        int[] freq = new int[10];
        int uniqDigits = 0;
        int maxFreq = 0;
        int hashVal = 0;

        for (int end = start; end < n; ++end) {
            int digit = digits[end];

            if (freq[digit] == 0) {
                ++uniqDigits;
            }

            ++freq[digit];
            maxFreq = Math.max(maxFreq, freq[digit]);
            hashVal = rollingHash(hashVal, digit);

            if (maxFreq * uniqDigits == end - start + 1) {
                set.add(hashVal);
            }
        }
    }

    private int rollingHash(int hashVal, int digit) {
        return (67 * hashVal + digit + 1) % 1000_000_000;
    }
}