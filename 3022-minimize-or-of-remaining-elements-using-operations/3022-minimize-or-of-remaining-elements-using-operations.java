class Solution {
    public int minOrAfterOperations(int[] nums, int k) {

        int answer = 0;
        int mask = 0;

        for (int bit = 29; bit >= 0; bit--) {

            int candidate = mask | (1 << bit);

            int groups = 0;
            int current = -1;

            for (int num : nums) {

                current &= num;

                if ((current & candidate) == 0) {
                    groups++;
                    current = -1;
                }
            }

            if (nums.length - groups <= k) {
                mask = candidate;
            } else {
                answer |= 1 << bit;
            }
        }

        return answer;
    }
}