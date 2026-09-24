class Solution {
    public int maxOperations(int[] nums) {
        HashMap<String, Integer> map = new HashMap<>();
        int ans = 0;
        int l = 0;
        int r = nums.length - 1;
        ans = Math.max(ans, 1 + count(nums, l + 1, r - 1, map, nums[l] + nums[r]));
        ans = Math.max(ans, 1 + count(nums, l + 2, r, map, nums[l] + nums[l + 1]));
        ans = Math.max(ans, 1 + count(nums, l, r - 2, map, nums[r] + nums[r - 1]));

        return ans;
    }

    private int count(int[] nums, int l, int r, Map<String, Integer> map, int target) {
        String key = l + "," + r;
        if (map.containsKey(key))
            return map.get(key);
        if(r-l==1) {
            if(nums[l]+nums[r]==target) return 1;
            return 0;
        }
        var qty = 0;
        if (l < r) {
            if (nums[l] + nums[r] == target)
                qty = Math.max(qty, 1 + count(nums, l + 1, r - 1, map, target));
            if (qty == (r - l + 1)/2) {
                map.put(key, qty);
                return qty;
            }
            if (nums[l] + nums[l + 1] == target)
                qty = Math.max(qty, 1 + count(nums, l + 2, r, map, target));
            if (qty == (r - l + 1)/2) {
                map.put(key, qty);
                return qty;
            }

            if (nums[r] + nums[r - 1] == target)
                qty = Math.max(qty, 1 + count(nums, l, r - 2, map, target));
            if (qty == (r - l + 1)/2) {
                map.put(key, qty);
                return qty;
            }
        }
        map.put(key, qty);
        return qty;
    }
}