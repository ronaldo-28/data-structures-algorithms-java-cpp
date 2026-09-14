//Lord Shiva
class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n=coins.length;

        int dp[]=new int[n];
        int next[]=new int[n];
        Arrays.fill(next,-1);

        if(coins[n-1]==-1){
            return new ArrayList<>();
        }
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[n-1]=coins[n-1];

        Deque<Integer>dq=new LinkedList<>();
        dq.offerLast(n-1);
        for(int i=n-2;i>=0;i--){
            if(coins[i]==-1){
                continue;
            }
            while(!dq.isEmpty() && dq.peekFirst()>i+maxJump){
                dq.pollFirst();
            }
            if(!dq.isEmpty()){
                int bestPos=dq.peekFirst();
                dp[i]=coins[i]+dp[bestPos];
                next[i]=bestPos;

                while(!dq.isEmpty() && dp[i]<=dp[dq.peekLast()]){
                    dq.pollLast();
                }
                dq.offerLast(i);
            }
        }

        if(dp[0]==Integer.MAX_VALUE){
            return new ArrayList<>();
        }

        List<Integer>ans=new ArrayList<>();
        int curr=0;
        while(curr!=-1){
            ans.add(curr+1);
            curr=next[curr];
        }
        return ans;

    }
}
//b1b1v1