class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int lp = 0, rp = 0;

        int leftProduct = 1;
        int rightProduct = 1;
        int maxProduct = Integer.MIN_VALUE;

        // Forward window (lp -> rp)
        while (rp < n) {
            leftProduct *= nums[rp];
            maxProduct = Math.max(maxProduct, leftProduct);

            // Reset the left window if product hits zero
            if (leftProduct == 0) {
                leftProduct = 1;
                lp = rp + 1;
            }
            rp++;
        }

        // Backward window (rp -> lp)
        rp = n - 1;
        while (rp >= 0) {
            rightProduct *= nums[rp];
            maxProduct = Math.max(maxProduct, rightProduct);

            // Reset the right window if product hits zero
            if (rightProduct == 0) {
                rightProduct = 1;
            }
            rp--;
        }

        return maxProduct;
    }
}