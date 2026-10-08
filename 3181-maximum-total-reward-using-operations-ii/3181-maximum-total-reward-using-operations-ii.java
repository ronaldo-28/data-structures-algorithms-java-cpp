class Solution {
   public int maxTotalReward(int[] rewardValues) {
       // Important observation: We must take the max element of the rewardValues array.
       // We can even say : The optimal solution sum <= 2*max(rewardValues) - 1;

       TreeSet<Integer> set = new TreeSet<>();
       for(int num : rewardValues) set.add(num);
       return set.last() + DFS(set, new HashMap<>(), set.last());
   }

       // DFS + Memo
       // DFS(arr, map, limit): try to find the max sum strictly smaller than the limit with elements in arr.(The arr is represented by a sorted set)
       // Memo: <limit, max_sum>, key is the limit of the sum, value is the max sum < limit that we can build from the array
   private int DFS(TreeSet<Integer> nums, Map<Integer, Integer> map, int limit) {
       if(limit == 0) {
           return 0;
       }
       if(map.containsKey(limit)) {
           return map.get(limit);
       }
       if(nums.contains(limit-1)) {
           return limit-1;
       }
       int res = 0;
       for(int num : nums.headSet(limit, false)) {
           res = Math.max(res, num + DFS(nums, map, Math.min(limit-num, num)));
       }
       map.put(limit, res);
       return res;
   }
}