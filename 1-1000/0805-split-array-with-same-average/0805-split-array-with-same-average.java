class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length, sum = 0;
        for(int num : nums) sum += num;
        for(int i = 0; i < nums.length; i++){
            nums[i] = nums[i] * n - sum;
        }
        // new avg, is sum

        // left Pathsum To selectCount Map
        HashMap<Integer, Integer> map = new HashMap<>();
        return process(nums, 0, n/2 + 1, map, 0, 0) || process(nums, n/2 + 1, n, map, 0, 0);
    }

    // limit, excluded
    boolean process(int[] nums, int from, int limit, HashMap<Integer, Integer> map, int pathsum, int selectCount){
        int n = nums.length;
        if(selectCount > 0) {
            if(pathsum == 0 && selectCount < n) return true;
            if(from <= n/2) {
                map.put(pathsum, selectCount); //前一半存表
            } else {
                // 后一半查表
                if(map.containsKey(-pathsum) && 
                        map.get(-pathsum) + selectCount < n) return true;
            }
        }
        if(from >= limit) return false;
        // 背包问题，选与不选
        return process(nums, from+1, limit, map, pathsum, selectCount) || 
                process(nums, from+1, limit, map, pathsum+nums[from], selectCount+1);
    }
}