class Solution {
    public long[] maximumCoins(int[] heroes, int[] monsters, int[] coins) {
        int n = heroes.length;
        int m = monsters.length;
        long[] ans = new long[n];
        long[] prefixSum = new long[m];
        long sum = 0;
        List<int[]> temp = new ArrayList<>();
        for(int i=0;i < m;i++)
        {
            temp.add(new int[]{monsters[i],coins[i]});
        }
        Collections.sort(temp,(a,b) -> (a[0]-b[0]));
        for(int i=0;i < m;i++)
        {
            sum += (long)(temp.get(i)[1]);
            prefixSum[i] = sum;
        }
        Arrays.sort(monsters);
        for(int i=0;i < n;i++)
        {
            int t = upperBound(heroes[i],monsters);
            if(t == -1)
            {
                ans[i] = 0;
            }
            else
            {
                ans[i] = prefixSum[t];
            }
        }
        return ans;
    }

    public int upperBound(int t,int[] monsters)
    {
        int low = 0;
        int high = monsters.length-1;
        int res = -1;
        while(low <= high)
        {
            int mid = (low+high)/2;
            if(t >= monsters[mid])
            {
                res = mid;
                low = mid+1;
            }
            else
            {
                high = mid-1;
            }
        }
        return res;
    }

}