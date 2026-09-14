class Solution {
    public int maximumStrongPairXor(int[] nums) {
        int max = 0;

        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                int xor = nums[i] ^ nums[j];
                if (max < xor && satisfies(nums[i], nums[j])) {
                    max =Math.max(max, xor);
                }
            }
        }

        return max;
    }

    public boolean satisfies(int a, int b) {
        return Math.abs(a-b)<=Math.min(a, b);
    }
}