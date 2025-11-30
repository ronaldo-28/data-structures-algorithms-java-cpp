class Solution {
    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < p)
            return -1;
        long target = sum % p;
        if (target == 0)
            return 0;
        HashMap<Integer, Integer> HM = new HashMap<>();
        HM.put(0, -1);
        sum = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % p == target)
                return 1;
            sum += nums[i];
            int a = (int) (sum % p);
            int b = (int) ((sum - target) % p);
            if (HM.containsKey(b))
                res = Math.min(i - HM.get(b), res);
            HM.put(a, i);
        }
        if (res >= nums.length)
            return -1;
        return res;
    }
}