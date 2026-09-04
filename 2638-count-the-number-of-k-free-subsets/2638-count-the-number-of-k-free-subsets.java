class Solution {
    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        
        Map<Integer, ArrayList<Integer>> groups = new HashMap<>();

        for(int num: nums){
            groups.computeIfAbsent(num%k, x -> new ArrayList<>()).add(num);
        }

        long count = 1;

        for(ArrayList<Integer> group: groups.values()){

            Collections.sort(group);

            int n = group.size();
            long[] dp = new long[n];
            Arrays.fill(dp, -1);

            long groupCount = getGroupCount(n-1, group, dp, k);

            count = count * groupCount;
        }

        return count;
    }

    public long getGroupCount(int n, ArrayList<Integer> group, long[] dp, int k){

        if(n < 0){
            return 1;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        long take = 0;
        long notTake = getGroupCount(n-1, group, dp, k);

        if(n > 0 && group.get(n) - group.get(n-1) == k){
            take = getGroupCount(n-2, group, dp, k);
        }
        else{
            take = getGroupCount(n-1, group, dp, k);
        }

        return dp[n] = take + notTake;
    }
}