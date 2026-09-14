class Solution {
    int res;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        boolean[][] cache = new boolean[toppingCosts.length][target * 2 + 1];;
        res = baseCosts[0];
        for(int i = 0; i < baseCosts.length; i++){
            if(baseCosts[i] > target){
                if(Math.abs(res - target) > Math.abs(baseCosts[i] - target)){
                    res = baseCosts[i];
                }
                continue;
            }
            dfs(0, baseCosts[i], toppingCosts, target, cache);
        }
        return res;
    }

    public void dfs(int idx, int curr, int[] tc, int t, boolean[][] cache){
        if(cache[0].length <= curr){
            return;
        }
        if(Math.abs(curr - t) < Math.abs(t - res)){
            res = curr;
        }else if(Math.abs(curr - t) == Math.abs(res - t)){
            res = Math.min(res, curr);
        }
        if(idx == tc.length){
            return;
        }
        if(cache[idx][curr]){
            return;
        }
        cache[idx][curr] = true;
        dfs(idx + 1, curr, tc, t, cache);
        dfs(idx + 1, curr + tc[idx], tc, t, cache);
        dfs(idx + 1, curr + tc[idx] * 2, tc, t, cache);
    }
}