class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int case1 = helper(nums, firstLen, secondLen);
        int case2 = helper(nums, secondLen, firstLen);

        return Math.max(case1, case2);
    }

    public int helper(int[] nums, int lenA, int lenB)
    {
        int sumA = 0;
        int sumB = 0;
        int maxSumA = Integer.MIN_VALUE;
        int n = nums.length;

        for(int i = 0; i < lenA; i++)
        {
            sumA+=nums[i];
        }

        maxSumA = sumA;

        for(int i = lenA; i < lenA + lenB; i++)
        {
            sumB+=nums[i];
        }

        int maxTotal = maxSumA + sumB;

        for(int i = lenA; i + lenB < n; i++)
        {
            sumA -= nums[i - lenA];
            sumA+= nums[i];

            if (sumA > maxSumA)
                maxSumA = sumA;

            sumB -= nums[i];
            sumB += nums[i + lenB];

            maxTotal = Math.max(maxTotal, maxSumA + sumB);


        }

        return maxTotal;
    }
}