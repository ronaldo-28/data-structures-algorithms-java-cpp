class Solution {
    public int maxIncreasingSubarrays(List<Integer> list) {
        Integer[] nums = list.toArray(Integer[]::new);
        int n = nums.length, i = 0, res = 0;
        int prev = 0;

        while (i < n) {
            int start = i;

            while (i + 1 < n && nums[i] < nums[i + 1]) {
                i++;
            }

            int curr = i - start + 1;
            res = Math.max(res, Math.max(curr / 2, Math.min(curr, prev)));
            
            prev = curr;
            i++;
        }
        
        return res;
    }
}