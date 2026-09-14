class Solution {
    public long numberOfWays(String s) {

        long zeros = 0;
        long ones = 0;

        long zeroOne = 0;
        long oneZero = 0;

        long answer = 0;

        for (char c : s.toCharArray()) {

            if (c == '0') {
                answer += zeroOne;
                oneZero += ones;
                zeros++;
            } else {
                answer += oneZero;
                zeroOne += zeros;
                ones++;
            }
        }

        return answer;
    }
}