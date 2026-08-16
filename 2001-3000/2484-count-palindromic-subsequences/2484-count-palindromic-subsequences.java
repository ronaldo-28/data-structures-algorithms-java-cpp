public class Solution {
    private static final int MOD = (int) 1e9 + 7;

    private char[] digits;
    private int length;

    public int countPalindromes(String s) {
        length = s.length();

        if (length < 5) {
            return 0;
        }

        digits = s.toCharArray();

        return layered();
    }

    private int layered() {
        // singles[d]: how many times digit 'd' has occurred so far
        long[] singles = new long[10];

        // pairs[j][d]: number of 2-digit subsequences 'j d' seen so far
        long[][] pairs = new long[10][10];

        // triplets[j][k]: number of 3-digit subsequences 'j k _'
        long[][] triplets = new long[10][10];

        // palindromeCandidates[j]: number of 4-length subsequences 'j k _ k' waiting to be completed with j
        long[] palindromeCandidates = new long[10];

        long result = 0;

        for (char c : digits) {
            int digit = c - '0';

            // accumulate complete palindromes of the form 'd _ _ _ d'
            result += palindromeCandidates[digit];

            //promote all the 3-lengths sequences into almost ready palindrome candidate in the form of 'j d _ d'
            for (int j = 0; j < 10; j++) {
                palindromeCandidates[j] += triplets[j][digit];
            }

            result = result;

            // promote all 2-digit subsequences 'j k' into 3-length subsequences of form 'j k d' where d is a middle digit 
                //for the futurepalindrome
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    triplets[j][k] += pairs[j][k];
                }
            }

            result = result;

            // build 2-digit subsequences by appending current digit to earlier single digits
            for (int j = 0; j < 10; j++) {
                pairs[j][digit] += singles[j];
            }

            result = result;

            // record the occurrence of current digit
            singles[digit]++;
        }

        return (int) (result % MOD);
    }

    private int streaming() {
        int result = 0;

        // build suffix pair counts (right side)
        int[] freqRight = new int[10];
        int[][] rightPairs = new int[10][10];

        for (int i = length - 1; i >= 0; i--) {
            int curr = digits[i] - '0';
            for (int a = 0; a < 10; a++) {
                rightPairs[curr][a] += freqRight[a];
            }
            freqRight[curr]++;
        }

        int[] freqLeft = new int[10];
        int[][] leftPairs = new int[10][10];

        // sweep through middle candidates and compute result
        for (int i = 0; i < length; i++) {
            int curr = digits[i] - '0';

            freqRight[curr]--;

            // remove s[i] from right frequencies (we are now at center)
            for (int a = 0; a < 10; a++) {
                rightPairs[curr][a] -= freqRight[a];
            }

            //accumulate the result
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    result = (int) ((result + (long) leftPairs[a][b] * rightPairs[b][a]) % MOD);
                }
            }

            // add s[i] to the left frequencies as we're moving past it next
            for (int a = 0; a < 10; a++) {
                leftPairs[a][curr] += freqLeft[a];
            }

            freqLeft[curr]++;
        }

        return result;
    }

    private int fullSweeps() {
        int[][][] left = new int[length][10][10]; // left[i][a][b] = number of a,b digits pairs to the left of index i
        int[][][] right = new int[length][10][10]; // right[i][a][b] = number of a,b digits pairs to the right of index i (backwards), so a after b in the source sequence
        preprocess(left, right);

        int result = 0;

        for (int middle = 2; middle < length - 2; middle++) {
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    result = (int) ((result + (long) left[middle - 1][a][b] * right[middle + 1][b][a]) % MOD);
                }
            }
        }

        return result;
    }

    private void preprocess(int[][][] left, int[][][] right) {
        int[] freq = new int[10];

        for (int i = 0; i < length; i++) {
            int digit = digits[i] - '0';

            if (i > 0) {
                for (int a = 0; a < 10; a++) {
                    for (int b = 0; b < 10; b++) {
                        left[i][a][b] = left[i - 1][a][b];

                        if (digit == b) {
                            left[i][a][b] += freq[a];
                        }
                    }
                }
            }
            freq[digit]++;
        }

        freq = new int[10];

        for (int i = length - 1; i >= 0; i--) {
            int digit = digits[i] - '0';

            if (i < length - 1) {
                for (int a = 0; a < 10; a++) {
                    for (int b = 0; b < 10; b++) {
                        right[i][b][a] = right[i + 1][b][a];

                        if (digit == b) {
                            right[i][b][a] += freq[a];
                        }
                    }
                }
            }
            freq[digit]++;
        }
    }
}