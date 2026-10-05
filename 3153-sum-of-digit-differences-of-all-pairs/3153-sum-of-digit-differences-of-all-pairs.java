class Solution {
    public long sumDigitDifferences(int[] nums) {
        long diff = 0;
        int numLen = (int) Math.floor(Math.log10(Math.abs(nums[0]))) + 1;

        int div = 1;

        for (int i = 0; i < numLen; i++) {
            int[] freq = new int[10];
            for (int num: nums) {
                freq[(num/div)%10]++;
            }

            for (int j = 0; j < 10; j++) {
                if (freq[j] > 0) {
                    diff += (long) freq[j]*(nums.length-freq[j]);
                }
            }

            div *= 10;
        }
        
        return diff/2;
    }
}