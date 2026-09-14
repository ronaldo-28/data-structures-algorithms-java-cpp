class Solution {

  public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        boolean[] countingNums1 = new boolean[100001];
        int[] diffNums2 = new int[100001];
        
        long absoluteSumDiff = 0;
        for (int i = 0; i < nums1.length; i++) {
            int absoluteDiff = Math.abs(nums1[i]-nums2[i]);
            countingNums1[nums1[i]] = true;
            diffNums2[nums2[i]] = Math.max(absoluteDiff, diffNums2[nums2[i]]);
            absoluteSumDiff += absoluteDiff;
        }
        
        int num1 = -100000;
        int num2 = -100000;
        int diff = 0;
        int maxProfit = 0;
        for (int num = 1; num < 100001; num++) {
            if (countingNums1[num]) {
                maxProfit = Math.max(diff - num + num2, maxProfit);
                num1 = num;
            }
            
            if (diffNums2[num] > 0) {
                maxProfit = Math.max(diffNums2[num] - num + num1, maxProfit);
                if (diff - num + num2 < diffNums2[num]) {
                    diff = diffNums2[num];
                    num2 = num;
                }
            }
        }
        
        return (int)((absoluteSumDiff - maxProfit) % 1_000_000_007);
    }

}