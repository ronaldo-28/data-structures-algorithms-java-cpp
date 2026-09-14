class Solution {
    public boolean canPossible(int[] candies,long k,int n) {
        long noOfChilds = 0;
        for(int pile : candies) {
            noOfChilds += pile / n;
            if(noOfChilds >= k)
                return true;
        }
        return noOfChilds >= k;
    }
    public int maximumCandies(int[] candies, long k) {
        long sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        if (sum / k == 0) { // not enough candies
            return 0;
        }
        int low = 1,high = (int)Math.min(sum / k, 10_000_000L),ans = 0;
        // for(int pile : candies)
        //     high = Math.max(high,pile);
        while(low <= high) {
            int mid = low + (high - low) /2;
            if(canPossible(candies,k,mid)) {
                ans = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return ans;
    }
}