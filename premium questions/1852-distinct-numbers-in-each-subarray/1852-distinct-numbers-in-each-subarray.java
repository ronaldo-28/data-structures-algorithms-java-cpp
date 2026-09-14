class Solution {

    public int[] distinctNumbers(int[] nums, int k) {
        // Find the maximum value in the input array
        int maxValue = 0;
        for (int num : nums) {
            if (num > maxValue) {
                maxValue = num;
            }
        }

        // Create a frequency array based on the maximum value in the input
        int[] freq = new int[maxValue + 1];
        int distinctCount = 0;
        int[] answer = new int[nums.length - k + 1];
        int ansPos = 0;

        for (int pos = 0; pos < nums.length; pos++) {
            // Add new number to window
            freq[nums[pos]]++;
            if (freq[nums[pos]] == 1) {
                distinctCount++;
            }

            // Remove number from previous window
            if (pos >= k) {
                freq[nums[pos - k]]--;
                if (freq[nums[pos - k]] == 0) {
                    distinctCount--;
                }
            }

            // Store result when window is complete
            if (pos + 1 >= k) {
                answer[ansPos++] = distinctCount;
            }
        }

        return answer;
    }
}