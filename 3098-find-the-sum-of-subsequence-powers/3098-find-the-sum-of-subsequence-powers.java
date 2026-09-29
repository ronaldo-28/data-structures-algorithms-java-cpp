// class Solution {
//     int MOD = 1_000_000_007;
//     Map<String, Integer> map;

//     private int solveMemo(int[] nums, int len, int k, int diff, int idx, int prev){
//         // base case
//         if(len == k){
//             return diff;
//         }
//         if(idx < 0){
//             return Integer.MAX_VALUE;
//         }

//         String str = ""+diff+"_"+len+"_"+idx+"_"+prev;
//         if(map.containsKey(str)){
//             return map.get(str);
//         }

//         int temp = solveMemo(nums, len, k, diff, idx-1, prev);
//         int total = 0;
//         if(temp != Integer.MAX_VALUE){
//             total += temp;
//         }
//         if(prev != nums.length){
//             diff = Math.min(diff, nums[prev]-nums[idx]);
//         }
//         temp = solveMemo(nums, len+1, k, diff, idx-1, idx);
//         if(temp != Integer.MAX_VALUE){
//             total +=temp;
//         }
//         total %= MOD;
//         map.put(str, total);
//         return total;
//     }

//     public int sumOfPowers(int[] nums, int k) {
//         Arrays.sort(nums);

//         map= new HashMap<>();
//         return solveMemo(nums, 0, k, Integer.MAX_VALUE, nums.length-1, nums.length);
//     }
// }



class Solution {
    int MOD = 1_000_000_007;
    int len;

    private int dfs(int lastIdx, int k, int minDiff, Map<Long, Integer> dp, int[] nums) {
        // Base Case
        if (k == 0) {
            return minDiff;
        }

        long key = (((long) minDiff) << 12) + (lastIdx << 6) + k;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int res = 0;
        for (int i = lastIdx + 1; i <= len - k; i++) {
            // nums[i] as the next element of the sub sequence
            res = (res + dfs(i, k - 1, Math.min(minDiff, nums[i] - nums[lastIdx]), dp, nums)) % MOD;
        }

        dp.put(key, res);
        return res;
    }


    public int sumOfPowers(int[] nums, int k) {
        len = nums.length;
        // why we can/need to sort?
        // 1. because "between any two elements", element order in subsequence does not
        // matter any more
        // 2. only by sorting, we can use nums[i] - nums[lastIdx] to get the minDiff
        // candidate
        Arrays.sort(nums);

        Map<Long, Integer> dp = new HashMap<>();
        int res = 0;
        for (int i = 0; i <= len - k; i++) {
            // nums[i] as the first element of the sub sequence
            res = (res + dfs(i, k - 1, nums[len - 1] - nums[0], dp, nums)) % MOD;
        }
        return res;
    }
}