class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;
        int depth[] = new int[n];
        depth[0] = 1;
        int height = 1;
        for(int i=1;i<n;i++){
            int p = parent[i];
            if(depth[p] == 0){
                int x=p;
                int d=1;
                while(depth[x] == 0){
                    d++;
                    x=parent[x];
                }
                int cur = d;
                x = p;
                while(depth[x] == 0){
                    depth[x] = cur--;
                    x = parent[x];
                }
            }
            depth[i] = depth[p] + 1;
            height = Math.max(height, depth[i]);
        }
        long ans = 0;
        for(int i=0;i<n;i++){
            ans += (long) nums[i] * (height - depth[i] + 1);
        }
        return ans;
    }
}