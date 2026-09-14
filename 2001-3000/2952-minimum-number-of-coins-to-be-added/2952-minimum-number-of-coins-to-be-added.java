class Solution {
    public int minimumAddedCoins(int[] coins, int target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i : coins) {
            if(i <= target) pq.add(i);
        }

        int currReachable = 0;
        int requiredCoins = 0;

        while(!pq.isEmpty()) {
            int currCoin = pq.remove();

            while(currReachable + 1 < currCoin) {
                int newCoin = currReachable + 1;
                currReachable += newCoin;

                requiredCoins++;
            }

            currReachable += currCoin;
            if(currReachable >= target) return requiredCoins;
        }

        while(currReachable < target) {
            currReachable += currReachable + 1;
            requiredCoins++;
        }

        return requiredCoins;
    }
}