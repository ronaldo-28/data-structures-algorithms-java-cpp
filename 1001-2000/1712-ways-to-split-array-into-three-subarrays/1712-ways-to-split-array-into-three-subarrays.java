class Solution {
 public int waysToSplit(int[] nums){
        int n = nums.length;
        int MOD = 1_000_000_007;

        //1 . Calculate prefix in sum
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }

        int ans = 0;
        int j = 1;  //left bound for mid array
        int k = 1; //right bound for mid array

        //2 . Iterate through the possible end indices of the 'left' array
        for (int i = 0; i < n - 2; i++) {
            if (3 * nums[i] > nums[n - 1]) break;

            j = Math.max(j, i + 1);
            while (j <= n - 2 && nums[j] < 2 * nums[i]) j++;

            k = Math.max(k , j);
            while (k <= n - 2 && 2 * nums[k] <= nums[n - 1] +  nums[i]) k++;

            if (k > j) ans = (ans + (k - j)) % MOD;
        }
        return ans;
    }
}

